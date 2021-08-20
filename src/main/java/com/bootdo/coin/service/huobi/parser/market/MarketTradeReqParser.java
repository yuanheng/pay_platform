package com.bootdo.coin.service.huobi.parser.market;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bootdo.coin.model.market.MarketTradeReq;
import com.bootdo.coin.service.huobi.parser.HuobiModelParser;
import com.bootdo.coin.service.huobi.utils.DataUtils;

public class MarketTradeReqParser implements HuobiModelParser<MarketTradeReq> {

  @Override
  public MarketTradeReq parse(JSONObject json) {
    String dataKey = DataUtils.getDataKey(json);

    JSONArray dataArray = json.getJSONArray(dataKey);

    return MarketTradeReq.builder()
        .ch(json.getString("req"))
        .list(new MarketTradeParser().parseArray(dataArray))
        .build();
  }

  @Override
  public MarketTradeReq parse(JSONArray json) {
    return null;
  }

  @Override
  public List<MarketTradeReq> parseArray(JSONArray jsonArray) {
    return null;
  }
}
