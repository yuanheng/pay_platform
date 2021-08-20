package com.bootdo.coin.service.huobi.parser.trade;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bootdo.coin.model.trade.BatchCancelOpenOrdersResult;
import com.bootdo.coin.service.huobi.parser.HuobiModelParser;

public class BatchCancelOpenOrdersResultParser implements HuobiModelParser<BatchCancelOpenOrdersResult> {

  @Override
  public BatchCancelOpenOrdersResult parse(JSONObject json) {
    return BatchCancelOpenOrdersResult.builder()
        .successCount(json.getInteger("success-count"))
        .failedCount(json.getInteger("failed-count"))
        .nextId(json.getLong("next-id"))
        .build();
  }

  @Override
  public BatchCancelOpenOrdersResult parse(JSONArray json) {
    return null;
  }

  @Override
  public List<BatchCancelOpenOrdersResult> parseArray(JSONArray jsonArray) {
    return null;
  }
}
