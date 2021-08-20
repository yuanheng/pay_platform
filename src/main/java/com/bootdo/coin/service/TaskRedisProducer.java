package com.bootdo.coin.service;

import com.bootdo.coin.domain.CoinTaskInfoDO;

public interface TaskRedisProducer {
  void producer(CoinTaskInfoDO coinTaskInfoDO);
}
