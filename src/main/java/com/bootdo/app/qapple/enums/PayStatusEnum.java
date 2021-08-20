package com.bootdo.app.qapple.enums;

/**
 * 支付结果
 *
 */
public enum PayStatusEnum {

    /** 待付款 */
    WAITPAY(0,"WAITPAY", "待付款"),
    /** 付款成功 */
    SUCCESS(1,"SUCCESS","付款成功"),
    /** 已取消 */
    CANCEL(3,"CANCEL","已取消"),
    /** 付款失败 */
    FAIL(2,"FAIL","付款失败");

    private int order;
    private String value;
    private String desc;

    PayStatusEnum(int order, String value, String desc){
        this.order = order;
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
