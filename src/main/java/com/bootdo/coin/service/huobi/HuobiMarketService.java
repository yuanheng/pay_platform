package com.bootdo.coin.service.huobi;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bootdo.coin.service.huobi.parser.market.CandlestickEventParser;
import com.bootdo.coin.service.huobi.parser.market.CandlestickParser;
import com.bootdo.coin.service.huobi.parser.market.CandlestickReqParser;
import com.bootdo.coin.service.huobi.parser.market.MarketBBOEventParser;
import com.bootdo.coin.service.huobi.parser.market.MarketDepthEventParser;
import com.bootdo.coin.service.huobi.parser.market.MarketDepthParser;
import com.bootdo.coin.service.huobi.parser.market.MarketDepthReqParser;
import com.bootdo.coin.service.huobi.parser.market.MarketDetailEventParser;
import com.bootdo.coin.service.huobi.parser.market.MarketDetailMergedParser;
import com.bootdo.coin.service.huobi.parser.market.MarketDetailParser;
import com.bootdo.coin.service.huobi.parser.market.MarketDetailReqParser;
import com.bootdo.coin.service.huobi.parser.market.MarketTickerParser;
import com.bootdo.coin.service.huobi.parser.market.MarketTradeEventParser;
import com.bootdo.coin.service.huobi.parser.market.MarketTradeParser;
import com.bootdo.coin.service.huobi.parser.market.MarketTradeReqParser;
import com.bootdo.coin.service.huobi.parser.market.MbpIncrementalUpdateEventParser;
import com.bootdo.coin.service.huobi.parser.market.MbpRefreshUpdateEventParser;
import com.bootdo.coin.client.MarketClient;
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
import com.bootdo.coin.constant.WebSocketConstants;
import com.bootdo.coin.constant.enums.DepthLevels;
import com.bootdo.coin.constant.enums.DepthSizeEnum;
import com.bootdo.coin.constant.enums.DepthStepEnum;
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
import com.bootdo.coin.service.huobi.connection.HuobiRestConnection;
import com.bootdo.coin.service.huobi.connection.HuobiWebSocketConnection;
import com.bootdo.coin.service.huobi.signature.UrlParamsBuilder;
import com.bootdo.coin.utils.InputChecker;
import com.bootdo.coin.utils.ResponseCallback;
import com.bootdo.coin.utils.SymbolUtils;
import com.bootdo.coin.utils.WebSocketConnection;
import org.springframework.stereotype.Service;

@Service
public class HuobiMarketService implements MarketClient {

  private Options options;

  private HuobiRestConnection restConnection;

  public HuobiMarketService(Options options) {
    this.options = options;
    restConnection = new HuobiRestConnection(options);
  }


  public static final String REST_CANDLESTICK_PATH = "/market/history/kline";
  public static final String REST_MARKET_DETAIL_MERGED_PATH = "/market/detail/merged";
  public static final String REST_MARKET_DETAIL_PATH = "/market/detail";
  public static final String REST_MARKET_TICKERS_PATH = "/market/tickers";
  public static final String REST_MARKET_DEPTH_PATH = "/market/depth";
  public static final String REST_MARKET_TRADE_PATH = "/market/trade";
  public static final String REST_MARKET_HISTORY_TRADE_PATH = "/market/history/trade";


  public static final String WEBSOCKET_CANDLESTICK_TOPIC = "market.$symbol$.kline.$period$";
  public static final String WEBSOCKET_MARKET_DETAIL_TOPIC = "market.$symbol.detail";
  public static final String WEBSOCKET_MARKET_DEPTH_TOPIC = "market.$symbol.depth.$type";
  public static final String WEBSOCKET_MARKET_TRADE_TOPIC = "market.$symbol.trade.detail";
  public static final String WEBSOCKET_MARKET_BBO_TOPIC = "market.$symbol.bbo";
  public static final String WEBSOCKET_MARKET_MBP_REFRESH_TOPIC = "market.$symbol.mbp.refresh.$levels";
  public static final String WEBSOCKET_MARKET_MBP_INCREMENT_TOPIC = "market.$symbol.mbp.$levels";

