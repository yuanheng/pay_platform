package com.bootdo.coin.service.huobi.parser.account;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bootdo.coin.model.account.AccountLedger;
import com.bootdo.coin.service.huobi.parser.HuobiModelParser;

public class AccountLedgerParser implements HuobiModelParser<AccountLedger> {

  @Override
  public AccountLedger parse(JSONObject json) {
    return null;
  }

  @Override
  public AccountLedger parse(JSONArray json) {
    return null;
  }

  @Override
  public List<AccountLedger> parseArray(JSONArray jsonArray) {
    return jsonArray.toJavaList(AccountLedger.class);
  }
}
