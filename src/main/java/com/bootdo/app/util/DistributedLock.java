package com.bootdo.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class DistributedLock {
  @Autowired
  RedisTemplate redisTemplate;

  /**
   * 获得锁
   */
  public boolean getLock(String lockId) {
    Boolean success = redisTemplate.opsForValue().setIfAbsent("lock:"+lockId, "");
    return success != null && success;
  }

  public void releaseLock(String lockId) {
    redisTemplate.delete("lock:"+lockId);
  }
}
