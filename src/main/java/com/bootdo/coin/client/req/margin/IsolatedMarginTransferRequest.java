package com.bootdo.coin.client.req.margin;

import java.math.BigDecimal;

import com.bootdo.coin.constant.enums.MarginTransferDirectionEnum;
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
public class IsolatedMarginTransferRequest {

  private MarginTransferDirectionEnum direction;

  private String symbol;

  private String currency;

  private BigDecimal amount;

}
