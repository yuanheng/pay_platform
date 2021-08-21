package com.bootdo.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.app.config.Constants;
import com.bootdo.app.model.MerchantPayInfo;
import com.bootdo.app.model.PaymentInfo;
import com.bootdo.app.model.Result;
import com.bootdo.app.util.Encript;
import com.bootdo.app.util.NumberUtil;
import com.bootdo.app.util.RedisUtils;

import com.bootdo.app.zwlenum.PayTypeEnum;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.system.domain.MerchantDO;
import com.bootdo.system.domain.OrderDO;
import com.bootdo.system.service.MerchantService;
import com.bootdo.system.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;


@RequestMapping("/api")
@Controller
public class ApiController {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private OrderService orderService;
    @Autowired
    private MerchantService merchantService;

    private static Logger logger = LoggerFactory.getLogger(ApiController.class);


    @GetMapping("/pay/{orderNo}")
    String add(@PathVariable("orderNo") String orderNo, Model model) {
        OrderDO order = (OrderDO) redisUtils.get(orderNo);
        String payInfo = order.getPaymentInfo();
        JSONObject payInfoJSON = JSONObject.parseObject(payInfo);
        String reallyAmount = order.getReallyAmount();
        model.addAttribute("reallyAmount", NumberUtil.divide(reallyAmount,"100"));
        model.addAttribute("payInfo", payInfoJSON);
        Integer second = (Integer) redisUtils.get(Constants.ORDER_TIMER_KEY);
        Integer minute = second / 60 - 1;
        model.addAttribute("minute", minute);
        model.addAttribute("order", order);
        Date createTime = order.getCreateTime();
        long createTimeMillions  = createTime.getTime();
        long currentTime = System.currentTimeMillis();
        long timer = (currentTime - createTimeMillions)/1000;
        timer = second - timer;
        if (timer > 0) {
            model.addAttribute("second", timer);
        } else {
            model.addAttribute("second", 0);
        }
        String title = "";
        if (order.getPayType().equals(PayTypeEnum.BANK_CODE.getKey())) {
            title = "请用进行网银转账付款";
        } else if (order.getPayType().equals(PayTypeEnum.WECHAT_CODE.getKey())) {
            title = "请用微信扫码付款";
        } else if (order.getPayType().equals(PayTypeEnum.APLIPAY_CODE.getKey())) {
            title = "请用支付宝扫码付款";
        }
        model.addAttribute("title",title);
        return  "system/order/payDetail";
    }

    @RequestMapping(value = "/createOrder")
    @ResponseBody
    public Result<Map> createOrder(PaymentInfo paymentInfo) throws Exception {

        if (StringUtils.isEmpty(paymentInfo.getMerchantNo())
                || StringUtils.isEmpty(paymentInfo.getMerchantNo())
                || StringUtils.isEmpty(paymentInfo.getAmount())
                || StringUtils.isEmpty(paymentInfo.getSign())
        ) {
            return Result.error("参数错误");
        }

        // 根据merchantId 获取商户信息
        MerchantDO merchant = merchantService.getByMerchantNo(paymentInfo.getMerchantNo());
        if (merchant == null) {
            return Result.error("商户不对，merchantNo不正确");
        }
        //校验签名
        /*
        boolean signResult = checkSign(merchant.getSecretKey(),paymentInfo);
        if (!signResult) {
            return Result.error("签名不正确");
        }*/

        //判断订单额度
        String amount = paymentInfo.getAmount();
        if (NumberUtil.compare("100", amount) > 0 || NumberUtil.compare("500000", amount) < 0) {
            return Result.error("订单额度在10-5000元之间");
        }
        //创建订单
        String type = paymentInfo.getType();
        PayTypeEnum payTypeEnum = PayTypeEnum.getByKey(type);
        if (payTypeEnum == null) {
            return Result.error("支付方式错误");
        }

        OrderDO order = null;
        if (type.equals(PayTypeEnum.WECHAT_CODE.getKey())) {
            order = orderService.createWechatOrder(paymentInfo);
        } else if (type.equals(PayTypeEnum.APLIPAY_CODE.getKey())) {
            order = orderService.createAlipayOrder(paymentInfo);
        } else if (type.equals(PayTypeEnum.BANK_CODE.getKey())) {
            order = orderService.createBankOrder(paymentInfo);
        }
        try {
            //生成支付返回给商户系统
            MerchantPayInfo merchantPayInfo = generatorPayInfo(order);
            return Result.ok(merchantPayInfo);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

    }

    private boolean checkSign(String secretKey, PaymentInfo paymentInfo) {
        String signstr = "amount="+paymentInfo.getAmount()+"&merchantNo="+paymentInfo.getMerchantNo()+"&type="+paymentInfo.getType()+"&secretKey="+secretKey;
        String tempSign = Encript.md5(signstr);
        String sign  = paymentInfo.getSign();
        return tempSign.equals(sign);
    }

    private MerchantPayInfo generatorPayInfo(OrderDO order) {
        MerchantPayInfo merchantPayInfo = new MerchantPayInfo();
        String payInfoStr = order.getPaymentInfo();
        JSONObject payInfoJSON = JSONObject.parseObject(payInfoStr);
        payInfoJSON.put("amount",order.getAmount());
        payInfoJSON.put("reallyAmount",order.getReallyAmount());
        payInfoJSON.put("type",order.getPayType());
        merchantPayInfo.setPayInfo(payInfoJSON);
        String payApi = (String) redisUtils.get(Constants.ORDER_PAY_URL);
        merchantPayInfo.setPayUrl(payApi + "/" + order.getOrderNo());
        return merchantPayInfo;
    }




}
