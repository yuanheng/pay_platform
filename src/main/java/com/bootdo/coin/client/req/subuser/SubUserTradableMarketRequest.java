package com.bootdo.coin.client.req.subuser;

import com.bootdo.coin.constant.enums.TradableMarketAccountTypeEnum;
import com.bootdo.coin.constant.enums.TradableMarketActivationEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubUserTradableMarketRequest {

  private String subUids;

  private TradableMarketAccountTypeEnum accountType;

  private TradableMarketActivationEnums activation;

}
