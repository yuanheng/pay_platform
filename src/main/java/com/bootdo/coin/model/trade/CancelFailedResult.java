package com.bootdo.coin.model.trade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CancelFailedResult {

  private Long orderId;

  private String errMsg;

  private String errCode;

  private Integer orderState;

}
