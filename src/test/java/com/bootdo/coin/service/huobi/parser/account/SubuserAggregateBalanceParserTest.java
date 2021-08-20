package com.bootdo.coin.service.huobi.parser.account;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.bootdo.coin.model.account.SubuserAggregateBalance;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class SubuserAggregateBalanceParserTest {

  public static final String JSON_ARRAY_STRING = "[{\"currency\":\"usdt\",\"balance\":\"0\",\"type\":\"spot\"}]";

  private SubuserAggregateBalanceParser parser;

  @Before
  public void init() {
    parser = new SubuserAggregateBalanceParser();
  }

  @Test
  public void test_array_parse(){

    List<SubuserAggregateBalance> subuserAggregateBalanceList = parser.parseArray(JSON.parseArray(JSON_ARRAY_STRING));
    Assert.assertNotNull(subuserAggregateBalanceList);
    Assert.assertTrue(subuserAggregateBalanceList.size() == 1);
  }
}
