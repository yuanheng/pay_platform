package com.bootdo.system.adptor;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.bootdo.app.util.DistributedLock;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.app.zwlenum.OrderStatusEnum;
import com.bootdo.app.zwlenum.PayTypeEnum;
import com.bootdo.common.config.Constant;
import com.bootdo.common.utils.MathUtil;
import com.bootdo.system.domain.BankInfoDO;
import com.bootdo.system.domain.OrderDO;
import com.bootdo.system.domain.PayAlipayInfoDO;
import com.bootdo.system.domain.PayWechatInfoDO;
import com.bootdo.system.domain.StatisticInfoDO;
import com.bootdo.system.service.BankInfoService;
import com.bootdo.system.service.OrderService;
import com.bootdo.system.service.PayAlipayInfoService;
import com.bootdo.system.service.PayWechatInfoService;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 确认收款后订单相关联的收款户统计信息更新
 * <p>
 * 1. 收款方统计信息更新
 * 2. 商户放统计信息更新
 *
 * @author Prometheus
 * @date 2021/8/22.
 */
@Component
public class AccountStatisticsSynchronizer {

    private static Logger logger = LoggerFactory.getLogger(AccountStatisticsSynchronizer.class);

    private DistributedLock lock;
    private BankInfoService bankInfoService;
    private PayWechatInfoService wechatInfoService;
    private PayAlipayInfoService alipayInfoService;
    private OrderService orderService;
    private RedisUtils redisUtils;

    @Autowired
    public AccountStatisticsSynchronizer(DistributedLock lock, BankInfoService bankInfoService,
                                         PayWechatInfoService wechatInfoService,
                                         PayAlipayInfoService alipayInfoService,
                                         OrderService orderService,
                                         RedisUtils redisUtils) {
        this.lock = lock;
        this.bankInfoService = bankInfoService;
        this.wechatInfoService = wechatInfoService;
        this.alipayInfoService = alipayInfoService;
        this.orderService = orderService;
        this.redisUtils = redisUtils;
    }

    public boolean triggerStatistics(OrderDO orderDO) {
        return triggerCoderStatistics(orderDO) && triggerMerchantStatistics(orderDO);
    }

    /**
     * 更新商户统计缓存
     *
     * @param orderDO 订单信息
     * @return 结果
     */
    public boolean triggerMerchantStatistics(OrderDO orderDO) {
        OrderDO.Payment payment = orderDO.getPayment();
        final Long mid = payment.getMid();
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("merchantNo", mid);
        paramMap.put("status", OrderStatusEnum.FINISHED_PAY.getKey());
        List<OrderDO> list = orderService.list(paramMap);
        StatisticInfoDO statisticInfoDO = new StatisticInfoDO().emptyInstance();
        final String datePattern = "yyyy-MM-dd";
        if (!CollectionUtils.isEmpty(list)) {
            final List<BigDecimal> totalAmountList = list.stream().filter(e -> StringUtils.isNotEmpty(e.getReallyAmount()))
                    .map(e -> BigDecimal.valueOf(Double.parseDouble(e.getReallyAmount()))
                            .multiply(BigDecimal.valueOf(100))).collect(Collectors.toList());
            final BigDecimal totalAmt = MathUtil.addUpDigit(totalAmountList);
            final List<BigDecimal> dailyAmountList = list.stream().filter(e -> StringUtils.isNotEmpty(e.getReallyAmount())
                    && e.getFinishTime() != null
                    && DateUtil.format(e.getFinishTime(), datePattern).equals(DateUtil.format(new Date(), datePattern)))
                    .map(e -> BigDecimal.valueOf(Double.parseDouble(e.getReallyAmount()))
                            .multiply(BigDecimal.valueOf(100))).collect(Collectors.toList());
            final BigDecimal dailyAmount = MathUtil.addUpDigit(dailyAmountList);
            statisticInfoDO.setTotalReceivedAmount(totalAmt);
            statisticInfoDO.setDailyReceivedAmount(dailyAmount);

            final String lockKey = String.format("%s%s", Constant.LOCK_4_STAT_M_INFO, orderDO.getMid());
            lock.getLock(lockKey);
            final String mKey = String.format("%s%s", Constant.PREFIX_OF_STAT_MERCHANT, mid);
            redisUtils.set(mKey, statisticInfoDO);
            lock.releaseLock(lockKey);
            logger.info("更新统计：商户：[{}] -> {}.", JSONUtil.toJsonStr(payment), JSONUtil.toJsonStr(statisticInfoDO));
        }
        return true;
    }

