package com.bootdo.app.qapple.vo;

import com.bootdo.app.qapple.enums.SignTypeEnum;
import org.springframework.util.StringUtils;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 预下单返回数据
 */
public class PrePayVo {
    /** 该交易在青苹果系统中的交易流水号。最长64位 */
    private String tradeNo;
    /** 商户网站唯一订单号 */
    private String outTradeNo;
    /** 商家账户名称 */
    private String merchantName;
    /** 订单总金额 */
    private BigDecimal orderAmountRmb;
    /** 支付页面跳转地址 */
    private String returnUrl;
    /** 加密码类型 */
    private SignTypeEnum signType;
    /** 参数签名 */
    private String sign;


    public PrePayVo() {
    }

    public PrePayVo(String tradeNo, String outTradeNo, String merchantName,
                    BigDecimal orderAmountRmb, String returnUrl) {
        this.tradeNo = tradeNo;
        this.outTradeNo = outTradeNo;
        this.merchantName = merchantName;
        this.orderAmountRmb = orderAmountRmb;
        this.returnUrl = returnUrl;
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

        if (!StringUtils.isEmpty(merchantName)) {
            sortMap.put("merchantName", merchantName);
        }

        if (!StringUtils.isEmpty(returnUrl)) {
            sortMap.put("returnUrl", returnUrl);
        }

        if (signType != null) {
            sortMap.put("signType", signType.toString());
        }

        return sortMap;
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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public BigDecimal getOrderAmountRmb() {
        return orderAmountRmb;
    }

    public void setOrderAmountRmb(BigDecimal orderAmountRmb) {
        this.orderAmountRmb = orderAmountRmb;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
