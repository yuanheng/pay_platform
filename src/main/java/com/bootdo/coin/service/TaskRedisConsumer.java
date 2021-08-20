package com.bootdo.coin.service;

import com.bootdo.coin.domain.CoinTaskInfoDO;

public interface TaskRedisConsumer {
  void consumer(CoinTaskInfoDO coinTaskInfoDO);
}
