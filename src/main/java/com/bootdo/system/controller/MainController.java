package com.bootdo.system.controller;

import com.bootdo.app.config.Constants;
import com.bootdo.app.util.DistributedLock;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.app.zwlenum.RoleTypeEnum;
import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.JSONUtils;
import com.bootdo.common.utils.MathUtil;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.BankInfoDO;
import com.bootdo.system.domain.MerchantDO;
import com.bootdo.system.domain.StatisticInfoDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.BankInfoService;
import com.bootdo.system.service.MerchantService;
import com.bootdo.system.service.PayAlipayInfoService;
import com.bootdo.system.service.PayWechatInfoService;
import com.bootdo.system.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Prometheus
 * @date 2021/8/22.
 */
@Controller
@RequestMapping("/main")
public class MainController extends BaseController {

    private DistributedLock distributedLock;

    @Autowired
    public void setDistributedLock(DistributedLock distributedLock) {
        this.distributedLock = distributedLock;
    }

    private BankInfoService bankInfoService;
    private PayAlipayInfoService alipayInfoService;
    private PayWechatInfoService wechatInfoService;
    private RoleService roleService;
    private MerchantService merchantService;

    private RedisUtils redisUtil;

    @Autowired
    public MainController(BankInfoService bankInfoService,
                          PayAlipayInfoService alipayInfoService,
                          PayWechatInfoService wechatInfoService,
                          RoleService roleService,
                          MerchantService merchantService,
                          RedisUtils redisUtil) {
        this.bankInfoService = bankInfoService;
        this.alipayInfoService = alipayInfoService;
        this.wechatInfoService = wechatInfoService;
        this.roleService = roleService;
        this.merchantService = merchantService;
        this.redisUtil = redisUtil;
    }

    @GetMapping()
    String main(Model model) {
        // 统计信息
        UserDO user = ShiroUtils.getUser();
        final Long userId = user.getUserId();

        // 看当前登录人是啥角色
        final RoleTypeEnum currentRoleEnum = roleService.distinguishByLoginInfo(user);
        model.addAttribute("roleType", currentRoleEnum);

        // 商户信息
        if (currentRoleEnum == RoleTypeEnum.ALL || currentRoleEnum == RoleTypeEnum.MERCHANT) {
            final String mKey = String.format("%s%s", Constant.PREFIX_OF_M_BASIC_INFO, userId);
            MerchantDO merchantDO = getMerchantBizInfoFromRedis(mKey);
            if (merchantDO != null) {
                model.addAttribute("merchantInfo", merchantDO);
            } else {
                MerchantDO byMid = merchantService.getByMid(userId);
                if (byMid != null) {
                    redisUtil.set(mKey, byMid);
                    model.addAttribute("merchantInfo", byMid);
                } else {
                    final String unknown = "不详";
                    model.addAttribute("merchantInfo", new MerchantDO(unknown, unknown, unknown));
                }
            }
        }
        return "main";
    }

    @Log("主页统计信息")
    @GetMapping({"/statistics"})
    @ResponseBody
    Object statistics() {
        // 统计信息
        UserDO user = ShiroUtils.getUser();
        final Long userId = user.getUserId();

        // 看当前登录人是啥角色
        final RoleTypeEnum currentRoleEnum = roleService.distinguishByLoginInfo(user);

        StatisticInfoDO statInfoDO = new StatisticInfoDO().emptyInstance(-1);
        statInfoDO.setCurrentRoleEnum(currentRoleEnum);

        String mKey = "";
        // 做对应统计
        if (currentRoleEnum == RoleTypeEnum.MERCHANT) {
            mKey = String.format("%s%s", Constant.PREFIX_OF_STAT_MERCHANT, userId);
        }
        if (currentRoleEnum == RoleTypeEnum.CODER) {
            mKey = String.format("%s%s", Constant.PREFIX_OF_STAT_CODER, userId);
        }
        if (StringUtils.isNotEmpty(mKey)) {
            StatisticInfoDO infoFromRedis = getStatisticFromRedis(mKey);
            if (infoFromRedis != null) {
                BeanUtils.copyProperties(infoFromRedis, statInfoDO);
            }
        }
        if (currentRoleEnum == RoleTypeEnum.ALL) {
            mKey = String.format("%s%s", Constant.PREFIX_OF_STAT_MERCHANT, userId);
            StatisticInfoDO infoFromRedis = getStatisticFromRedis(mKey);
            if (infoFromRedis != null) {
                statInfoDO.setTotalReceivedAmount(infoFromRedis.getTotalReceivedAmount());
                statInfoDO.setDailyReceivedAmount(infoFromRedis.getDailyReceivedAmount());
            }

            mKey = String.format("%s%s", Constant.PREFIX_OF_STAT_CODER, userId);
            infoFromRedis = getStatisticFromRedis(mKey);
            if (infoFromRedis != null) {
                statInfoDO.setTotalReceivedAmount(infoFromRedis.getTotalReceivedAmount());
                statInfoDO.setTotalTxTimes(infoFromRedis.getTotalTxTimes());
                statInfoDO.setSucceedTxTimes(infoFromRedis.getSucceedTxTimes());
                statInfoDO.setTxSucceedRate(infoFromRedis.getTxSucceedRate());
            }
        }
        if (currentRoleEnum == RoleTypeEnum.UNKNOWN) {
            new StatisticInfoDO().emptyInstance(-1);
        }
        return statInfoDO;
    }

