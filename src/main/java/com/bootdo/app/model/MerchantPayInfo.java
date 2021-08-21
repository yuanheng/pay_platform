package com.bootdo.app.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class MerchantPayInfo implements Serializable {
  private String payUrl;
  private JSONObject payInfo;

  public String getPayUrl() {
    return payUrl;
  }

  public void setPayUrl(String payUrl) {
    this.payUrl = payUrl;
  }

  public JSONObject getPayInfo() {
    return payInfo;
  }

  public void setPayInfo(JSONObject payInfo) {
    this.payInfo = payInfo;
  }
}
