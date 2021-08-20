package com.bootdo.coin.client;

import java.util.List;

import com.bootdo.coin.client.req.trade.BatchCancelOpenOrdersRequest;
import com.bootdo.coin.client.req.trade.CreateOrderRequest;
import com.bootdo.coin.client.req.trade.FeeRateRequest;
import com.bootdo.coin.client.req.trade.MatchResultRequest;
import com.bootdo.coin.client.req.trade.OpenOrdersRequest;
import com.bootdo.coin.client.req.trade.OrderHistoryRequest;
import com.bootdo.coin.client.req.trade.OrdersRequest;
import com.bootdo.coin.client.req.trade.SubOrderUpdateV2Request;
import com.bootdo.coin.client.req.trade.SubTradeClearingRequest;
import com.bootdo.coin.constant.Options;
import com.bootdo.coin.constant.enums.ExchangeEnum;
import com.bootdo.coin.service.huobi.HuobiTradeService;
import com.bootdo.coin.utils.ResponseCallback;
import com.bootdo.coin.exception.SDKException;
import com.bootdo.coin.model.trade.BatchCancelOpenOrdersResult;
import com.bootdo.coin.model.trade.BatchCancelOrderResult;
import com.bootdo.coin.model.trade.FeeRate;
import com.bootdo.coin.model.trade.MatchResult;
import com.bootdo.coin.model.trade.Order;
import com.bootdo.coin.model.trade.OrderUpdateV2Event;
import com.bootdo.coin.model.trade.TradeClearingEvent;

public interface TradeClient {

  Long createOrder(CreateOrderRequest request);

  Long cancelOrder(Long orderId);

  Integer cancelOrder(String clientOrderId);

  BatchCancelOpenOrdersResult batchCancelOpenOrders(BatchCancelOpenOrdersRequest request);

  BatchCancelOrderResult batchCancelOrder(List<Long> ids);

  List<Order> getOpenOrders(OpenOrdersRequest request);

  Order getOrder(Long orderId);

  Order getOrder(String clientOrderId);

  List<Order> getOrders(OrdersRequest request);

  List<Order> getOrdersHistory(OrderHistoryRequest request);

  List<MatchResult> getMatchResult(Long orderId);

  List<MatchResult> getMatchResults(MatchResultRequest request);

  List<FeeRate> getFeeRate(FeeRateRequest request);

  void subOrderUpdateV2(SubOrderUpdateV2Request request, ResponseCallback<OrderUpdateV2Event> callback);

  void subTradeClearing(SubTradeClearingRequest request, ResponseCallback<TradeClearingEvent> callback);

  static TradeClient create(Options options) {

    if (options.getExchange().equals(ExchangeEnum.HUOBI)) {
      return new HuobiTradeService(options);
    }
    throw new SDKException(SDKException.INPUT_ERROR, "Unsupport Exchange.");
  }

}
