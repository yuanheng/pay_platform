package com.bootdo.coin.client.req.trade;

import com.bootdo.coin.constant.enums.QueryDirectionEnum;
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
public class OrderHistoryRequest {

  private String symbol;

  private Long startTime;

  private Long endTime;

  private QueryDirectionEnum direction;

  private Integer size;

}
