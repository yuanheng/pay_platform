package com.bootdo.app.qapple.param;

import com.alibaba.fastjson.JSON;

import org.springframework.util.StringUtils;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 商家预下单请求参数
 */
public class PrePayParam {
    /** 商家订单号 */
    private String outTradeNo;
    /** 订单总金额 */
    private BigDecimal orderAmountRmb;
    /** 商家账户名称 */
    private String merchantName;
    /** 商家会员名称 */
    private String vipName;
    /** 商品的标题/交易标题/订单标题/订单关键字等 */
    private String subject;
    /** 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body */
    private String body;
    /** 回调地址 */
    private String notifyUrl;
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
        if (orderAmountRmb != null) {
            sortMap.put("orderAmountRmb",  String.format("%.2f", orderAmountRmb));
        }
        if (!StringUtils.isEmpty(merchantName)) {
            sortMap.put("merchantName", merchantName);
        }
        if (!StringUtils.isEmpty(vipName)) {
            sortMap.put("vipName", vipName);
        }
        if (!StringUtils.isEmpty(subject)) {
            sortMap.put("subject", subject);
        }
        if (!StringUtils.isEmpty(body)) {
            sortMap.put("body", body);
        }
        if (!StringUtils.isEmpty(notifyUrl)) {
            sortMap.put("notifyUrl", notifyUrl);
        }
        if (signType != null) {
            sortMap.put("signType", signType);
        }
        return sortMap;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public BigDecimal getOrderAmountRmb() {
        return orderAmountRmb;
    }

    public void setOrderAmountRmb(BigDecimal orderAmountRmb) {
        this.orderAmountRmb = orderAmountRmb;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
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

    public Long getOrderAmountRmb10000() {
        return orderAmountRmb.multiply(DDD).longValue();
    }
    public static final BigDecimal DDD = new BigDecimal(10000);

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
