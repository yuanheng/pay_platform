package com.bootdo.app.qapple.param;


import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 商家出款外部卖出参数
 */
public class FastSellParam {
    /**"商家账号*/
    private String merchantName;
    /**青苹果出款订单号*/
    private String tradeNo;

    /**"商家密码"*/
    private String password;

    /**"签名类型:RSA"*/
    private String signType;

    /**"签名参数"*/
    private String sign;

    public FastSellParam() {
    }



    /**
     * 获取签名拼接字符串
     * @return
     */
    public String makeSignJoinStr(){
        Map<String, String> signMap = getSignMap();
        if (signMap.size() < 1) { return "";};
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : signMap.keySet()){
            stringBuilder.append(key + "=" + signMap.get(key) + "&");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }


    private Map<String, String> getSignMap() {
        Map<String, String> sortMap = new TreeMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2); /**用正负表示大小值*/
            }
        });
        if (!StringUtils.isEmpty(merchantName)) {
            sortMap.put("merchantName", merchantName);
        }
        if (!StringUtils.isEmpty(tradeNo)) {
            sortMap.put("tradeNo", tradeNo);
        }
        if (!StringUtils.isEmpty(password)) {
            sortMap.put("password", password);
        }
        if (!StringUtils.isEmpty(signType)) {
            sortMap.put("signType", signType);
        }

        return sortMap;
    };



    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
