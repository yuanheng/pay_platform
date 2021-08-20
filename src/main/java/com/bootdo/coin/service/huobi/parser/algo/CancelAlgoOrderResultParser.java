package com.bootdo.coin.service.huobi.parser.algo;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bootdo.coin.service.huobi.parser.HuobiModelParser;
import com.bootdo.coin.model.algo.CancelAlgoOrderResult;

public class CancelAlgoOrderResultParser implements HuobiModelParser<CancelAlgoOrderResult> {

  @Override
  public CancelAlgoOrderResult parse(JSONObject json) {
    return json.toJavaObject(CancelAlgoOrderResult.class);
  }

  @Override
  public CancelAlgoOrderResult parse(JSONArray json) {
    return null;
  }

  @Override
  public List<CancelAlgoOrderResult> parseArray(JSONArray jsonArray) {
    return null;
  }
}
