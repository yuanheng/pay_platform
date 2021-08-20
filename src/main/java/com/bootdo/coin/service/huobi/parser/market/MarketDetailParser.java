package com.bootdo.coin.service.huobi.parser.market;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bootdo.coin.model.market.MarketDetail;
import com.bootdo.coin.service.huobi.parser.HuobiModelParser;

public class MarketDetailParser implements HuobiModelParser<MarketDetail> {

  @Override
  public MarketDetail parse(JSONObject json) {
    return json.toJavaObject(MarketDetail.class);
  }

  @Override
  public MarketDetail parse(JSONArray json) {
    return null;
  }

  @Override
  public List<MarketDetail> parseArray(JSONArray jsonArray) {
    return null;
  }
}
