package com.bootdo.app.qapple.param;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 商家出款预下单参数
 */
public class PreSellParam {

    /**
     * 出款金额(单位:人民币)
     */
    private BigDecimal amountRmb;

    /**
     * 商家账号
     */
    private String merchantName;

    /**
     * 商家订单号
     */
    private String outTradeNo;

    /**
     * 会员名称
     */
    private String memberName;

    /**
     * 收款卡类型=={\"BANK_CARD\":\"银行卡\"}
     */
    private String cardType;

    /**
     * 银行账户名称-收款人
     */
    private String payee;

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 总行名称
     */
    private String bankName;

    /**
     * 分行名称
     */
    private String branchName;

    /**
     * 签名类型:RSA
     */
    private String signType;
    /**
     * 签名参数
     */
    private String sign;

    /**
     * 回调地址
     */
    private String notifyUrl;

    public PreSellParam() {
    }

    /**
     * 获取签名拼接字符串
     *
     * @return
     */
    public String makeSignJoinStr() {
        Map<String, String> signMap = getSignMap();
        if (signMap.size() < 1) {
            return "";
        }
        ;
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : signMap.keySet()) {
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
        if (amountRmb != null) {
            sortMap.put("amountRmb", String.format("%.2f", amountRmb));
        }
        if (!StringUtils.isEmpty(merchantName)) {
            sortMap.put("merchantName", merchantName);
        }
        if (!StringUtils.isEmpty(outTradeNo)) {
            sortMap.put("outTradeNo", outTradeNo);

        }
        if (!StringUtils.isEmpty(memberName)) {
            sortMap.put("memberName", memberName);

        }
        if (!StringUtils.isEmpty(cardType)) {
            sortMap.put("cardType", cardType);

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
        if (!StringUtils.isEmpty(signType)) {
            sortMap.put("signType", signType);

        }
        if (!StringUtils.isEmpty(notifyUrl)) {
            sortMap.put("notifyUrl", notifyUrl);

        }

        return sortMap;
    }

    public BigDecimal getAmountRmb() {
        return amountRmb;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public void setAmountRmb(BigDecimal amountRmb) {
        this.amountRmb = amountRmb;
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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
