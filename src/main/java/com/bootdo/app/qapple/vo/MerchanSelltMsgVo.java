package com.bootdo.app.qapple.vo;

import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 商家出款回调消息
 */
public class MerchanSelltMsgVo {
    /**
     * 会员名字
     */
    private String memberName;
    /**
     * 商家名称
     */
    private String merchantName;
    /**
     * 订单号
     */
    private String outTradeNo;
    /**
     * 下单时间
     */
    private String payTime;
    /**
     * 签名
     */
    private String sign;
    /**
     * 签名类型
     */
    private String signType;
    /**
     * 状态
     */
    private String status;
    /**
     * 青苹果订单号
     */
    private String tradeNo;


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
        if (!StringUtils.isEmpty(memberName)) {
            sortMap.put("memberName", memberName);
        }
        if (!StringUtils.isEmpty(merchantName)) {
            sortMap.put("merchantName", merchantName);
        }
        if (!StringUtils.isEmpty(outTradeNo)) {
            sortMap.put("outTradeNo", outTradeNo);
        }
        if (!StringUtils.isEmpty(payTime)) {
            sortMap.put("payTime", payTime);
        }
        if (!StringUtils.isEmpty(signType)) {
            sortMap.put("signType", signType);
        }
        if (!StringUtils.isEmpty(status)) {
            sortMap.put("status", status);
        }
        if (!StringUtils.isEmpty(tradeNo)) {
            sortMap.put("tradeNo", tradeNo);
        }
        return sortMap;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    @Override
    public String toString() {
        return "MerchanSelltMsgVo{" +
                "memberName='" + memberName + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", payTime='" + payTime + '\'' +
                ", sign='" + sign + '\'' +
                ", signType='" + signType + '\'' +
                ", status='" + status + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                '}';
    }
}

