package com.bootdo.system.domain;

import lombok.ToString;

import java.math.BigDecimal;

/**
 * 首页统计信息
 *
 * @author Zhu YongQi
 * @date 2021/8/22.
 */
@ToString
public class StatisticInfoDO implements java.io.Serializable {

    /**
     * 当日收款总额
     */
    private BigDecimal dailyReceivedAmount;

    /**
     * 总收款金额
     */
    private BigDecimal totalReceivedAmount;

    /**
     * 总单数
     */
    private Integer totalTxTimes;

    /**
     * 成功单数
     */
    private Integer succeedTxTimes;

    /**
     * 收款成功比例（%）
     */
    private BigDecimal txSucceedRate;

    public BigDecimal getDailyReceivedAmount() {
        return dailyReceivedAmount;
    }

    public void setDailyReceivedAmount(BigDecimal dailyReceivedAmount) {
        this.dailyReceivedAmount = dailyReceivedAmount;
    }

    public BigDecimal getTotalReceivedAmount() {
        return totalReceivedAmount;
    }

    public void setTotalReceivedAmount(BigDecimal totalReceivedAmount) {
        this.totalReceivedAmount = totalReceivedAmount;
    }

    public Integer getTotalTxTimes() {
        return totalTxTimes;
    }

    public void setTotalTxTimes(Integer totalTxTimes) {
        this.totalTxTimes = totalTxTimes;
    }

    public Integer getSucceedTxTimes() {
        return succeedTxTimes;
    }

    public void setSucceedTxTimes(Integer succeedTxTimes) {
        this.succeedTxTimes = succeedTxTimes;
    }

    public BigDecimal getTxSucceedRate() {
        return txSucceedRate;
    }

    public void setTxSucceedRate(BigDecimal txSucceedRate) {
        this.txSucceedRate = txSucceedRate;
    }

    public StatisticInfoDO emptyInstance() {
        this.totalReceivedAmount = BigDecimal.ZERO;
        this.totalTxTimes = 0;
        this.succeedTxTimes = 0;
        this.txSucceedRate = BigDecimal.ZERO;
        this.dailyReceivedAmount = BigDecimal.ZERO;
        return this;
    }
}
