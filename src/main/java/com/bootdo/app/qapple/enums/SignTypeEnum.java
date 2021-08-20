package com.bootdo.app.qapple.enums;

/**
 * 加密类型
 *
 */
public enum SignTypeEnum {

    /** RSA */
    RSA(0,"RSA"),
    /** RSA2 */
    RSA2(1,"RSA2");

    private Integer key;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    SignTypeEnum(Integer key, String value){
        this.key = key;
        this.value = value;
    }

    public static SignTypeEnum getSignTypeByKey(Integer key) {
        for (SignTypeEnum type : values()) {
            if (type.key.equals(key)) {
                return type;
            }
        }
        return null;
    }
}