  @Override
  public List<Candlestick> getCandlestick(CandlestickRequest request) {

    // 参数检查
    InputChecker.checker()
        .checkSymbol(request.getSymbol())
        .checkRange(request.getSize(), 1, 2000, "size")
        .shouldNotNull(request.getInterval(), "CandlestickInterval");

    // 参数构建
    UrlParamsBuilder paramBuilder = UrlParamsBuilder.build()
        .putToUrl("symbol", request.getSymbol())
        .putToUrl("period", request.getInterval().getCode())
        .putToUrl("size", request.getSize());

    JSONObject json = restConnection.executeGet(REST_CANDLESTICK_PATH, paramBuilder);
    JSONArray data = json.getJSONArray("data");
    return new CandlestickParser().parseArray(data);
  }

  @Override
  public void subCandlestick(SubCandlestickRequest request, ResponseCallback<CandlestickEvent> callback) {

    // 检查参数
    InputChecker.checker()
        .shouldNotNull(request.getSymbol(), "symbol")
        .shouldNotNull(request.getInterval(), "interval");
    // 格式化symbol为数组
    List<String> symbolList = SymbolUtils.parseSymbols(request.getSymbol());

    // 检查数组
    InputChecker.checker()
        .checkSymbolList(symbolList);

    List<String> commandList = new ArrayList<>(symbolList.size());
    symbolList.forEach(symbol -> {

      String topic = WEBSOCKET_CANDLESTICK_TOPIC
          .replace("$symbol$", symbol)
          .replace("$period$", request.getInterval().getCode());

      JSONObject command = new JSONObject();
      command.put("sub", topic);
      command.put("id", System.nanoTime());
      commandList.add(command.toJSONString());
    });

    HuobiWebSocketConnection.createMarketConnection(options, commandList, new CandlestickEventParser(), callback, false);
  }

  @Override
  public MarketDetailMerged getMarketDetailMerged(MarketDetailMergedRequest request) {

    // 检查参数
    InputChecker.checker()
        .shouldNotNull(request.getSymbol(), "symbol");

    // 参数构建
    UrlParamsBuilder paramBuilder = UrlParamsBuilder.build()
        .putToUrl("symbol", request.getSymbol());

    JSONObject json = restConnection.executeGet(REST_MARKET_DETAIL_MERGED_PATH, paramBuilder);
    JSONObject data = json.getJSONObject("tick");
    return new MarketDetailMergedParser().parse(data);
  }

  @Override
  public MarketDetail getMarketDetail(MarketDetailRequest request) {

    // 检查参数
    InputChecker.checker()
        .shouldNotNull(request.getSymbol(), "symbol");

    // 参数构建
    UrlParamsBuilder paramBuilder = UrlParamsBuilder.build()
        .putToUrl("symbol", request.getSymbol());

    JSONObject json = restConnection.executeGet(REST_MARKET_DETAIL_PATH, paramBuilder);
    JSONObject data = json.getJSONObject("tick");
    return new MarketDetailParser().parse(data);
  }

  @Override
  public void subMarketDetail(SubMarketDetailRequest request, ResponseCallback<MarketDetailEvent> callback) {
    // 检查参数
    InputChecker.checker()
        .shouldNotNull(request.getSymbol(), "symbol");

    // 格式化symbol为数组
    List<String> symbolList = SymbolUtils.parseSymbols(request.getSymbol());

    // 检查数组
    InputChecker.checker()
        .checkSymbolList(symbolList);

    List<String> commandList = new ArrayList<>(symbolList.size());
    symbolList.forEach(symbol -> {

      String topic = WEBSOCKET_MARKET_DETAIL_TOPIC
          .replace("$symbol", symbol);

      JSONObject command = new JSONObject();
      command.put("sub", topic);
      command.put("id", System.nanoTime());
      commandList.add(command.toJSONString());
    });

    HuobiWebSocketConnection.createMarketConnection(options, commandList, new MarketDetailEventParser(), callback, false);
  }

