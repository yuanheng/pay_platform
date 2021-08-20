package com.bootdo.coin.constant.enums.algo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlgoOrderTypeEnum {

  LIMIT("limit"),
  MARKET("market"),

  ;
  private final String type;
}
