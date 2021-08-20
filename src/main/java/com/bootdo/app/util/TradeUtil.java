package com.bootdo.app.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TradeUtil {

    /**
     * 参数
     * @param orderNo       外部订单号
     * @param customerName  用户名
     * @param accountType   法币类型,固定传CNY
     * @param account       金额
     * @param currencyType   货币类型:usdt
     * @param returnUrl 同步回调地址
     * @param notifyUrl 异步回调地址
     * @param signType 签名类型:MD5
     * @param appId  用户appId(个人资料中查看)
     * @return
     */
    public static Map<String,String> params(String orderNo, String customerName,String customerId, String accountType, BigDecimal account, String
            currencyType, String returnUrl, String notifyUrl, String signType,  String appId ,String type){
        String sign = TradeUtil.sign(orderNo, customerName,customerId, accountType, account,currencyType, returnUrl, notifyUrl, signType, appId,type);
        Map<String,String> params = new HashMap<String, String>();
        params.put("appId", appId);
        params.put("orderNo", orderNo);
        params.put("customerName", customerName);
        params.put("customerId", customerId);
        params.put("accountType", accountType);
        params.put("account", account+"");
        params.put("currencyType", currencyType);
        params.put("returnUrl", returnUrl);
        params.put("notifyUrl", notifyUrl);
        params.put("signType", signType);
        params.put("type", type);
        params.put("sign", sign);
        return params;
    }

    /**
     * 签名
     * @param orderNo
     * @param customerName
     * @param accountType
     * @param account
     * @param returnUrl
     * @param notifyUrl
     * @param signType
     * @param appId
     * @return
     */
    public static String sign(String orderNo, String customerName,String customerId, String accountType, BigDecimal account, String currencyType, String returnUrl,
                              String notifyUrl, String signType, String appId,String type){
        String signstr = "APPID=" + appId + "&ACCOUNT=" + account + "&ACCOUNT_TYPE="  + accountType+"&CURRENCY_TYPE="+currencyType +  "&CUSTOMER_NAME=" +
                customerName + "&CUSTOMER_ID=" + customerId +"&NOTIFY_URL=" + notifyUrl + "&ORDER_NO=" + orderNo + "&RETURN_URL=" + returnUrl + "&SIGN_TYPE=" +
                signType + "&TYPE=" + type +"&PRICATE_KEY=" + UPayConfig.private_key;
        return Encript.md5(signstr);
    }

    public static boolean validate(String orderNo, BigDecimal account, String  accountType,String currencyType, String signType, String sign,
                                  String transactionId, String status){

        String signstr = "ACCOUNT=" + account + "&ACCOUNT_TYPE="  + accountType +"&CURRENCY_TYPE="+currencyType+  "&ORDER_NO=" + orderNo + "&SIGN_TYPE=" +
                signType + "&STATUS=" + status + "&TRANSACTIONID=" + transactionId + "&PRICATE_KEY=" + UPayConfig.private_key;

        return sign.equals(Encript.md5(signstr));
    }

}
