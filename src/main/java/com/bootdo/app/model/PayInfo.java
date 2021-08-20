package com.bootdo.app.model;

import java.io.Serializable;

public class PayInfo implements Serializable {

  private String username;
  private String bankName;
  private String brankBranchName;
  private String cardId;

  public PayInfo(){

  }
  public PayInfo(String username, String bankName, String brankBranchName, String cardId) {
    this.username = username;
    this.bankName = bankName;
    this.brankBranchName = brankBranchName;
    this.cardId = cardId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getBrankBranchName() {
    return brankBranchName;
  }

  public void setBrankBranchName(String brankBranchName) {
    this.brankBranchName = brankBranchName;
  }

  public String getCardId() {
    return cardId;
  }

  public void setCardId(String cardId) {
    this.cardId = cardId;
  }
}
