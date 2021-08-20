package com.bootdo.coin.service.huobi.parser.trade;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bootdo.coin.model.trade.TradeClearing;
import com.bootdo.coin.service.huobi.parser.HuobiModelParser;

public class TradeClearingParser implements HuobiModelParser<TradeClearing> {

  @Override
  public TradeClearing parse(JSONObject json) {
    return json.toJavaObject(TradeClearing.class);
  }

  @Override
  public TradeClearing parse(JSONArray json) {
    return null;
  }

  @Override
  public List<TradeClearing> parseArray(JSONArray jsonArray) {
    return null;
  }
}
