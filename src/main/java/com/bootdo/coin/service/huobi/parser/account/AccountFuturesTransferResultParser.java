package com.bootdo.coin.service.huobi.parser.account;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bootdo.coin.model.account.AccountFuturesTransferResult;
import com.bootdo.coin.service.huobi.parser.HuobiModelParser;

public class AccountFuturesTransferResultParser implements HuobiModelParser<AccountFuturesTransferResult> {

  @Override
  public AccountFuturesTransferResult parse(JSONObject json) {
    return AccountFuturesTransferResult.builder().data(json.getLong("data")).build();
  }

  @Override
  public AccountFuturesTransferResult parse(JSONArray json) {
    return null;
  }

  @Override
  public List<AccountFuturesTransferResult> parseArray(JSONArray jsonArray) {
    return null;
  }
}
