package com.bootdo.coin.service.huobi.parser.market;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bootdo.coin.model.market.MarketDetail;
import com.bootdo.coin.model.market.MarketDetailEvent;
import com.bootdo.coin.service.huobi.parser.HuobiModelParser;
import com.bootdo.coin.service.huobi.utils.DataUtils;

public class MarketDetailEventParser implements HuobiModelParser<MarketDetailEvent> {

  @Override
  public MarketDetailEvent parse(JSONObject json) {
    String dataKey = DataUtils.getDataKey(json);

    JSONObject data = json.getJSONObject(dataKey);
    MarketDetail marketDetail = new MarketDetailParser().parse(data);
    return MarketDetailEvent.builder()
        .ch(json.getString("ch"))
        .ts(json.getLong("ts"))
        .detail(marketDetail)
        .build();
  }

  @Override
  public MarketDetailEvent parse(JSONArray json) {
    return null;
  }

  @Override
  public List<MarketDetailEvent> parseArray(JSONArray jsonArray) {
    return null;
  }
}
