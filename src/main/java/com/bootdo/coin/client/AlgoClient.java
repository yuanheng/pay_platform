package com.bootdo.coin.client;

import com.bootdo.coin.client.req.algo.CancelAlgoOrderRequest;
import com.bootdo.coin.client.req.algo.CreateAlgoOrderRequest;
import com.bootdo.coin.client.req.algo.GetHistoryAlgoOrdersRequest;
import com.bootdo.coin.client.req.algo.GetOpenAlgoOrdersRequest;
import com.bootdo.coin.constant.enums.ExchangeEnum;
import com.bootdo.coin.service.huobi.HuobiAlgoService;
import com.bootdo.coin.constant.Options;
import com.bootdo.coin.exception.SDKException;
import com.bootdo.coin.model.algo.AlgoOrder;
import com.bootdo.coin.model.algo.CancelAlgoOrderResult;
import com.bootdo.coin.model.algo.CreateAlgoOrderResult;
import com.bootdo.coin.model.algo.GetHistoryAlgoOrdersResult;
import com.bootdo.coin.model.algo.GetOpenAlgoOrdersResult;

public interface AlgoClient {

  CreateAlgoOrderResult createAlgoOrder(CreateAlgoOrderRequest request);

  CancelAlgoOrderResult cancelAlgoOrder(CancelAlgoOrderRequest request);

  GetOpenAlgoOrdersResult getOpenAlgoOrders(GetOpenAlgoOrdersRequest request);

  GetHistoryAlgoOrdersResult getHistoryAlgoOrders(GetHistoryAlgoOrdersRequest request);

  AlgoOrder getAlgoOrdersSpecific(String clientOrderId);


  static AlgoClient create(Options options) {

    if (options.getExchange().equals(ExchangeEnum.HUOBI)) {
      return new HuobiAlgoService(options);
    }
    throw new SDKException(SDKException.INPUT_ERROR, "Unsupport Exchange.");
  }
}
