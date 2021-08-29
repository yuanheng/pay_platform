package com.bootdo.app.model;

import java.io.Serializable;

public class PaymentInfo implements Serializable {

  private String merchantNo; //商户号
  private String merchantOrderNo; //商户订单
  private String amount; //订单金额 单位是分
  private String sign;
  private String type;

  private String remark;

  public String getMerchantOrderNo() {
    return merchantOrderNo;
  }

  public void setMerchantOrderNo(String merchantOrderNo) {
    this.merchantOrderNo = merchantOrderNo;
  }

  public String getMerchantNo() {
    return merchantNo;
  }

  public void setMerchantNo(String merchantNo) {
    this.merchantNo = merchantNo;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }


  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
