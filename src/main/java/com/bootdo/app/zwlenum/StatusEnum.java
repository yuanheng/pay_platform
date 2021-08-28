package com.bootdo.app.zwlenum;

/**
 * PayTypeEnum
 *
 * @author Sylow
 * @version v1.0, 2016年7月6日
 * @since v6.1
 */
public enum StatusEnum {
  //充值
  ENABLE("enable", "启用"),
  DISABLE("disable", "禁用"),
  DELETED("delete", "删除");


  StatusEnum(String key, String desc) {
    this.key = key;
    this.typeDesc = desc;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }


  public static String getTypeDescByType(String type) {
    return StatusEnum.valueOf(type).typeDesc;
  }

  @Override
  public String toString() {
    return this.key;
  }

  private String key;
  private String typeDesc;

}
