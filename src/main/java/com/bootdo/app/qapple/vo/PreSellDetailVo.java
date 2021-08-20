package com.bootdo.app.qapple.vo;

import com.bootdo.app.qapple.enums.SignTypeEnum;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class PreSellDetailVo {
    //该交易在青苹果系统中的交易流水号。最长64位
    private String tradeNo;

    //出款金额GAC数量
    private BigDecimal amountGac;

    //出款金额rmb
    private BigDecimal amountRmb;

    //手续费
    private BigDecimal fundCharge;

    //商家账号
    private String merchantName;

    //外部商家订单号
    private String outTradeNo;

    //下单时间
    private String applyTime;

    //支付状态更新时间
    private String payTime;

    //支付状态=\"1:\"创建\",\"2\":\"待付款\",\"3\":\"已付款\",\"4\":\"已完成\",\"201\":\"付款失败\",\"202\":\"取消付款\"
    private Integer payStatus;

    //用户选择的支付卡类型=={\"BANK_CARD\":\"银行卡\"}
    private String cardType;

    //会员名
    private String memberName;

    //承兑人
    private String accepterName;

    //银行账户名称-收款人
    private String payee;

    //卡号
    private String cardNo;

    //总行名称
    private String bankName;

    //分行名称
    private String branchName;

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
        if (amountGac != null) {
            sortMap.put("amountGac", String.format("%.2f", amountGac));
        }
        if (amountRmb != null) {
            sortMap.put("amountRmb", String.format("%.2f", amountRmb));
        }
        if (fundCharge != null) {
            sortMap.put("fundCharge", String.format("%.2f", fundCharge));
        }
        if (!StringUtils.isEmpty(merchantName)) {
            sortMap.put("merchantName", merchantName);
        }
        if (!StringUtils.isEmpty(outTradeNo)) {
            sortMap.put("outTradeNo", outTradeNo);
        }
        if (!StringUtils.isEmpty(applyTime)) {
            sortMap.put("applyTime", applyTime);
        }
        if (!StringUtils.isEmpty(payTime)) {
            sortMap.put("payTime", payTime);
        }
        if (payStatus != null) {
            sortMap.put("payStatus", payStatus.toString());
        }
        if (!StringUtils.isEmpty(cardType)) {
            sortMap.put("cardType", cardType);
        }
        if (!StringUtils.isEmpty(memberName)) {
            sortMap.put("memberName", memberName);
        }
        if (!StringUtils.isEmpty(accepterName)) {
            sortMap.put("accepterName", accepterName);
        }
        if (!StringUtils.isEmpty(payee)) {
            sortMap.put("payee", payee);
        }
        if (!StringUtils.isEmpty(cardNo)) {
            sortMap.put("cardNo", cardNo);
        }
        if (!StringUtils.isEmpty(bankName)) {
            sortMap.put("bankName", bankName);
        }
        if (!StringUtils.isEmpty(branchName)) {
            sortMap.put("branchName", branchName);
        }
        if (signType != null) {
            sortMap.put("signType", signType.toString());
        }

        return sortMap;
    }



    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getAccepterName() {
        return accepterName;
    }

    public void setAccepterName(String accepterName) {
        this.accepterName = accepterName;
    }

    public PreSellDetailVo() {
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public BigDecimal getAmountGac() {
        return amountGac;
    }

    public void setAmountGac(BigDecimal amountGac) {
        this.amountGac = amountGac;
    }

    public BigDecimal getAmountRmb() {
        return amountRmb;
    }

    public void setAmountRmb(BigDecimal amountRmb) {
        this.amountRmb = amountRmb;
    }

    public BigDecimal getFundCharge() {
        return fundCharge;
    }

    public void setFundCharge(BigDecimal fundCharge) {
        this.fundCharge = fundCharge;
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

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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
