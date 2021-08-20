package com.bootdo.app.zwlenum;

public enum MemberLevelTypeEnum {

    //周
    VP1(1),
    //月
    VIP2(2),

    VIP3(3);


    // 构造方法
    MemberLevelTypeEnum(Integer key) {
        this.key = key;

    }


    public static String getPercent(Integer key){
        if(key == 1){
            return "0.30";
        }else if(key == 2){
            return "0.25";
        }else if(key == 3){
            return "0.15";
        }
        return "0.00";
    }



    @Override
    public String toString(){
        return this.key+"";
    }

    private Integer key;
    private String percent;
}
