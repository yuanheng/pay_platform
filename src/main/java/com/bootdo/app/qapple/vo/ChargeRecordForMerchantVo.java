package com.bootdo.app.qapple.vo;

import com.alibaba.fastjson.JSON;
import com.bootdo.app.qapple.enums.PayStatusEnum;
import com.bootdo.app.qapple.enums.SignTypeEnum;
import org.springframework.util.StringUtils;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 商家支付消息明细
 */
public class ChargeRecordForMerchantVo {
    /**  商户网站唯一订单号,String,必填 */
    private String outTradeNo;
    /** 该交易在青苹果系统中的交易流水号。最长64位,String,必填 */
    private String tradeNo;
    /** 订单总金额，Number,必填，单位为元，精确到小数点后两位 */
    private Double orderAmountRmb;
    /** 商家预计到账的Gac金额(最小0.01),Double,必填 */
    private Double receiveAmountGac;
    /** 用户实际支付的RMB金额(最小0.01),Double,必填 */
    private Double userpayAmountRmb;
    /** 支付状态 String 必填,SUCCESS支付成功,CANCEL:已取消，FAIL:失败 */
    private PayStatusEnum status;
    /** 支付时间戳 */
    private Long payTime;
    /** 商家账户名称,String,必填 */
    private String merchantName;
    /** 商家会员名称,String,必填 */
    private String vipName;
    /** 商品的标题/交易标题/订单标题/订单关键字等 */
    private String subject;
    /** 加密码类型 */
    private SignTypeEnum signType;
    /** 参数签名 */
    private String sign;

    public ChargeRecordForMerchantVo(){
        super();
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
        if (!StringUtils.isEmpty(tradeNo)) {
            sortMap.put("tradeNo", tradeNo);
        }
        if (orderAmountRmb != null) {
            sortMap.put("orderAmountRmb", String.format("%.2f", orderAmountRmb));
        }
        if (receiveAmountGac != null) {
            sortMap.put("receiveAmountGac", String.format("%.2f", receiveAmountGac));
        }
        if (userpayAmountRmb != null) {
            sortMap.put("userpayAmountRmb", String.format("%.2f", userpayAmountRmb));
        }
        if (status != null) {
            sortMap.put("status", status.toString());
        }
        if (payTime != null) {
            sortMap.put("payTime", String.valueOf(payTime));
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
        if (signType != null) {
            sortMap.put("signType", signType.toString());
        }
        return sortMap;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Double getOrderAmountRmb() {
        return orderAmountRmb;
    }

    public void setOrderAmountRmb(Double orderAmountRmb) {
        this.orderAmountRmb = orderAmountRmb;
    }

    public Double getReceiveAmountGac() {
        return receiveAmountGac;
    }

    public void setReceiveAmountGac(Double receiveAmountGac) {
        this.receiveAmountGac = receiveAmountGac;
    }

    public Double getUserpayAmountRmb() {
        return userpayAmountRmb;
    }

    public void setUserpayAmountRmb(Double userpayAmountRmb) {
        this.userpayAmountRmb = userpayAmountRmb;
    }

    public PayStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PayStatusEnum status) {
        this.status = status;
    }

    public Long getPayTime() {
        return payTime;
    }

    public void setPayTime(Long payTime) {
        this.payTime = payTime;
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

    public SignTypeEnum getSignType() {
        return signType;
    }

    public void setSignType(SignTypeEnum signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