    /**
     * 更新码商统计缓存
     *
     * @param orderDO 订单信息
     * @return 结果
     */
    public boolean triggerCoderStatistics(OrderDO orderDO) {
        String payType = orderDO.getPayType();
        OrderDO.Payment payment = orderDO.getPayment();
        final String accountNo = payment.getAccount();
        final Long mid = payment.getMid();
        StatisticInfoDO statisticInfoDO = new StatisticInfoDO().emptyInstance();
        StatisticInfoDO bankStat = new StatisticInfoDO().emptyInstance();
        StatisticInfoDO wechatStat = new StatisticInfoDO().emptyInstance();
        StatisticInfoDO alipayStat = new StatisticInfoDO().emptyInstance();
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("mid", mid);
        paramMap.put("account", accountNo);
        switch (PayTypeEnum.getByKey(payType)) {
            case BANK_CODE:
                List<BankInfoDO> bankInfoDOList = bankInfoService.list(paramMap);
                bankStat = statBankInfo2Redis(orderDO, bankInfoDOList);
                break;
            case WECHAT_CODE:
                List<PayWechatInfoDO> wechatInfoDOList = wechatInfoService.list(paramMap);
                wechatStat = statWechatPayInfo2Redis(orderDO, wechatInfoDOList);
                break;
            case APLIPAY_CODE:
                List<PayAlipayInfoDO> alipayInfoDOList = alipayInfoService.list(paramMap);
                alipayStat = statAliPayInfo2Redis(orderDO, alipayInfoDOList);
                break;
            default:
                break;
        }
        statisticInfoDO.setTotalReceivedAmount(bankStat.getTotalReceivedAmount()
                .add(wechatStat.getTotalReceivedAmount().add(alipayStat.getTotalReceivedAmount())));

        statisticInfoDO.setTotalTxTimes(bankStat.getTotalTxTimes()
                + wechatStat.getTotalTxTimes() + alipayStat.getTotalTxTimes());

        statisticInfoDO.setSucceedTxTimes(bankStat.getSucceedTxTimes()
                + wechatStat.getSucceedTxTimes() + alipayStat.getSucceedTxTimes());

        statisticInfoDO.setTxSucceedRate(
                MathUtil.divide(statisticInfoDO.getSucceedTxTimes(), statisticInfoDO.getTotalTxTimes()));

        final String lockKey = String.format("%s%s", Constant.LOCK_4_STAT_C_INFO, orderDO.getMid());
        lock.getLock(lockKey);
        final String mKey = String.format("%s%s", Constant.PREFIX_OF_STAT_CODER, mid);
        redisUtils.set(mKey, statisticInfoDO);
        lock.releaseLock(lockKey);
        logger.info("更新统计：码商：{} -> {}.", JSONUtil.toJsonStr(payment), JSONUtil.toJsonStr(statisticInfoDO));
        return true;
    }

    private StatisticInfoDO statBankInfo2Redis(OrderDO orderDO, List<BankInfoDO> list) {
        StatisticInfoDO statisticInfoDO = new StatisticInfoDO().emptyInstance();
        if (!CollectionUtils.isEmpty(list)) {
            List<String> accountNoList;
            if (list.size() > 1) {
                accountNoList = list.stream().filter(e -> StringUtils.isNotEmpty(e.getAccount()))
                        .map(BankInfoDO::getAccount).collect(Collectors.toList());
                final String warnMsg = String.format("查到[%s]多条收款信息，仅对第一条[%s]进行统计。",
                        String.join(Constant.SYMBOL_COMMA, accountNoList), accountNoList.get(0));
                logger.warn(warnMsg);
            }
            BankInfoDO infoDO = list.get(0);

            final String lockKey = String.format("%s%s", Constant.LOCK_4_STAT_C_INFO, infoDO.getId());
            lock.getLock(lockKey);
            BigDecimal totalReceivedAmount = infoDO.getTotalReceivedAmount() != null
                    ? infoDO.getTotalReceivedAmount() : BigDecimal.ZERO;
            Integer totalTxTimes = infoDO.getTotalTxTimes() != null ? infoDO.getTotalTxTimes() : 0;
            Integer succeedTxTimes = infoDO.getSucceedTxTimes() != null ? infoDO.getSucceedTxTimes() : 0;
            totalTxTimes++;
            succeedTxTimes++;
            totalReceivedAmount = totalReceivedAmount
                    .add(BigDecimal.valueOf(Double.parseDouble(orderDO.getReallyAmount()))
                            .multiply(BigDecimal.valueOf(100)));
            BigDecimal succeedRate = MathUtil.divide(succeedTxTimes, totalTxTimes).multiply(BigDecimal.valueOf(100));

            statisticInfoDO.setTotalReceivedAmount(totalReceivedAmount);
            statisticInfoDO.setTotalTxTimes(totalTxTimes);
            statisticInfoDO.setSucceedTxTimes(succeedTxTimes);
            statisticInfoDO.setTxSucceedRate(succeedRate);

            infoDO.setTotalReceivedAmount(totalReceivedAmount);
            infoDO.setTotalTxTimes(totalTxTimes);
            infoDO.setSucceedTxTimes(succeedTxTimes);
            infoDO.setTxSucceedRate(succeedRate);
            bankInfoService.update(infoDO);
            lock.releaseLock(lockKey);
        } else {
            logger.warn("未找到有效收款信息.");
        }
        return statisticInfoDO;
    }

