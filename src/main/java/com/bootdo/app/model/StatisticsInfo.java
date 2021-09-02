package com.bootdo.app.model;

import java.io.Serializable;

/**
 * 太阳的雅鲁藏布
 */
public class StatisticsInfo implements Serializable {

    private Integer totalAmount;
    private Integer payedTotalAmount;
    private Integer totalOrderNum;
    private Integer payedTotalOrderNum;
    private Integer currentDayAmount;
    private Integer yesterdayAmount;

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getPayedTotalAmount() {
        return payedTotalAmount;
    }

    public void setPayedTotalAmount(Integer payedTotalAmount) {
        this.payedTotalAmount = payedTotalAmount;
    }

    public Integer getTotalOrderNum() {
        return totalOrderNum;
    }

    public void setTotalOrderNum(Integer totalOrderNum) {
        this.totalOrderNum = totalOrderNum;
    }

    public Integer getPayedTotalOrderNum() {
        return payedTotalOrderNum;
    }

    public void setPayedTotalOrderNum(Integer payedTotalOrderNum) {
        this.payedTotalOrderNum = payedTotalOrderNum;
    }

    public Integer getCurrentDayAmount() {
        return currentDayAmount;
    }

    public void setCurrentDayAmount(Integer currentDayAmount) {
        this.currentDayAmount = currentDayAmount;
    }

    public Integer getYesterdayAmount() {
        return yesterdayAmount;
    }

    public void setYesterdayAmount(Integer yesterdayAmount) {
        this.yesterdayAmount = yesterdayAmount;
    }
}
