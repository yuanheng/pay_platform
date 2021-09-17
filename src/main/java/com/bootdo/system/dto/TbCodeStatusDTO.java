package com.bootdo.system.dto;

/**
 * @author Prometheus
 * @date 2021/9/15.
 */
public class TbCodeStatusDTO {

    private String amount;
    private String status;
    private String count;

    public TbCodeStatusDTO() {
    }

    public TbCodeStatusDTO(String amount, String status, String count) {
        this.amount = amount;
        this.status = status;
        this.count = count;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
