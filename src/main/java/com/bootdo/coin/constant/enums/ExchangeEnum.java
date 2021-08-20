package com.bootdo.coin.constant.enums;

import lombok.Getter;

@Getter
public enum ExchangeEnum {

  HUOBI("huobi"),

  ;
  private final String code;
  ExchangeEnum(String code) {
    this.code = code;
  }

}
