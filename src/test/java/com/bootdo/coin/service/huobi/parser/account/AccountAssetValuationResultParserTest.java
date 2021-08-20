package com.bootdo.coin.service.huobi.parser.account;

import com.alibaba.fastjson.JSON;
import com.bootdo.coin.model.account.AccountAssetValuationResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AccountAssetValuationResultParserTest {
  public static final String JSON_STRING = "{\n"
    + "        \"balance\": \"34.75\",\n"
    + "        \"timestamp\": 1594901254363\n"
    + "    }";

  private AccountAssetValuationResultParser parser;

  @Before
  public void init() {
    parser = new AccountAssetValuationResultParser();
  }

  @Test
  public void test_parse(){
    AccountAssetValuationResult result = parser.parse(JSON.parseObject(JSON_STRING));
    Assert.assertNotNull(result);
    Assert.assertNotNull(result.getBalance());
  }
}
