package com.bootdo.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.app.config.Constants;
import com.bootdo.app.model.MerchantPayInfo;
import com.bootdo.app.model.Result;
import com.bootdo.app.model.StatisticsInfo;
import com.bootdo.app.util.DistributedLock;
import com.bootdo.app.util.NumberUtil;
import com.bootdo.app.util.OrderCodeUtil;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.app.zwlenum.PayTypeEnum;
import com.bootdo.system.domain.MerchantDO;
import com.bootdo.system.domain.OrderDO;
import com.bootdo.system.dto.Order4InstanceReqDTO;
import com.bootdo.system.service.MerchantService;
import com.bootdo.system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author Prometheus
 * @date 2021/8/29.
 */
@Controller
@RequestMapping("/e")
public class PayExamController {
    private RedisUtils redisUtils;
    private OrderService orderService;
    private MerchantService merchantService;
    private SimpMessagingTemplate template;
    private DistributedLock distributedLock;

    @Autowired
    public PayExamController(RedisUtils redisUtils, OrderService orderService, MerchantService merchantService, SimpMessagingTemplate template, DistributedLock distributedLock) {
        this.redisUtils = redisUtils;
        this.orderService = orderService;
        this.merchantService = merchantService;
        this.template = template;
        this.distributedLock = distributedLock;
    }

    @GetMapping("/toward")
    String toward() {
        return "pay_instance";
    }

    @RequestMapping(value = "/createOrder")
    @ResponseBody
    public Result<Map> createOrder(@Validated Order4InstanceReqDTO paymentInfo) throws Exception {
        // 根据merchantId 获取商户信息
        try {
            final String merchantNo = "1000001";
            MerchantDO merchant = merchantService.getByMerchantNo(merchantNo);
            if (merchant == null) {
                return Result.error("商户不对，merchantNo不正确");
            }

            //校验签名
//            boolean signResult = checkSign(merchant.getSecretKey(), paymentInfo);
//            if (!signResult) {
//                return Result.error("签名不正确");
//            }

            //判断订单额度
            String amount = paymentInfo.getAmount();
            if (NumberUtil.compare("100", amount) > 0 || NumberUtil.compare("5000001", amount) < 0) {
                return Result.error("订单额度在10-5000元之间");
            }

            //创建订单
            String type = paymentInfo.getType();
            PayTypeEnum payTypeEnum = PayTypeEnum.getByKey(type);
            if (payTypeEnum == null) {
                return Result.error("支付方式错误");
            }
            OrderDO order = null;

            paymentInfo.setRemark(String.format("测试支付_%s", OrderCodeUtil.getDateTime()));
            if (type.equals(PayTypeEnum.WECHAT_CODE.getKey())) {
                order = orderService.createWechatOrder(paymentInfo);
            } else if (type.equals(PayTypeEnum.APLIPAY_CODE.getKey())) {
                order = orderService.createAlipayOrder(paymentInfo);
            } else if (type.equals(PayTypeEnum.BANK_CODE.getKey())) {
                order = orderService.createBankOrder(paymentInfo);
            }
            Integer second = (Integer) redisUtils.get(Constants.ORDER_TIMER_KEY);
            redisUtils.set(Constants.getOrderKey(order.getOrderNo()), order, second);
            //生成支付返回给商户系统
            MerchantPayInfo merchantPayInfo = generatorPayInfo(order);
            final String no = order.getOrderNo();
            final String mid = order.getMid() + "";
            new Thread() {
                @Override
                public void run() {
                    //统计码商 和 商户的信息
                    staticsOrder(no);
                    //推送新订单
                    template.convertAndSend("/topic/" + mid + "/getOrderNo", no);
                }
            }.start();
            return Result.ok(merchantPayInfo);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    private MerchantPayInfo generatorPayInfo(OrderDO order) {
        MerchantPayInfo merchantPayInfo = new MerchantPayInfo();
        String payInfoStr = order.getPaymentInfo();
        JSONObject payInfoJSON = JSONObject.parseObject(payInfoStr);
        String amount = order.getAmount();
        String type = order.getPayType();
        String reallyAmount = order.getReallyAmount();
        String url = payInfoJSON.getString("remark");
        String account = payInfoJSON.getString("account");
        String name = payInfoJSON.getString("name");
        JSONObject tempInfo = new JSONObject();
        tempInfo.put("amount", amount);
        tempInfo.put("type", type);
        tempInfo.put("reallyAmount", reallyAmount);
        tempInfo.put("account", account);
        tempInfo.put("name", name);
        tempInfo.put("url", url);
        merchantPayInfo.setPayInfo(tempInfo);
        String payApi = (String) redisUtils.get(Constants.ORDER_PAY_URL);
        merchantPayInfo.setPayUrl(payApi + "/" + order.getOrderNo());
        return merchantPayInfo;
    }

    public void staticsOrder(String orderNo) {
        String key = Constants.getOrderKey(orderNo);
        OrderDO orderDO = (OrderDO) redisUtils.get(key);
        String payInfo = orderDO.getPaymentInfo();
        JSONObject payInfoJSON = JSONObject.parseObject(payInfo);
        String type = orderDO.getPayType();
        String payId = payInfoJSON.getString("id");
        String payStatisticsInfoKey = "payStatisticsInfoKey_" + orderDO.getMid() + "_" + type + ":" + payId;
        updateStatisticsInfo(payStatisticsInfoKey, orderDO);
        String merchantStatisticsInfoKey = "merchantStatisticsInfoKey_" + orderDO.getMerchantNo();
        updateStatisticsInfo(merchantStatisticsInfoKey, orderDO);
    }

    private void updateStatisticsInfo(String key, OrderDO orderDO) {
        if (redisUtils.hasKey(key)) {
            try {
                distributedLock.getLock(key);
                StatisticsInfo statisticsInfo = (StatisticsInfo) redisUtils.get(key);
                statisticsInfo.setTotalAmount(statisticsInfo.getTotalAmount() + Integer.parseInt(orderDO.getAmount()));
                statisticsInfo.setTotalOrderNum(statisticsInfo.getTotalOrderNum() + 1);
                redisUtils.set(key, statisticsInfo);
            } finally {
                distributedLock.releaseLock(key);
            }
        } else {
            try {
                distributedLock.getLock(key);
                StatisticsInfo statisticsInfo = new StatisticsInfo();
                statisticsInfo.setPayedTotalOrderNum(0);
                statisticsInfo.setTotalAmount(Integer.parseInt(orderDO.getAmount()));
                statisticsInfo.setTotalOrderNum(1);
                statisticsInfo.setPayedTotalAmount(0);
                redisUtils.set(key, statisticsInfo);
            } finally {
                distributedLock.releaseLock(key);
            }
        }
    }

}
