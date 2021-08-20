package com.bootdo.app.zwlenum;

public enum AgreementTypeEnum {

    DAY("day",1,"0.30",500000),

    //周
    WEEK("week",7,"0.30",500000),

    //周
    HAFL_MONTH("half_month",15,"0.30",500000),

    //月
    MONTH("month",30,"0.25",500000),

    THREE_MONTH("three_month",90,"0.25",500000),

    SIX_MONTH("six_month",180,"0.25",500000);


    // 构造方法
    AgreementTypeEnum(String key,Integer days,String percent,Integer maxAmount) {
        this.key = key;
        this.days = days;
        this.maxAmount = maxAmount;
        this.percent = percent;

    }
    
    public static AgreementTypeEnum getDaysByKey(String key){
        if("day".equals(key)){
            return DAY;
        } else if("week".equals(key)){
            return WEEK;
        }else if("month".equals(key)){
            return MONTH;
        }else if("half_month".equals(key)){
            return HAFL_MONTH;
        }else if("three_month".equals(key)){
            return THREE_MONTH;
        }else if("six_month".equals(key)){
            return SIX_MONTH;
        }
        return null;
    }



    @Override
    public String toString(){
        return this.key;
    }

    public String key;

    public Integer days;

    public Integer maxAmount;

    public String percent;

}
