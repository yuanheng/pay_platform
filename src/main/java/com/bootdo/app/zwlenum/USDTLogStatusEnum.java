package com.bootdo.app.zwlenum;

public enum USDTLogStatusEnum {
    //待支付
    pre_pay(1,"pre_pay","待确认"),

    cancled(2,"cancled","取消"),
    //已支付
    payed(3,"payed","成功"),
    //待转账
    pre_transfer(4,"pre_transfer","待回款"),
    //回款完成
    transfered(5,"transfered","回款完成"),
    //交易失败
    faild_transfer(6,"faild_transfer","交易失败");

    // 构造方法
    USDTLogStatusEnum(int id,String key,String desc) {
        this.id = id;
        this.key = key;
        this.desc = desc;
    }


    public static String getTypeDescById(int  type){
        if(type - pre_pay.id == 0){
            return pre_pay.desc;
        } else if(type - cancled.id == 0){
            return cancled.desc;
        } else if(type - payed.id == 0){
            return payed.desc;
        }else if(type - pre_transfer.id == 0){
            return pre_transfer.desc;
        }else if(type - transfered.id == 0){
            return  transfered.desc;
        }else if(type - faild_transfer.id == 0) {
            return faild_transfer.desc;
        }
        return null;
    }

    private String key;
    private String desc;
    private int id;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
