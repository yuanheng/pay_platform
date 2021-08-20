package com.bootdo.app.qapple.param;


import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 商家冲值状态查询参数
 */
public class PrePayQryForMerchantParam {
    /** 商家账户名称 */
    private String merchantName;
    /** 商家订单号 */
    private String outTradeNo;

    /** 签名类型:RSA */
    private String signType;
    /** 签名参数 */
    private String sign;


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

    private Map<String, String> getSignMap(){
        Map<String, String> sortMap = new TreeMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2); //用正负表示大小值
            }
        });
        if (!StringUtils.isEmpty(outTradeNo)) {
            sortMap.put("outTradeNo", outTradeNo);
        }
        if (!StringUtils.isEmpty(getMerchantName())) {
            sortMap.put("merchantName", getMerchantName());
        }
        if (signType != null) {
            sortMap.put("signType", signType);
        }
        return sortMap;
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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
