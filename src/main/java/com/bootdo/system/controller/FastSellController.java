package com.bootdo.system.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import com.bootdo.app.qapple.enums.ReponseCode;
import com.bootdo.app.qapple.param.FastSellParam;
import com.bootdo.app.qapple.param.PrePayQryForMerchantParam;
import com.bootdo.app.qapple.param.PreSellParam;
import com.bootdo.app.qapple.util.HttpClient;
import com.bootdo.app.qapple.util.RsaUtil;
import com.bootdo.app.qapple.vo.BaseResponse;
import com.bootdo.app.qapple.vo.MerchanSelltMsgVo;
import com.bootdo.app.qapple.vo.PreSellDetailVo;
import com.bootdo.app.qapple.vo.PreSellNewVo;
import com.bootdo.app.qapple.vo.ResponseData;
import com.bootdo.app.util.AmountUtil;
import com.bootdo.app.zwlenum.PayStatusEnum;
import com.bootdo.app.zwlenum.TradeTypeEnum;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.MemberDO;
import com.bootdo.system.domain.TradeLogDO;
import com.bootdo.system.service.MemberService;
import com.bootdo.system.service.TradeLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("zwl")
public class FastSellController {

    private static Logger logger = LoggerFactory.getLogger(FastSellController.class);

    //商家账号名
    @Value("${MERCHANT_NAME}")
    private String MERCHANT_NAME;
    //商家用户merchant1私钥
    @Value("${MERCHANT_PRIVATE_KEY}")
    private String MERCHANT_PRIVATE_KEY;
    //用青苹果平台公钥
    @Value("${QAPPLE_PUBLIC_KEY}")
    private String QAPPLE_PUBLIC_KEY;
    //青苹果支付服务器地址
    @Value("${QAPPLE_SERVER_URL}")
    private  String QAPPLE_SERVER_URL;

    @Value("${sell_nofity_url}")
    private String notifyUrl;

    @Autowired
    private MemberService memberService;

    @Autowired
    private TradeLogService tradeLogService;

