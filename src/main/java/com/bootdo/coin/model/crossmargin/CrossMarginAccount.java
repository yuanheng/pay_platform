package com.bootdo.coin.model.crossmargin;

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
public class CrossMarginAccount {

  private Long id;

  private String type;

  private String state;

  private BigDecimal riskRate;

  private BigDecimal acctBalanceSum;

  private BigDecimal debtBalanceSum;

  private List<Balance> balanceList;

}