  @Override
  public List<MarketTicker> getTickers() {

    JSONObject json = restConnection.executeGet(REST_MARKET_TICKERS_PATH, UrlParamsBuilder.build());
    JSONArray data = json.getJSONArray("data");
    return new MarketTickerParser().parseArray(data);
  }

  @Override
  public MarketDepth getMarketDepth(MarketDepthRequest request) {

    // 参数检查
    InputChecker.checker()
        .checkSymbol(request.getSymbol())
        .shouldNotNull(request.getStep(), "step");

    int size = request.getDepth() == null ? DepthSizeEnum.SIZE_20.getSize() : request.getDepth().getSize();

    // 参数构建
    UrlParamsBuilder paramBuilder = UrlParamsBuilder.build()
        .putToUrl("symbol", request.getSymbol())
        .putToUrl("depth", size)
        .putToUrl("type", request.getStep().getStep());

    JSONObject json = restConnection.executeGet(REST_MARKET_DEPTH_PATH, paramBuilder);
    JSONObject data = json.getJSONObject("tick");
    return new MarketDepthParser().parse(data);
  }

  @Override
  public void subMarketDepth(SubMarketDepthRequest request, ResponseCallback<MarketDepthEvent> callback) {
    // 检查参数
    InputChecker.checker()
        .shouldNotNull(request.getSymbol(), "symbol");

    // 格式化symbol为数组
    List<String> symbolList = SymbolUtils.parseSymbols(request.getSymbol());

    // 检查数组
    InputChecker.checker()
        .checkSymbolList(symbolList);

    String step = request.getStep() == null ? DepthStepEnum.STEP0.getStep() : request.getStep().getStep();
    List<String> commandList = new ArrayList<>(symbolList.size());
    symbolList.forEach(symbol -> {

      String topic = WEBSOCKET_MARKET_DEPTH_TOPIC
          .replace("$symbol", symbol)
          .replace("$type", step);

      JSONObject command = new JSONObject();
      command.put("sub", topic);
      command.put("id", System.nanoTime());
      commandList.add(command.toJSONString());
    });

    HuobiWebSocketConnection.createMarketConnection(options, commandList, new MarketDepthEventParser(), callback, false);
  }

  @Override
  public List<MarketTrade> getMarketTrade(MarketTradeRequest request) {
    // 参数检查
    InputChecker.checker()
        .checkSymbol(request.getSymbol());

    // 参数构建
    UrlParamsBuilder paramBuilder = UrlParamsBuilder.build()
        .putToUrl("symbol", request.getSymbol());

    JSONObject json = restConnection.executeGet(REST_MARKET_TRADE_PATH, paramBuilder);
    JSONArray data = json.getJSONObject("tick").getJSONArray("data");
    return new MarketTradeParser().parseArray(data);
  }

  @Override
  public void subMarketTrade(SubMarketTradeRequest request, ResponseCallback<MarketTradeEvent> callback) {

    // 检查参数
    InputChecker.checker()
        .shouldNotNull(request.getSymbol(), "symbol");

    // 格式化symbol为数组
    List<String> symbolList = SymbolUtils.parseSymbols(request.getSymbol());

    // 检查数组
    InputChecker.checker()
        .checkSymbolList(symbolList);

    List<String> commandList = new ArrayList<>(symbolList.size());
    symbolList.forEach(symbol -> {

      String topic = WEBSOCKET_MARKET_TRADE_TOPIC
          .replace("$symbol", symbol);

      JSONObject command = new JSONObject();
      command.put("sub", topic);
      command.put("id", System.nanoTime());
      commandList.add(command.toJSONString());
    });

    HuobiWebSocketConnection.createMarketConnection(options, commandList, new MarketTradeEventParser(), callback, false);
  }

