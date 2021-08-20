package com.bootdo.testDemo;


import com.bootdo.app.util.DistributedLock;
import com.bootdo.app.config.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisLock {
  @Autowired
  private DistributedLock distributedLock;

  @Test
  public void test() {
    distributedLock.releaseLock(Constants.PRODUCT_LOCK_ID);
    System.out.println(distributedLock.getLock(Constants.PRODUCT_LOCK_ID));
    System.out.println(distributedLock.getLock(Constants.PRODUCT_LOCK_ID));
    distributedLock.releaseLock(Constants.PRODUCT_LOCK_ID);
    System.out.println(distributedLock.getLock(Constants.PRODUCT_LOCK_ID));

  }



}
