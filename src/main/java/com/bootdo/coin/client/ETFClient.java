package com.bootdo.coin.client;

import java.util.List;

import com.bootdo.coin.client.req.etf.ETFSwapListRequest;
import com.bootdo.coin.client.req.etf.ETFSwapRequest;
import com.bootdo.coin.model.etf.ETFConfig;
import com.bootdo.coin.model.etf.ETFSwapRecord;

public interface ETFClient {

  ETFConfig getConfig(String etfName);

  void etfSwap(ETFSwapRequest request);

  List<ETFSwapRecord> getEtfSwapList(ETFSwapListRequest request);

}
