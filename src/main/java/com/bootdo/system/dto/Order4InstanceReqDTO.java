package com.bootdo.system.dto;

import com.bootdo.app.model.PaymentInfo;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Prometheus
 * @date 2021/8/29.
 */
@Data
public class Order4InstanceReqDTO extends PaymentInfo {

    @NotEmpty(message = "非法的數值[name]")
    private String name;
    @NotEmpty(message = "非法的數值[payType]")
    private String type;
    @NotEmpty(message = "非法的數值[amount]")
    private String amount;
    @NotEmpty(message = "非法的數值[remark]")
    private String remark;

}