  @Override
  public List<MarketTrade> getMarketHistoryTrade(MarketHistoryTradeRequest request) {
    // 参数检查
    InputChecker.checker()
        .checkSymbol(request.getSymbol());

    int size = request.getSize() == null ? 2000 : request.getSize();

    // 参数构建
    UrlParamsBuilder paramBuilder = UrlParamsBuilder.build()
        .putToUrl("symbol", request.getSymbol())
        .putToUrl("size", size);

    JSONObject json = restConnection.executeGet(REST_MARKET_HISTORY_TRADE_PATH, paramBuilder);
    JSONArray jsonArray = json.getJSONArray("data");
    if (jsonArray == null || jsonArray.size() <= 0) {
      return new ArrayList<>();
    }

    // 解析数据
    List<MarketTrade> resList = new ArrayList<>();
    MarketTradeParser parser = new MarketTradeParser();
    for (int i = 0; i < jsonArray.size(); i++) {
      JSONObject data = jsonArray.getJSONObject(i);
      JSONArray dataArray = data.getJSONArray("data");
      List<MarketTrade> dataList = parser.parseArray(dataArray);
      if (dataList != null && dataList.size() > 0) {
        resList.addAll(dataList);
      }
    }
    return resList;
  }

  @Override
  public void subMarketBBO(SubMarketBBORequest request, ResponseCallback<MarketBBOEvent> callback) {

    // 检查参数
    InputChecker.checker()
        .shouldNotNull(request.getSymbol(), "symbol");

    // 格式化symbol为数组
    List<String> symbolList = SymbolUtils.parseSymbols(request.getSymbol());

    // 检查数组
    InputChecker.checker()
        .checkSymbolList(symbolList);

    List<String> commandList = new ArrayList<>(symbolList.size());
    symbolList.forEach(symbol -> {

      String topic = WEBSOCKET_MARKET_BBO_TOPIC
          .replace("$symbol", symbol);

      JSONObject command = new JSONObject();
      command.put("sub", topic);
      command.put("id", System.nanoTime());
      commandList.add(command.toJSONString());
    });

    HuobiWebSocketConnection.createMarketConnection(options, commandList, new MarketBBOEventParser(), callback, false);

  }

  public void subMbpRefreshUpdate(SubMbpRefreshUpdateRequest request, ResponseCallback<MbpRefreshUpdateEvent> callback) {

    // 检查参数
    InputChecker.checker()
        .shouldNotNull(request.getSymbols(), "symbols");

    // 格式化symbol为数组
    List<String> symbolList = SymbolUtils.parseSymbols(request.getSymbols());

    // 检查数组
    InputChecker.checker()
        .checkSymbolList(symbolList);

    int level = request.getLevels() == null ? DepthLevels.LEVEL_20.getLevel() : request.getLevels().getLevel();
    if (level >= DepthLevels.LEVEL_150.getLevel()) {
      throw new SDKException(SDKException.INPUT_ERROR, " Unsupport Levels : " + request.getLevels());
    }
    List<String> commandList = new ArrayList<>(symbolList.size());
    symbolList.forEach(symbol -> {

      String topic = WEBSOCKET_MARKET_MBP_REFRESH_TOPIC
          .replace("$symbol", symbol)
          .replace("$levels", level + "");

      JSONObject command = new JSONObject();
      command.put("sub", topic);
      command.put("id", System.nanoTime());
      commandList.add(command.toJSONString());
    });

    HuobiWebSocketConnection.createMarketConnection(options, commandList, new MbpRefreshUpdateEventParser(), callback, false);
  }

  public WebSocketConnection subMbpIncrementalUpdate(SubMbpIncrementalUpdateRequest request, ResponseCallback<MbpIncrementalUpdateEvent> callback) {

    // 检查参数
    InputChecker.checker()
        .checkSymbol(request.getSymbol());

    int level = request.getLevels() == null ? DepthLevels.LEVEL_150.getLevel() : request.getLevels().getLevel();
    List<String> commandList = new ArrayList<>(1);

    String topic = WEBSOCKET_MARKET_MBP_INCREMENT_TOPIC
        .replace("$symbol", request.getSymbol())
        .replace("$levels", level + "");

    JSONObject command = new JSONObject();
    command.put("sub", topic);
    command.put("id", System.nanoTime());
    commandList.add(command.toJSONString());

    return HuobiWebSocketConnection.createMarketConnection(options, commandList, new MbpIncrementalUpdateEventParser(), callback, false);
  }

