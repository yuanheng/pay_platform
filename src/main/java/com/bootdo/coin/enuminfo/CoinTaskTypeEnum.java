package com.bootdo.coin.enuminfo;

public enum CoinTaskTypeEnum {
    //BI多元
    BI_DUOYUAN("multi"),

    BI_CHENGFA_LIMIT("square_limit"),

    BI_CHENGFA_MARKET("square_market");

    // 构造方法
    CoinTaskTypeEnum(String key) {
        this.key = key;
    }


    @Override
    public String toString(){
        return this.key;
    }

    private String key;
}
