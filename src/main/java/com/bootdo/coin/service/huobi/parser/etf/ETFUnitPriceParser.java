package com.bootdo.coin.service.huobi.parser.etf;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bootdo.coin.service.huobi.parser.HuobiModelParser;
import com.bootdo.coin.model.etf.ETFUnitPrice;

public class ETFUnitPriceParser implements HuobiModelParser<ETFUnitPrice> {

  @Override
  public ETFUnitPrice parse(JSONObject json) {
    return json.toJavaObject(ETFUnitPrice.class);
  }

  @Override
  public ETFUnitPrice parse(JSONArray json) {
    return null;
  }

  @Override
  public List<ETFUnitPrice> parseArray(JSONArray jsonArray) {
    if (jsonArray == null || jsonArray.size() <= 0) {
      return new ArrayList<>();
    }
    return jsonArray.toJavaList(ETFUnitPrice.class);
  }
}
