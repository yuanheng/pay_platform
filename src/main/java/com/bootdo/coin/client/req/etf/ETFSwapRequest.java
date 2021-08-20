package com.bootdo.coin.client.req.etf;

import java.math.BigDecimal;

import com.bootdo.coin.constant.enums.EtfSwapDirectionEnum;
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
public class ETFSwapRequest {

  private EtfSwapDirectionEnum direction;

  private String etfName;

  private BigDecimal amount;

}
