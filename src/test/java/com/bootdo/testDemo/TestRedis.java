package com.bootdo.testDemo;

import com.bootdo.app.util.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

    private RedisUtils redisUtils;

    @Autowired
    public void setRedisUtils(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Test
    public void test() {
        redisUtils.keys("tb_payinfo_*").forEach(e -> {
            System.out.println(e + ", " + redisUtils.lGetListSize(String.valueOf(e)));
        });
    }

}