  public WebSocketConnection reqMbpIncrementalUpdate(SubMbpIncrementalUpdateRequest request, WebSocketConnection connection) {

    // 检查参数
    InputChecker.checker()
        .checkSymbol(request.getSymbol());

    int level = request.getLevels() == null ? DepthLevels.LEVEL_150.getLevel() : request.getLevels().getLevel();
    if (level != DepthLevels.LEVEL_150.getLevel()) {
      throw new SDKException(SDKException.INPUT_ERROR, " Unsupport Levels : " + request.getLevels() + " incremental update only support level_150");
    }
    List<String> commandList = new ArrayList<>(1);

    String topic = WEBSOCKET_MARKET_MBP_INCREMENT_TOPIC
        .replace("$symbol", request.getSymbol())
        .replace("$levels", level + "");

    JSONObject command = new JSONObject();
    command.put("req", topic);
    command.put("id", System.nanoTime());

    connection.send(command.toJSONString());
    return connection;
  }

  public void reqCandlestick(ReqCandlestickRequest request, ResponseCallback<CandlestickReq> callback) {

    // 检查参数
    InputChecker.checker()
        .shouldNotNull(request.getSymbol(), "symbol")
        .shouldNotNull(request.getInterval(), "interval");

    String topic = WEBSOCKET_CANDLESTICK_TOPIC
        .replace("$symbol$", request.getSymbol())
        .replace("$period$", request.getInterval().getCode());

    JSONObject command = new JSONObject();
    command.put(WebSocketConstants.OP_REQ, topic);
    command.put("id", System.nanoTime());
    if (request.getFrom() != null) {
      command.put("from", request.getFrom());
    }
    if (request.getTo() != null) {
      command.put("to", request.getTo());
    }
    List<String> commandList = new ArrayList<>(1);
    commandList.add(command.toJSONString());

    HuobiWebSocketConnection.createMarketConnection(options, commandList, new CandlestickReqParser(), callback, true);
  }

  public void reqMarketDepth(ReqMarketDepthRequest request, ResponseCallback<MarketDepthReq> callback) {
    // 检查参数
    InputChecker.checker()
        .shouldNotNull(request.getSymbol(), "symbol")
        .shouldNotNull(request.getStep(), "step");

    String topic = WEBSOCKET_MARKET_DEPTH_TOPIC
        .replace("$symbol", request.getSymbol())
        .replace("$type", request.getStep().getStep());

    JSONObject command = new JSONObject();
    command.put(WebSocketConstants.OP_REQ, topic);
    command.put("id", System.nanoTime());

    List<String> commandList = new ArrayList<>(1);
    commandList.add(command.toJSONString());
    HuobiWebSocketConnection.createMarketConnection(options, commandList, new MarketDepthReqParser(), callback, true);

  }

  public void reqMarketTrade(ReqMarketTradeRequest request, ResponseCallback<MarketTradeReq> callback) {
    // 检查参数
    InputChecker.checker()
        .shouldNotNull(request.getSymbol(), "symbol");

    String topic = WEBSOCKET_MARKET_TRADE_TOPIC
        .replace("$symbol", request.getSymbol());

    JSONObject command = new JSONObject();
    command.put(WebSocketConstants.OP_REQ, topic);
    command.put("id", System.nanoTime());

    List<String> commandList = new ArrayList<>(1);
    commandList.add(command.toJSONString());
    HuobiWebSocketConnection.createMarketConnection(options, commandList, new MarketTradeReqParser(), callback, true);
  }

  public void reqMarketDetail(ReqMarketDetailRequest request, ResponseCallback<MarketDetailReq> callback) {
    // 检查参数
    InputChecker.checker()
        .shouldNotNull(request.getSymbol(), "symbol");

    String topic = WEBSOCKET_MARKET_DETAIL_TOPIC
        .replace("$symbol", request.getSymbol());

    JSONObject command = new JSONObject();
    command.put(WebSocketConstants.OP_REQ, topic);
    command.put("id", System.nanoTime());

    List<String> commandList = new ArrayList<>(1);
    commandList.add(command.toJSONString());
    HuobiWebSocketConnection.createMarketConnection(options, commandList, new MarketDetailReqParser(), callback, true);
  }


}
