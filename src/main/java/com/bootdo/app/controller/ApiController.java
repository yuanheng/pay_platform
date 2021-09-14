package com.bootdo.app.controller;

import cn.hutool.db.sql.Order;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.app.config.Constants;
import com.bootdo.app.model.MerchantPayInfo;
import com.bootdo.app.model.PaymentInfo;
import com.bootdo.app.model.Result;
import com.bootdo.app.model.StatisticsInfo;
import com.bootdo.app.util.DistributedLock;
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
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
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
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private DistributedLock distributedLock;

    private static Logger logger = LoggerFactory.getLogger(ApiController.class);


    @GetMapping("/pay/{orderNo}")
    String add(@PathVariable("orderNo") String orderNo, Model model) {
        String orderKey = Constants.getOrderKey(orderNo);
        String returnVM = "payDetail";
        try {
            OrderDO order = (OrderDO) redisUtils.get(orderKey);
            String payInfo = order.getPaymentInfo();
            Integer second = (Integer) redisUtils.get(Constants.ORDER_TIMER_KEY);
            Integer minute = second / 60;
            model.addAttribute("minute", minute);
            model.addAttribute("order", order);
            String reallyAmount = order.getReallyAmount();
            model.addAttribute("reallyAmount", NumberUtil.divide(reallyAmount, "100"));
            Date createTime = order.getCreateTime();
            long createTimeMillions = createTime.getTime();
            long currentTime = System.currentTimeMillis();
            long timer = (currentTime - createTimeMillions) / 1000;
            timer = second - timer;
            if (timer > 0) {
                model.addAttribute("second", timer);
            } else {
                model.addAttribute("second", 0);
            }
            String title = "";
            if (order.getPayType().equals(PayTypeEnum.BANK_CODE.getKey())) {
                title = "请用网银转账付款";
            } else if (order.getPayType().equals(PayTypeEnum.WECHAT_CODE.getKey())) {
                title = "请用微信扫码付款";
                returnVM = "payWXDetail";
            }  else  if (order.getPayType().equals(PayTypeEnum.APLIPAY_CODE.getKey()) || order.getPayType().equals(PayTypeEnum.TABO_CODE.getKey())) {
                JSONObject payInfoJSON = JSONObject.parseObject(payInfo);
                model.addAttribute("payInfo", payInfoJSON);
                String url = payInfoJSON.getString("url");
                String bizNo  = getPamras(url,"biz_no");
                String[] temps = bizNo.split("_");
                model.addAttribute("bizNo", temps[0]);
                returnVM = "tbPayDetail";
            }
            model.addAttribute("title", title);
            model.addAttribute("flag", "success");
        } catch (Exception e) {
            logger.error("支付信息已失效");
            model.addAttribute("flag", "error");
            model.addAttribute("error", "支付信息已失效");
        }

        return "system/order/" + returnVM;
    }

    @RequestMapping(value = "/createOrder")
    @ResponseBody
    public Result<Map> createOrder(PaymentInfo paymentInfo) throws Exception {

        if (StringUtils.isEmpty(paymentInfo.getMerchantNo())
                || StringUtils.isEmpty(paymentInfo.getMerchantOrderNo())
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

        boolean signResult = checkSign(merchant.getSecretKey(), paymentInfo);
        if (!signResult) {
            return Result.error("签名不正确");
        }

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

        try {
            OrderDO order = null;
            if (type.equals(PayTypeEnum.WECHAT_CODE.getKey())) {
                order = orderService.createWechatOrder(paymentInfo);
            } else if (type.equals(PayTypeEnum.BANK_CODE.getKey())) {
                order = orderService.createBankOrder(paymentInfo);
            } else if (type.equals(PayTypeEnum.APLIPAY_CODE.getKey()) || type.equals(PayTypeEnum.TABO_CODE.getKey())) {
                order = orderService.createTBOrder(paymentInfo);
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

    @RequestMapping(value = "/createAlipayOrder")
    public String createAlipayOrder(String amount, Model model) throws Exception {

        String returnVM = "payDetail";

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount(amount);

        if (StringUtils.isEmpty(paymentInfo.getAmount())) {
            amount = "50000";
        }

        paymentInfo.setMerchantNo("1000001");

        //判断订单额度
        if (NumberUtil.compare("100", amount) > 0 || NumberUtil.compare("5000001", amount) < 0) {
            amount = "50000";
        }

        OrderDO order = null;
        try {
            paymentInfo.setType(PayTypeEnum.APLIPAY_CODE.getKey());
            order = orderService.createAlipayOrder(paymentInfo);
            Integer second = (Integer) redisUtils.get(Constants.ORDER_TIMER_KEY);
            redisUtils.set(Constants.getOrderKey(order.getOrderNo()), order, second);
            String payInfo = order.getPaymentInfo();
            JSONObject payInfoJSON = JSONObject.parseObject(payInfo);
            String reallyAmount = order.getReallyAmount();
            model.addAttribute("reallyAmount", NumberUtil.divide(reallyAmount, "100"));
            model.addAttribute("payInfo", payInfoJSON);
            model.addAttribute("minute", second / 60);
            model.addAttribute("order", order);
            Date createTime = order.getCreateTime();
            long createTimeMillions = createTime.getTime();
            long currentTime = System.currentTimeMillis();
            long timer = (currentTime - createTimeMillions) / 1000;
            timer = second - timer;
            if (timer > 0) {
                model.addAttribute("second", timer);
            } else {
                model.addAttribute("second", 0);
            }
            String title = "";
            if (order.getPayType().equals(PayTypeEnum.BANK_CODE.getKey())) {
                title = "请用网银转账付款";
            } else if (order.getPayType().equals(PayTypeEnum.WECHAT_CODE.getKey())) {
                title = "请用微信扫码付款";
                returnVM = "payWXDetail";
            } else if (order.getPayType().equals(PayTypeEnum.APLIPAY_CODE.getKey())) {
                title = "请用支付宝扫码付款";
            }
            model.addAttribute("title", title);
            model.addAttribute("flag", "success");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("支付信息已失效");
            model.addAttribute("flag", "error");
            model.addAttribute("error", "支付信息已失效");
        }
        return "system/order/" + returnVM;
    }

    @RequestMapping(value = "/createWechatOrder")
    public String createWechatOrder(String amount, Model model) throws Exception {

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setAmount(amount);

        if (StringUtils.isEmpty(paymentInfo.getAmount())) {
            amount = "50000";
        }

        paymentInfo.setMerchantNo("1000001");
        // 根据merchantId 获取商户信息
        MerchantDO merchant = merchantService.getByMerchantNo(paymentInfo.getMerchantNo());


        //判断订单额度
        if (NumberUtil.compare("100", amount) > 0 || NumberUtil.compare("5000001", amount) < 0) {
            amount = "50000";
        }
        //创建订单
        String type = "wechat";
        PayTypeEnum payTypeEnum = PayTypeEnum.getByKey(type);
        OrderDO order = null;
        try {
            paymentInfo.setType(PayTypeEnum.WECHAT_CODE.getKey());
            order = orderService.createWechatOrder(paymentInfo);
            Integer second = (Integer) redisUtils.get(Constants.ORDER_TIMER_KEY);
            redisUtils.set(Constants.getOrderKey(order.getOrderNo()), order, second);

            String payInfo = order.getPaymentInfo();
            JSONObject payInfoJSON = JSONObject.parseObject(payInfo);
            String reallyAmount = order.getReallyAmount();
            model.addAttribute("reallyAmount", NumberUtil.divide(reallyAmount, "100"));
            model.addAttribute("payInfo", payInfoJSON);
            model.addAttribute("minute", "10");
            model.addAttribute("order", order);
            Date createTime = order.getCreateTime();
            long createTimeMillions = createTime.getTime();
            long currentTime = System.currentTimeMillis();
            long timer = (currentTime - createTimeMillions) / 1000;
            timer = second - timer;
            if (timer > 0) {
                model.addAttribute("second", timer);
            } else {
                model.addAttribute("second", 0);
            }
            String title = "";
            if (order.getPayType().equals(PayTypeEnum.BANK_CODE.getKey())) {
                title = "请用网银转账付款";
            } else if (order.getPayType().equals(PayTypeEnum.WECHAT_CODE.getKey())) {
                title = "请用微信扫码付款";
            } else if (order.getPayType().equals(PayTypeEnum.APLIPAY_CODE.getKey())) {
                title = "请用支付宝扫码付款";
            }
            model.addAttribute("title", title);
            model.addAttribute("flag", "success");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("支付信息已失效");
            model.addAttribute("flag", "error");
            model.addAttribute("error", "支付信息已失效");
        }
        return "system/order/payDetail";
    }

    private boolean checkSign(String secretKey, PaymentInfo paymentInfo) {
        String signstr = "amount=" + paymentInfo.getAmount() + "&merchantNo=" + paymentInfo.getMerchantNo() + "&merchantOrderNo=" + paymentInfo.getMerchantOrderNo() + "&type=" + paymentInfo.getType() + "&secretKey=" + secretKey;
        String tempSign = Encript.md5(signstr);
        String sign = paymentInfo.getSign();
        return tempSign.equalsIgnoreCase(sign);
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

    @RequestMapping(value = "/callback")
    @ResponseBody
    public String createOrder(String sign, String merchantNo, String amount, String merchantOrderNo) throws Exception {
        logger.info("callbak success sign={},merchantNo={},amount={},merchantOrderNo={}", sign, merchantNo, amount, merchantOrderNo, merchantOrderNo);
        return "OK";
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


    private String getPamras(String url, String key){
        Map<String,String> params = getParameter(url);
        return params.get(key);
    }

    private Map<String, String> getParameter(String url) {
        Map<String, String> map = new HashMap<>();
        try {
            final String charset = "utf-8";
            url = URLDecoder.decode(url, charset);
            if (url.indexOf('?') != -1) {
                final String contents = url.substring(url.indexOf('?') + 1);
                String[] keyValues = contents.split("&");
                for (int i = 0; i < keyValues.length; i++) {
                    String key = keyValues[i].substring(0, keyValues[i].indexOf("="));
                    String value = keyValues[i].substring(keyValues[i].indexOf("=") + 1);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
