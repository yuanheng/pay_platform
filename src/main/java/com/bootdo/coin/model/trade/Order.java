package com.bootdo.coin.model.trade;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;
import com.bootdo.coin.constant.enums.StopOrderOperatorEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  private Long id;

  private String symbol;

  private Long accountId;

  private BigDecimal amount;

  private BigDecimal price;

  private String type;

  private BigDecimal filledAmount;

  private BigDecimal filledCashAmount;

  private BigDecimal filledFees;

  private String source;

  private String state;

  private Long createdAt;

  private Long canceledAt;

  private Long finishedAt;

  private BigDecimal stopPrice;

  private BigDecimal profit;


  @JSONField(deserialize = false)
  private StopOrderOperatorEnum operator;

}
