package com.bootdo.coin.service.huobi.parser.market;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bootdo.coin.model.market.MarketDepthReq;
import com.bootdo.coin.service.huobi.parser.HuobiModelParser;
import com.bootdo.coin.service.huobi.utils.DataUtils;

public class MarketDepthReqParser implements HuobiModelParser<MarketDepthReq> {

  @Override
  public MarketDepthReq parse(JSONObject json) {
    String dataKey = DataUtils.getDataKey(json);
    return MarketDepthReq.builder()
        .ch(json.getString("ch"))
        .ts(json.getLong("ts"))
        .depth(new MarketDepthParser().parse(json.getJSONObject(dataKey)))
        .build();
  }

  @Override
  public MarketDepthReq parse(JSONArray json) {
    return null;
  }

  @Override
  public List<MarketDepthReq> parseArray(JSONArray jsonArray) {
    return null;
  }
}
