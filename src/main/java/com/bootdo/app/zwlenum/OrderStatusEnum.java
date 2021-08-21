package com.bootdo.app.zwlenum;

/**
 * PayTypeEnum
 *
 * @author Sylow
 * @version v1.0, 2016年7月6日
 * @since v6.1
 */
public enum OrderStatusEnum {

  PRE_PAY("pre_pay", "待支付"),
  FINISHED_PAY("finished_pay", "支付完成"),
  CALLBACK_SUCCESS("callback_success", "回调成功"),
  CALLBACK_FAILED("callback_failed","回调失败"),
  CANCELED("canceled", "取消");


  OrderStatusEnum(String key, String desc) {
    this.key = key;
    this.typeDesc = desc;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getTypeDesc() {
    return typeDesc;
  }

  public void setTypeDesc(String typeDesc) {
    this.typeDesc = typeDesc;
  }

  public static String getTypeDescByType(String type) {
    return OrderStatusEnum.valueOf(type).typeDesc;
  }

  @Override
  public String toString() {
    return this.key;
  }

  private String key;
  private String typeDesc;

}
