package com.bootdo.app.model;

public class PayNotifyResult {

    private String amount;
    private String factAmount;
    private String tradeNo;
    private String tradeOutNo;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFactAmount() {
        return factAmount;
    }

    public void setFactAmount(String factAmount) {
        this.factAmount = factAmount;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeOutNo() {
        return tradeOutNo;
    }

    public void setTradeOutNo(String tradeOutNo) {
        this.tradeOutNo = tradeOutNo;
    }
}
