package com.bootdo.coin.client.req.market;

import com.bootdo.coin.constant.enums.DepthSizeEnum;
import com.bootdo.coin.constant.enums.DepthStepEnum;
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
public class MarketDepthRequest {

  private String symbol;

  private DepthSizeEnum depth;

  private DepthStepEnum step;

}
