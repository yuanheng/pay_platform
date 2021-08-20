package com.bootdo.coin.service.huobi.parser.subuser;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bootdo.coin.model.subuser.GetSubUserAccountListResult;
import com.bootdo.coin.service.huobi.parser.HuobiModelParser;

public class GetSubUserAccountListResultParser implements HuobiModelParser<GetSubUserAccountListResult> {

  @Override
  public GetSubUserAccountListResult parse(JSONObject json) {
    return json.toJavaObject(GetSubUserAccountListResult.class);
  }

  @Override
  public GetSubUserAccountListResult parse(JSONArray json) {
    return null;
  }

  @Override
  public List<GetSubUserAccountListResult> parseArray(JSONArray jsonArray) {
    return null;
  }
}
