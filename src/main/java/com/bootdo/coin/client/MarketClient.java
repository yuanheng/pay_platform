package com.bootdo.coin.client;

import java.util.List;

import com.bootdo.coin.client.req.market.CandlestickRequest;
import com.bootdo.coin.client.req.market.MarketDepthRequest;
import com.bootdo.coin.client.req.market.MarketDetailMergedRequest;
import com.bootdo.coin.client.req.market.MarketDetailRequest;
import com.bootdo.coin.client.req.market.MarketHistoryTradeRequest;
import com.bootdo.coin.client.req.market.MarketTradeRequest;
import com.bootdo.coin.client.req.market.ReqCandlestickRequest;
import com.bootdo.coin.client.req.market.ReqMarketDepthRequest;
import com.bootdo.coin.client.req.market.ReqMarketDetailRequest;
import com.bootdo.coin.client.req.market.ReqMarketTradeRequest;
import com.bootdo.coin.client.req.market.SubCandlestickRequest;
import com.bootdo.coin.client.req.market.SubMarketBBORequest;
import com.bootdo.coin.client.req.market.SubMarketDepthRequest;
import com.bootdo.coin.client.req.market.SubMarketDetailRequest;
import com.bootdo.coin.client.req.market.SubMarketTradeRequest;
import com.bootdo.coin.client.req.market.SubMbpIncrementalUpdateRequest;
import com.bootdo.coin.client.req.market.SubMbpRefreshUpdateRequest;
import com.bootdo.coin.constant.Options;
import com.bootdo.coin.constant.enums.ExchangeEnum;
import com.bootdo.coin.service.huobi.HuobiMarketService;
import com.bootdo.coin.utils.ResponseCallback;
import com.bootdo.coin.utils.WebSocketConnection;
import com.bootdo.coin.exception.SDKException;
import com.bootdo.coin.model.market.Candlestick;
import com.bootdo.coin.model.market.CandlestickEvent;
import com.bootdo.coin.model.market.CandlestickReq;
import com.bootdo.coin.model.market.MarketBBOEvent;
import com.bootdo.coin.model.market.MarketDepth;
import com.bootdo.coin.model.market.MarketDepthEvent;
import com.bootdo.coin.model.market.MarketDepthReq;
import com.bootdo.coin.model.market.MarketDetail;
import com.bootdo.coin.model.market.MarketDetailEvent;
import com.bootdo.coin.model.market.MarketDetailMerged;
import com.bootdo.coin.model.market.MarketDetailReq;
import com.bootdo.coin.model.market.MarketTicker;
import com.bootdo.coin.model.market.MarketTrade;
import com.bootdo.coin.model.market.MarketTradeEvent;
import com.bootdo.coin.model.market.MarketTradeReq;
import com.bootdo.coin.model.market.MbpIncrementalUpdateEvent;
import com.bootdo.coin.model.market.MbpRefreshUpdateEvent;

public interface MarketClient {

  List<Candlestick> getCandlestick(CandlestickRequest request);

  MarketDetailMerged getMarketDetailMerged(MarketDetailMergedRequest request);

  MarketDetail getMarketDetail(MarketDetailRequest request);

  List<MarketTicker> getTickers();

  MarketDepth getMarketDepth(MarketDepthRequest request);

  List<MarketTrade> getMarketTrade(MarketTradeRequest request);

  List<MarketTrade> getMarketHistoryTrade(MarketHistoryTradeRequest request);

  void subCandlestick(SubCandlestickRequest request, ResponseCallback<CandlestickEvent> callback);

  void subMarketDetail(SubMarketDetailRequest request, ResponseCallback<MarketDetailEvent> callback);

  void subMarketDepth(SubMarketDepthRequest request, ResponseCallback<MarketDepthEvent> callback);

  void subMarketTrade(SubMarketTradeRequest request, ResponseCallback<MarketTradeEvent> callback);

  void subMarketBBO(SubMarketBBORequest request, ResponseCallback<MarketBBOEvent> callback);

  void subMbpRefreshUpdate(SubMbpRefreshUpdateRequest request, ResponseCallback<MbpRefreshUpdateEvent> callback);

  WebSocketConnection subMbpIncrementalUpdate(SubMbpIncrementalUpdateRequest request, ResponseCallback<MbpIncrementalUpdateEvent> callback);

  WebSocketConnection reqMbpIncrementalUpdate(SubMbpIncrementalUpdateRequest request, WebSocketConnection connection);

  void reqCandlestick(ReqCandlestickRequest request, ResponseCallback<CandlestickReq> callback);

  void reqMarketDepth(ReqMarketDepthRequest request, ResponseCallback<MarketDepthReq> callback);

  void reqMarketTrade(ReqMarketTradeRequest request, ResponseCallback<MarketTradeReq> callback);

  void reqMarketDetail(ReqMarketDetailRequest request, ResponseCallback<MarketDetailReq> callback);

  static MarketClient create(Options options) {

    if (options.getExchange().equals(ExchangeEnum.HUOBI)) {
      return new HuobiMarketService(options);
    }
    throw new SDKException(SDKException.INPUT_ERROR, "Unsupport Exchange.");
  }


}
