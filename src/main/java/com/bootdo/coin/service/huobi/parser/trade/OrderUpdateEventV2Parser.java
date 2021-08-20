package com.bootdo.coin.service.huobi.parser.trade;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bootdo.coin.model.trade.OrderUpdateV2Event;
import com.bootdo.coin.service.huobi.parser.HuobiModelParser;

public class OrderUpdateEventV2Parser implements HuobiModelParser<OrderUpdateV2Event> {

  @Override
  public OrderUpdateV2Event parse(JSONObject json) {
    return OrderUpdateV2Event.builder()
        .action(json.getString("action"))
        .topic(json.getString("ch"))
        .orderUpdate(new OrderUpdateV2Parser().parse(json.getJSONObject("data")))
        .build();
  }

  @Override
  public OrderUpdateV2Event parse(JSONArray json) {
    return null;
  }

  @Override
  public List<OrderUpdateV2Event> parseArray(JSONArray jsonArray) {
    return null;
  }
}
