package com.bootdo.app.zwlenum;

public enum PayStatusEnum {
    //待支付
    PREPAY("pre_pay"),

    //已支付
    PAYED("payed"),

    //待转账
    PRE_TRANSFER("pre_transfer"),

    //处理失败
    FAILD_TRANSFER("faild_transfer"),


    //拒接
    REJECT("reject"),

    //完成
    FINISHED("finished"),

    //无效
    DISABLE("disable"),

    //在途
    ONLIONING("onlining"),

    //启用
    ENABLE("enable");


    // 构造方法
    PayStatusEnum(String key) {
        this.key = key;
    }


    @Override
    public String toString(){
        return this.key;
    }

    private String key;
}
