package com.bootdo.coin.model.market;

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
public class MarketDepthEvent {

  private String ch;

  private Long ts;

  private MarketDepth depth;
}
