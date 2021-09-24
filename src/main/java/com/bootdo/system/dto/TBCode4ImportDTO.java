package com.bootdo.system.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Prometheus
 * @date 2021/9/24.
 */
public class TBCode4ImportDTO implements java.io.Serializable {

    private String tbAccountNo;
    private BigDecimal amount;
    private String tbOrderNo;
    private Date tbOrderCreateTime;
    private String payUrl;
    private String mid;

    public String getTbAccountNo() {
        return tbAccountNo;
    }

    public void setTbAccountNo(String tbAccountNo) {
        this.tbAccountNo = tbAccountNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTbOrderNo() {
        return tbOrderNo;
    }

    public void setTbOrderNo(String tbOrderNo) {
        this.tbOrderNo = tbOrderNo;
    }

    public Date getTbOrderCreateTime() {
        return tbOrderCreateTime;
    }

    public void setTbOrderCreateTime(Date tbOrderCreateTime) {
        this.tbOrderCreateTime = tbOrderCreateTime;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    @Override
    public String toString() {
        return "TBCode4ImportDTO{" +
                "tbAccountNo='" + tbAccountNo + '\'' +
                ", amount=" + amount +
                ", tbOrderNo='" + tbOrderNo + '\'' +
                ", tbOrderCreateTime=" + tbOrderCreateTime +
                ", payUrl='" + payUrl + '\'' +
                ", mid='" + mid + '\'' +
                '}';
    }
}
