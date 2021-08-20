package com.bootdo.coin.model.isolatedmargin;

import java.math.BigDecimal;
import java.util.List;

import com.bootdo.coin.model.account.Balance;
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
public class IsolatedMarginAccount {

  private Long id;

  private String symbol;

  private BigDecimal flPrice;

  private String flType;

  private BigDecimal riskRate;

  private String type;

  private String state;

  private List<Balance> balanceList;

}
