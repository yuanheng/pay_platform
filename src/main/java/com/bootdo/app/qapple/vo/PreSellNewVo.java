package com.bootdo.app.qapple.vo;

import com.bootdo.app.qapple.enums.SignTypeEnum;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 商家出款预下单返回vo
 */
public class PreSellNewVo {

    //该交易在青苹果系统中的交易流水号。最长64位
    private String tradeNo;

    //出款金额(人民币)
    private BigDecimal amountRmb;

    //出款数量(GAC)
    private BigDecimal amountGac;

    //当前账户余额(GAC)
    private BigDecimal curAmountGac;

    //出款手续费(GAC)
    private BigDecimal fundCharge;

    //出款后账户余额(GAC)
    private BigDecimal remainAmountGac;

    //商家账号
    private String merchantName;

    //商家订单号
    private String outTradeNo;

    //加密码类型
    private SignTypeEnum signType;
    //参数签名
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



    private Map<String, String> getSignMap() {
        Map<String, String> sortMap = new TreeMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2); //用正负表示大小值
            }
        });
        if (!StringUtils.isEmpty(tradeNo)) {
            sortMap.put("tradeNo", tradeNo);
        }
        if (amountRmb != null) {
            sortMap.put("amountRmb", String.format("%.2f", amountRmb));
        }
        if (amountGac != null) {
            sortMap.put("amountGac", String.format("%.2f", amountGac));
        }
        if (curAmountGac != null) {
            sortMap.put("curAmountGac", String.format("%.2f", curAmountGac));
        }
        if (fundCharge != null) {
            sortMap.put("fundCharge", String.format("%.2f", fundCharge));
        }
        if (remainAmountGac != null) {
            sortMap.put("remainAmountGac", String.format("%.2f", remainAmountGac));
        }
        if (!StringUtils.isEmpty(merchantName)) {
            sortMap.put("merchantName", merchantName);
        }
        if (!StringUtils.isEmpty(outTradeNo)) {
            sortMap.put("outTradeNo", outTradeNo);
        }
        if (!StringUtils.isEmpty(signType)) {
            sortMap.put("signType", String.valueOf(signType));
        }
        return sortMap;
    }

    public PreSellNewVo() {
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public BigDecimal getAmountRmb() {
        return amountRmb;
    }

    public void setAmountRmb(BigDecimal amountRmb) {
        this.amountRmb = amountRmb;
    }

    public BigDecimal getAmountGac() {
        return amountGac;
    }

    public void setAmountGac(BigDecimal amountGac) {
        this.amountGac = amountGac;
    }

    public BigDecimal getCurAmountGac() {
        return curAmountGac;
    }

    public void setCurAmountGac(BigDecimal curAmountGac) {
        this.curAmountGac = curAmountGac;
    }

    public BigDecimal getFundCharge() {
        return fundCharge;
    }

    public void setFundCharge(BigDecimal fundCharge) {
        this.fundCharge = fundCharge;
    }

    public BigDecimal getRemainAmountGac() {
        return remainAmountGac;
    }

    public void setRemainAmountGac(BigDecimal remainAmountGac) {
        this.remainAmountGac = remainAmountGac;
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
}
