package com.bootdo.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Prometheus
 * @date 2021/9/15.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TbCodeStatusDTO {

    private String amount;
    private String status;
    private String count;

}
