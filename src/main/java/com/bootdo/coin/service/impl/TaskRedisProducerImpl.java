package com.bootdo.coin.service.impl;

import com.bootdo.app.config.Constants;
import com.bootdo.app.util.DistributedLock;
import com.bootdo.coin.dao.CoinTaskInfoDao;
import com.bootdo.coin.domain.CoinTaskInfoDO;
import com.bootdo.coin.enuminfo.CoinTaskStatusEnum;
import com.bootdo.coin.service.TaskRedisProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskRedisProducerImpl implements TaskRedisProducer {

  private static Logger logger = LoggerFactory.getLogger(TaskRedisProducerImpl.class);

  @Autowired
  private RedisTemplate redisTemplate;

  @Autowired
  private DistributedLock distributedLock;

  @Autowired
  private CoinTaskInfoDao coinTaskInfoDao;

  @Override
  public void producer(CoinTaskInfoDO coinTaskInfoDO) {
    redisTemplate.opsForList().leftPush(Constants.PRODUCT_TASK_INFO_QUEUE, coinTaskInfoDO);
  }



  public void pushTaskInQueue() {
    try {
      if(distributedLock.getLock(Constants.PRODUCT_LOCK_ID)){
        logger.info("fetch product lock....");
        CoinTaskInfoDO params = new CoinTaskInfoDO();
        params.setDeleted(0);
        params.setStatus(CoinTaskStatusEnum.CLOSED.toString());
        List<CoinTaskInfoDO> tasks =  coinTaskInfoDao.listRuningTask(params);
        logger.info("task product size is {}", tasks.size());
        tasks.forEach((task) -> {
          producer(task);
        });
      } else {
        logger.info("unfetch product lock....");
      }
    } finally {
      distributedLock.releaseLock(Constants.PRODUCT_LOCK_ID);
    }
  }



}
