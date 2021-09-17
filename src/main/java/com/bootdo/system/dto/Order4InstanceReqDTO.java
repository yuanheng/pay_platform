package com.bootdo.system.dto;

import com.bootdo.app.model.PaymentInfo;

import javax.validation.constraints.NotEmpty;

/**
 * @author Prometheus
 * @date 2021/8/29.
 */
public class Order4InstanceReqDTO extends PaymentInfo {

    //    @NotEmpty(message = "非法的數值[name]")
    private String name;
    @NotEmpty(message = "非法的數值[payType]")
    private String type;
    @NotEmpty(message = "非法的數值[amount]")
    private String amount;
    //    @NotEmpty(message = "非法的數值[remark]")

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getAmount() {
        return amount;
    }

    @Override
    public void setAmount(String amount) {
        this.amount = amount;
    }
}
