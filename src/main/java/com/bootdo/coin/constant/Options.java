package com.bootdo.coin.constant;

import com.bootdo.coin.constant.enums.ExchangeEnum;
import org.springframework.stereotype.Component;

public interface Options {

  String getApiKey();

  String getSecretKey();

  ExchangeEnum getExchange();

  String getRestHost();

  String getWebSocketHost();

  boolean isWebSocketAutoConnect();

}
