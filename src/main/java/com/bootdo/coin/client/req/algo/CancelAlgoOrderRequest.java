package com.bootdo.coin.client.req.algo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CancelAlgoOrderRequest {

  private List<String> clientOrderIds;

}