    /**
     * 预出款下单
     */
    @GetMapping("/preSell")
    public R preSell(Integer tid) {

        TradeLogDO tradeLogDO = tradeLogService.get(tid);
        if(tradeLogDO == null || !tradeLogDO.getType().equals(TradeTypeEnum.WITHDRAW.toString())){
            return R.error();
        }
        MemberDO memberDO = memberService.get(tradeLogDO.getMid());
        String username = memberDO.getUsername();
        String tempName = username.substring(4);

        try {
            /** 预下单测试地址  */
            String prePayUrl = QAPPLE_SERVER_URL + "/merchant/merchantcenter/fastSell/preSell";
            PreSellParam preSellParam = new PreSellParam();
            preSellParam.setAmountRmb(new BigDecimal(AmountUtil.changeF2Y(tradeLogDO.getFactAmount()))); //款金额
            preSellParam.setMerchantName(MERCHANT_NAME); // 商家账号
            preSellParam.setOutTradeNo(tradeLogDO.getTradeNo());   //订单号

            preSellParam.setNotifyUrl(notifyUrl);
            preSellParam.setMemberName(tempName);   //会员名称
            preSellParam.setCardType("BANK_CARD");     //收款卡类型
            preSellParam.setPayee(memberDO.getReallyName());        //银行卡账户类型
            preSellParam.setCardNo(memberDO.getBankNo());        //卡号
            preSellParam.setBankName(memberDO.getBankName());      //总行名称
            preSellParam.setBranchName(memberDO.getBankBranchName());    //分行名称
            preSellParam.setSignType("RSA");      //签名类型®
            preSellParam.setSign("");           //签名参数
            preSellParam.setSign(RsaUtil.sign(preSellParam.makeSignJoinStr(), MERCHANT_PRIVATE_KEY)); //签名
            //发起请求
            TypeReference<ResponseData<PreSellNewVo>> responseType = new TypeReference<ResponseData<PreSellNewVo>>() {};
            String result = HttpClient.doPost((JSONObject) JSON.toJSON(preSellParam), prePayUrl);
            ResponseData<PreSellNewVo> responseData = JSON.parseObject(result, responseType);

            //判断请求响应是否成功
            if (!responseData.getCode().equals(ReponseCode.SUCCESS.getStatus())) {
                logger.error(String.format("请求失败 错误码%s,错误信息%s", responseData.getCode(), responseData.getMessage()));
                logger.error(("调试信息：" + responseData.getMsgDebug() + "，跟踪号:" + responseData.getTransNo()));
                return R.error("出款失败！");
            }
            PreSellNewVo preSellNewVo = responseData.getData();
            //用青苹果平台公钥验签
            boolean checkSign = RsaUtil.doCheck(preSellNewVo.makeSignJoinStr(), preSellNewVo.getSign(), QAPPLE_PUBLIC_KEY);
            if (checkSign) {
                String tradeOutNo = preSellNewVo.getTradeNo();
                tradeLogDO.setTradeOutNo(tradeOutNo);
                tradeLogService.update(tradeLogDO);
                logger.info("验签成功！"+tradeLogDO.getTradeNo()+notifyUrl);
            } else {
                logger.error("验签失败！"+tradeLogDO.getTradeNo());
                return R.error("出款失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("出款失败！"+tradeLogDO.getTradeNo());
            //处理异常
            return R.error("出款失败！");
        }

        return R.ok();

    }

    /**
     * 预出款查询
     * @return
     */
    @GetMapping("/queryOrderByOutTradeNo")
    public R queryOrderByOutTradeNo(Integer tid) {

        TradeLogDO tradeLogDO = tradeLogService.get(tid);
        if(tradeLogDO == null || !tradeLogDO.getType().equals(TradeTypeEnum.WITHDRAW.toString())){
            return R.error();
        }

        try {
            /** 预下单查询地址  */
            String prePayUrl = QAPPLE_SERVER_URL + "/merchant/merchantcenter/fastSell/qryPreSellByOutTradeNo";
            PrePayQryForMerchantParam prePayQryForMerchantParam = new PrePayQryForMerchantParam();
            prePayQryForMerchantParam.setMerchantName(MERCHANT_NAME); //商家账户名
            prePayQryForMerchantParam.setOutTradeNo(tradeLogDO.getTradeNo());   //商家订单号
            prePayQryForMerchantParam.setSignType("RSA");     //签名类型
            prePayQryForMerchantParam.setSign(RsaUtil.sign(prePayQryForMerchantParam.makeSignJoinStr(),MERCHANT_PRIVATE_KEY));

            //发起请求
            TypeReference<ResponseData<PreSellDetailVo>> responseType = new TypeReference<ResponseData<PreSellDetailVo>>() {
            };
            String result = HttpClient.doPost((JSONObject) JSON.toJSON(prePayQryForMerchantParam), prePayUrl);
            ResponseData<PreSellDetailVo> responseData = JSON.parseObject(result, responseType);

            //判断请求响应是否成功
            if (!responseData.getCode().equals(ReponseCode.SUCCESS.getStatus())) {
                System.out.println(String.format("请求失败 错误码%s,错误信息%s", responseData.getCode(), responseData.getMessage()));
                System.out.println("调试信息：" + responseData.getMsgDebug() + "，跟踪号:" + responseData.getTransNo());
                return R.error();
            }
            PreSellDetailVo preSellDetailVo = responseData.getData();

            //用青苹果平台公钥验签
            boolean checkSign = RsaUtil.doCheck(preSellDetailVo.makeSignJoinStr(), preSellDetailVo.getSign(), QAPPLE_PUBLIC_KEY);
            if (checkSign) {
                System.out.println("验签成功！");
                System.out.println(preSellDetailVo.toString());




            } else {
                System.out.println("验签失败！");
                return R.error();
            }


        } catch (Exception e) {
            e.printStackTrace();
            //处理异常
            return R.error();
        }
        return R.ok();
    }

    /**
     * 确认出款
     * @return
     */
    @PostMapping("/confirmSell")
    public R sell(Integer tid) {
        TradeLogDO tradeLogDO = tradeLogService.get(tid);
        try {
            /** 商家确认出款地址  */
            String prePayUrl = QAPPLE_SERVER_URL + "/merchant/merchantcenter/fastSell/confirmSell";
            FastSellParam fastSellParam = new FastSellParam();
            fastSellParam.setMerchantName(MERCHANT_NAME);  // 商户账户
            fastSellParam.setTradeNo(tradeLogDO.getTradeOutNo());       //青苹果出款订单号
            fastSellParam.setPassword("5J5FrYn3XgDWJM7yqbkgFwhXL7KejXWug9aKJzu9WN9jB6ENEUt");       // 商家密码
            fastSellParam.setSignType("RSA");       //签名类型
            fastSellParam.setSign(RsaUtil.sign(fastSellParam.makeSignJoinStr(),MERCHANT_PRIVATE_KEY));
            //发起请求
            TypeReference<ResponseData<PreSellDetailVo>> responseType = new TypeReference<ResponseData<PreSellDetailVo>>() {
            };
            String result = HttpClient.doPost((JSONObject) JSON.toJSON(fastSellParam), prePayUrl);
            ResponseData<PreSellDetailVo> responseData = JSON.parseObject(result, responseType);

            if (!responseData.getCode().equals(ReponseCode.SUCCESS.getStatus())) {
                System.out.println(String.format("请求失败 错误码%s,错误信息%s", responseData.getCode(), responseData.getMessage()));
                System.out.println("调试信息：" + responseData.getMsgDebug() + "，跟踪号:" + responseData.getTransNo());

                return R.error();
            }
            PreSellDetailVo preSellDetailVo = responseData.getData();
            //用青苹果平台公钥验签
            boolean checkSign = RsaUtil.doCheck(preSellDetailVo.makeSignJoinStr(), preSellDetailVo.getSign(), QAPPLE_PUBLIC_KEY);
            if (checkSign) {
                System.out.println("确认成功！");
                System.out.println(preSellDetailVo.toString());
            } else {
                System.out.println("确认失败！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            //处理异常
            return R.error();
        }

        return R.ok();

    }

    /**
     * 确认收款
     * @return
     */
    @PostMapping("/dischargedSell")
    public String dischargedSell() {

        try {
            String prePayUrl = QAPPLE_SERVER_URL + "/merchant/merchantcenter/fastSell/dischargedSell";
            FastSellParam fastSellParam = new FastSellParam();
            fastSellParam.setMerchantName(MERCHANT_NAME);  // 商户账户
            fastSellParam.setTradeNo("19042416062827053");       //青苹果出款订单号
            fastSellParam.setPassword("e10adc3949ba59abbe56e057f20f883e");       // 商家密码
            fastSellParam.setSignType("RSA");       //签名类型
            fastSellParam.setSign(RsaUtil.sign(fastSellParam.makeSignJoinStr(),MERCHANT_PRIVATE_KEY));
            //发起请求
            TypeReference<ResponseData<PreSellDetailVo>> responseType = new TypeReference<ResponseData<PreSellDetailVo>>() {
            };
            String result = HttpClient.doPost((JSONObject) JSON.toJSON(fastSellParam), prePayUrl);
            ResponseData<PreSellDetailVo> responseData = JSON.parseObject(result, responseType);

            if (!responseData.getCode().equals(ReponseCode.SUCCESS.getStatus())) {
                System.out.println(String.format("请求失败 错误码%s,错误信息%s", responseData.getCode(), responseData.getMessage()));
                System.out.println("调试信息：" + responseData.getMsgDebug() + "，跟踪号:" + responseData.getTransNo());
                return "查询失败";
            }
            PreSellDetailVo preSellDetailVo = responseData.getData();
            //用青苹果平台公钥验签
            boolean checkSign = RsaUtil.doCheck(preSellDetailVo.makeSignJoinStr(), preSellDetailVo.getSign(), QAPPLE_PUBLIC_KEY);
            if (checkSign) {
                System.out.println("确认成功！");
            } else {
                System.out.println("确认失败！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            //处理异常
            return "确认失败!";
        }
        return "确认成功";

    }



    /**
     * 审核不通过
     * @return
     */
    @PostMapping("/auditFail")
    public String auditFail() {

        try {
            String prePayUrl = QAPPLE_SERVER_URL + "/merchant/merchantcenter/fastSell/auditFail";
            FastSellParam fastSellParam = new FastSellParam();
            fastSellParam.setMerchantName(MERCHANT_NAME);  // 商户账户
            fastSellParam.setTradeNo("19042416062827053");       //青苹果出款订单号
            fastSellParam.setPassword("e10adc3949ba59abbe56e057f20f883e");       // 商家密码
            fastSellParam.setSignType("RSA");       //签名类型
            fastSellParam.setSign(RsaUtil.sign(fastSellParam.makeSignJoinStr(),MERCHANT_PRIVATE_KEY));
            //发起请求
            TypeReference<ResponseData<PreSellDetailVo>> responseType = new TypeReference<ResponseData<PreSellDetailVo>>() {
            };
            String result = HttpClient.doPost((JSONObject) JSON.toJSON(fastSellParam), prePayUrl);
            ResponseData<PreSellDetailVo> responseData = JSON.parseObject(result, responseType);

            if (!responseData.getCode().equals(ReponseCode.SUCCESS.getStatus())) {
                System.out.println(String.format("请求失败 错误码%s,错误信息%s", responseData.getCode(), responseData.getMessage()));
                System.out.println("调试信息：" + responseData.getMsgDebug() + "，跟踪号:" + responseData.getTransNo());
                return "查询失败";
            }
            PreSellDetailVo preSellDetailVo = responseData.getData();
            //用青苹果平台公钥验签
            boolean checkSign = RsaUtil.doCheck(preSellDetailVo.makeSignJoinStr(), preSellDetailVo.getSign(), QAPPLE_PUBLIC_KEY);
            if (checkSign) {
                System.out.println("确认成功！");
            } else {
                System.out.println("确认失败！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            //处理异常
            return "确认失败!";
        }
        return "确认成功";

    }

    /**
     * 商家支付消息结果通知
     * @param merchanSelltMsgVo
     * @return
     */
    @RequestMapping(path = "/nofitfy",method = RequestMethod.POST)
    public BaseResponse receiveMessage(@RequestBody MerchanSelltMsgVo merchanSelltMsgVo){
        logger.info("nofitfy receive merchant message: " + JSON.toJSONString(merchanSelltMsgVo));
        //用青苹果平台公钥验签
        logger.info("商家支付消息结果通知");
        boolean checkSign = RsaUtil.doCheck( merchanSelltMsgVo.makeSignJoinStr(), merchanSelltMsgVo.getSign(), QAPPLE_PUBLIC_KEY);

        if (!checkSign){
            logger.info("验签失败！");
            BaseResponse baseResponse = new BaseResponse(500, "操作错误");
            return baseResponse;
        }

        if("SUCCESS".equals(merchanSelltMsgVo.getStatus())){

            String tradeNo = merchanSelltMsgVo.getOutTradeNo();
            logger.info("代付款成功：tradeNo:"+tradeNo);
            TradeLogDO tradeLogDO = tradeLogService.getTradeLogByNo(tradeNo);
            tradeLogDO.setFinishedTime(new Date());
            tradeLogDO.setStatus(PayStatusEnum.FINISHED.toString());
            tradeLogService.update(tradeLogDO);
            //同时更新用户的
            BaseResponse baseResponse = new BaseResponse(200, "操作成功");
            return baseResponse;
        }

        BaseResponse baseResponse = new BaseResponse(500, "失败");
        return baseResponse;
    }


    public static void main(String[] args) {
        String username="13070104069";
        System.out.println(username.substring(4));
    }
}
