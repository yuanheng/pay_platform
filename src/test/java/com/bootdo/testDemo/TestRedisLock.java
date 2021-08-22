package com.bootdo.testDemo;


import com.bootdo.app.util.DistributedLock;
import com.bootdo.app.config.Constants;
import com.bootdo.common.config.Constant;
import com.bootdo.common.redis.shiro.RedisManager;
import com.bootdo.common.utils.JSONUtils;
import com.bootdo.system.domain.StatisticInfoDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisLock {
    @Autowired
    private DistributedLock distributedLock;

    private RedisManager redisManager;

    @Autowired
    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

    @Test
    public void test() {
        distributedLock.releaseLock(Constants.PRODUCT_LOCK_ID);
        System.out.println(distributedLock.getLock(Constants.PRODUCT_LOCK_ID));
        System.out.println(distributedLock.getLock(Constants.PRODUCT_LOCK_ID));
        distributedLock.releaseLock(Constants.PRODUCT_LOCK_ID);
        System.out.println(distributedLock.getLock(Constants.PRODUCT_LOCK_ID));

    }

    @Test
    public void testGetAndSet() {
        distributedLock.releaseLock(Constants.PRODUCT_LOCK_ID);
        System.out.println(distributedLock.getLock(Constants.PRODUCT_LOCK_ID));
        StatisticInfoDO statistics = new StatisticInfoDO().emptyInstance();
        final Long userId = 250L;
        final String mKey = String.format("%s%s", Constant.PREFIX_OF_STAT_CODER, userId);
        System.out.println(mKey);
        redisManager.set(mKey.getBytes(), JSONUtils.beanToJson(statistics).getBytes());

        distributedLock.releaseLock(Constants.PRODUCT_LOCK_ID);
    }


}