    private StatisticInfoDO statWechatPayInfo2Redis(OrderDO orderDO, List<PayWechatInfoDO> list) {
        StatisticInfoDO statisticInfoDO = new StatisticInfoDO().emptyInstance();
        if (!CollectionUtils.isEmpty(list)) {
            List<String> accountNoList;
            if (list.size() > 1) {
                accountNoList = list.stream().filter(e -> StringUtils.isNotEmpty(e.getAccount()))
                        .map(PayWechatInfoDO::getAccount).collect(Collectors.toList());
                final String warnMsg = String.format("查到[%s]多条收款信息，仅对第一条[%s]进行统计。",
                        String.join(Constant.SYMBOL_COMMA, accountNoList), accountNoList.get(0));
                logger.warn(warnMsg);
            }
            PayWechatInfoDO infoDO = list.get(0);
            final String lockKey = String.format("%s%s", Constant.LOCK_4_STAT_C_INFO, infoDO.getId());
            lock.getLock(lockKey);
            BigDecimal totalReceivedAmount = infoDO.getTotalReceivedAmount() != null
                    ? infoDO.getTotalReceivedAmount() : BigDecimal.ZERO;
            Integer totalTxTimes = infoDO.getTotalTxTimes() != null ? infoDO.getTotalTxTimes() : 0;
            Integer succeedTxTimes = infoDO.getSucceedTxTimes() != null ? infoDO.getSucceedTxTimes() : 0;
            totalTxTimes++;
            succeedTxTimes++;
            totalReceivedAmount = totalReceivedAmount
                    .add(BigDecimal.valueOf(Double.parseDouble(orderDO.getReallyAmount()))
                            .multiply(BigDecimal.valueOf(100)));
            BigDecimal succeedRate = MathUtil.divide(succeedTxTimes, totalTxTimes).multiply(BigDecimal.valueOf(100));
            statisticInfoDO.setTotalReceivedAmount(totalReceivedAmount);
            statisticInfoDO.setTotalTxTimes(totalTxTimes);
            statisticInfoDO.setSucceedTxTimes(succeedTxTimes);
            statisticInfoDO.setTxSucceedRate(succeedRate);

            infoDO.setTotalReceivedAmount(totalReceivedAmount);
            infoDO.setTotalTxTimes(totalTxTimes);
            infoDO.setSucceedTxTimes(succeedTxTimes);
            infoDO.setTxSucceedRate(succeedRate);
            wechatInfoService.update(infoDO);
            lock.releaseLock(lockKey);
        } else {
            logger.warn("未找到有效收款信息.");
        }
        return statisticInfoDO;
    }

    private StatisticInfoDO statAliPayInfo2Redis(OrderDO orderDO, List<PayAlipayInfoDO> list) {
        StatisticInfoDO statisticInfoDO = new StatisticInfoDO().emptyInstance();
        if (!CollectionUtils.isEmpty(list)) {
            List<String> accountNoList;
            if (list.size() > 1) {
                accountNoList = list.stream().filter(e -> StringUtils.isNotEmpty(e.getAccount()))
                        .map(PayAlipayInfoDO::getAccount).collect(Collectors.toList());
                final String warnMsg = String.format("查到[%s]多条收款信息，仅对第一条[%s]进行统计。",
                        String.join(Constant.SYMBOL_COMMA, accountNoList), accountNoList.get(0));
                logger.warn(warnMsg);
            }
            PayAlipayInfoDO infoDO = list.get(0);
            final String lockKey = String.format("%s%s", Constant.LOCK_4_STAT_C_INFO, infoDO.getId());
            lock.getLock(lockKey);
            BigDecimal totalReceivedAmount = infoDO.getTotalReceivedAmount() != null
                    ? infoDO.getTotalReceivedAmount() : BigDecimal.ZERO;
            Integer totalTxTimes = infoDO.getTotalTxTimes() != null ? infoDO.getTotalTxTimes() : 0;
            Integer succeedTxTimes = infoDO.getSucceedTxTimes() != null ? infoDO.getSucceedTxTimes() : 0;
            totalTxTimes++;
            succeedTxTimes++;
            totalReceivedAmount = totalReceivedAmount
                    .add(BigDecimal.valueOf(Double.parseDouble(orderDO.getReallyAmount()))
                            .multiply(BigDecimal.valueOf(100)));
            BigDecimal succeedRate = MathUtil.divide(succeedTxTimes, totalTxTimes).multiply(BigDecimal.valueOf(100));
            statisticInfoDO.setTotalReceivedAmount(totalReceivedAmount);
            statisticInfoDO.setTotalTxTimes(totalTxTimes);
            statisticInfoDO.setSucceedTxTimes(succeedTxTimes);
            statisticInfoDO.setTxSucceedRate(succeedRate);

            infoDO.setTotalReceivedAmount(totalReceivedAmount);
            infoDO.setTotalTxTimes(totalTxTimes);
            infoDO.setSucceedTxTimes(succeedTxTimes);
            infoDO.setTxSucceedRate(succeedRate);
            alipayInfoService.update(infoDO);
            lock.releaseLock(lockKey);
        } else {
            logger.warn("未找到有效收款信息.");
        }
        return statisticInfoDO;
    }
}
