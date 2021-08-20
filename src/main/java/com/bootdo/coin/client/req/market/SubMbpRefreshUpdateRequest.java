package com.bootdo.coin.client.req.market;

import com.bootdo.coin.constant.enums.DepthLevels;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubMbpRefreshUpdateRequest {

  private String symbols;

  private DepthLevels levels;

}
