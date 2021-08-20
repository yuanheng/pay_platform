package com.bootdo.coin.client;

import java.util.List;

import com.bootdo.coin.client.req.generic.CurrencyChainsRequest;
import com.bootdo.coin.constant.Options;
import com.bootdo.coin.constant.enums.ExchangeEnum;
import com.bootdo.coin.model.generic.CurrencyChain;
import com.bootdo.coin.model.generic.MarketStatus;
import com.bootdo.coin.model.generic.Symbol;
import com.bootdo.coin.service.huobi.HuobiGenericService;
import com.bootdo.coin.exception.SDKException;

public interface GenericClient {

  String getSystemStatus();

  MarketStatus getMarketStatus();

  List<Symbol> getSymbols();

  List<String> getCurrencys();

  List<CurrencyChain> getCurrencyChains(CurrencyChainsRequest request);

  Long getTimestamp();

  static GenericClient create(Options options) {

    if (options.getExchange().equals(ExchangeEnum.HUOBI)) {
      return new HuobiGenericService(options);
    }
    throw new SDKException(SDKException.INPUT_ERROR, "Unsupport Exchange.");
  }
}