    /**
     * 收款码的统计信息
     *
     * @param mKey
     * @return
     */
    private StatisticInfoDO getStatisticFromRedis(final String mKey) {
        distributedLock.releaseLock(Constants.PRODUCT_LOCK_ID);
        distributedLock.getLock(Constants.PRODUCT_LOCK_ID);
        final String result = JSONUtils.beanToJson(redisUtil.get(mKey));
        distributedLock.releaseLock(Constants.PRODUCT_LOCK_ID);
        if (StringUtils.isNotEmpty(result)) {
            return (StatisticInfoDO) JSONUtils.jsonToBean(result, StatisticInfoDO.class);
        }
        return null;
    }

    /**
     * 商户基本信息
     *
     * @param mKey
     * @return
     */
    private MerchantDO getMerchantBizInfoFromRedis(final String mKey) {
        distributedLock.releaseLock(Constants.PRODUCT_LOCK_ID);
        distributedLock.getLock(Constants.PRODUCT_LOCK_ID);
        final String result = JSONUtils.beanToJson(redisUtil.get(mKey));
        distributedLock.releaseLock(Constants.PRODUCT_LOCK_ID);
        if (StringUtils.isNotEmpty(result)) {
            return (MerchantDO) JSONUtils.jsonToBean(result, MerchantDO.class);
        }
        return null;
    }

    @Log("主页统计信息")
    @GetMapping({"/statistic"})
    @ResponseBody
    Object statistic() {
        // 统计商户信息
        UserDO user = ShiroUtils.getUser();
        final Long userId = user.getUserId();

        // 看当前登录人是啥角色
        final RoleTypeEnum currentRoleEnum = roleService.distinguishByLoginInfo(user);

        StatisticInfoDO statInfoDO = new StatisticInfoDO().emptyInstance();
        // 做对应统计
        if (currentRoleEnum == RoleTypeEnum.MERCHANT) {
            statMerchantInfo(statInfoDO);
        }
        if (currentRoleEnum == RoleTypeEnum.CODER) {
            statCoderInfo(statInfoDO);
        }
        if (currentRoleEnum == RoleTypeEnum.ALL) {
            statMerchantInfo(statInfoDO);
            statCoderInfo(statInfoDO);
        }
        if (currentRoleEnum == RoleTypeEnum.UNKNOWN) {
            new StatisticInfoDO().emptyInstance(-1);
        }

        final List<BankInfoDO> bankInfoList = bankInfoService.getByUserId(userId);

        // TODO Alipay

        // TODO WePay

        if (!CollectionUtils.isEmpty(bankInfoList)) {
            BigDecimal totalReceivedAmount = MathUtil.addUpDigit(bankInfoList.stream().filter(e -> e.getTotalReceivedAmount() != null)
                    .map(BankInfoDO::getTotalReceivedAmount).collect(Collectors.toList()));
            statInfoDO.setTotalReceivedAmount(totalReceivedAmount);
        }
        return statInfoDO;
    }

    private void statCoderInfo(StatisticInfoDO statInfoDO) {
    }

    private void statMerchantInfo(StatisticInfoDO statInfoDO) {

    }

}
