package com.bootdo.coin.model.etf;

import java.math.BigDecimal;
import java.util.List;

import com.bootdo.coin.constant.enums.EtfStatusEnum;
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
public class ETFConfig {

  private int purchaseMinAmount;
  private int purchaseMaxAmount;
  private int redemptionMinAmount;
  private int redemptionMaxAmount;
  private BigDecimal purchaseFeeRate;
  private BigDecimal redemptionFeeRate;
  private EtfStatusEnum status;
  private List<ETFUnitPrice> unitPriceList;

}
