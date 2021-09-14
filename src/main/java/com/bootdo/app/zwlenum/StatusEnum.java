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
  DELETED("delete", "删除"),
  FINISHED("finish", "完成");


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

  public static StatusEnum getStatusEnumByKey(String key){
    if(StatusEnum.ENABLE.key.equals(key)) {
      return StatusEnum.ENABLE;
    } else if (StatusEnum.DISABLE.key.equals(key)) {
      return StatusEnum.DISABLE;
    } else if (StatusEnum.DELETED.equals(key)) {
      return StatusEnum.DELETED;
    }
    return null;
  }

}
