package com.bootdo.coin.service.huobi.parser.crossmargin;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bootdo.coin.model.crossmargin.CrossMarginAccount;
import com.bootdo.coin.service.huobi.parser.account.BalanceParser;
import com.bootdo.coin.service.huobi.parser.HuobiModelParser;

public class CrossMarginAccountParser implements HuobiModelParser<CrossMarginAccount> {

  @Override
  public CrossMarginAccount parse(JSONObject json) {
    return CrossMarginAccount.builder()
        .id(json.getLong("id"))
        .type(json.getString("type"))
        .state(json.getString("state"))
        .riskRate(json.getBigDecimal("risk-rate"))
        .acctBalanceSum(json.getBigDecimal("acct-balance-sum"))
        .debtBalanceSum(json.getBigDecimal("debt-balance-sum"))
        .balanceList(new BalanceParser().parseArray(json.getJSONArray("list")))
        .build();
  }

  @Override
  public CrossMarginAccount parse(JSONArray json) {
    return null;
  }

  @Override
  public List<CrossMarginAccount> parseArray(JSONArray jsonArray) {
    if (jsonArray == null | jsonArray.size() <= 0) {
      return new ArrayList<>();
    }

    List<CrossMarginAccount> list = new ArrayList<>();
    for (int i = 0; i < jsonArray.size(); i++) {
      list.add(parse(jsonArray.getJSONObject(i)));
    }
    return list;
  }
}
