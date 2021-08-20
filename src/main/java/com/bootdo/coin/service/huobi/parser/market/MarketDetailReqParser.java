package com.bootdo.coin.service.huobi.parser.market;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bootdo.coin.model.market.MarketDetail;
import com.bootdo.coin.model.market.MarketDetailReq;
import com.bootdo.coin.service.huobi.parser.HuobiModelParser;
import com.bootdo.coin.service.huobi.utils.DataUtils;

public class MarketDetailReqParser implements HuobiModelParser<MarketDetailReq> {

  @Override
  public MarketDetailReq parse(JSONObject json) {
    String dataKey = DataUtils.getDataKey(json);

    JSONObject data = json.getJSONObject(dataKey);
    MarketDetail marketDetail = new MarketDetailParser().parse(data);
    return MarketDetailReq.builder()
        .ch(json.getString("rep"))
        .ts(json.getLong("ts"))
        .detail(marketDetail)
        .build();
  }

  @Override
  public MarketDetailReq parse(JSONArray json) {
    return null;
  }

  @Override
  public List<MarketDetailReq> parseArray(JSONArray jsonArray) {
    return null;
  }
}
