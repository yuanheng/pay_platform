package com.bootdo.coin.enuminfo;

public enum CoinTaskStatusEnum {
    //关闭
    CLOSED("closed"),

    //开启
    ENABLE("enable"),

    //暂停买入
    PAUSE_BUY("pause_buy"),

    //暂停买入 卖出中
    PAUSE_BUY_DEAILING("pause_buy_dealing"),

    // 交易中

    DEAILING("dealing");


    // 构造方法
    CoinTaskStatusEnum(String key) {
        this.key = key;
    }


    @Override
    public String toString(){
        return this.key;
    }

    private String key;
}
