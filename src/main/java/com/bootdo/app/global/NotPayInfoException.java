package com.bootdo.app.global;

public class NotPayInfoException extends Exception {
  private String msg;
  private Integer status;

  public NotPayInfoException(String msg,Integer status){
    super(msg);
    this.status = status;
  }

  public NotPayInfoException(String msg){
    super(msg);
    this.msg = msg;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
