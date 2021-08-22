package com.bootdo.app.util;

import com.bootdo.app.config.Constants;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
  public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
    super(listenerContainer);
  }

  /**
   * Redis-key失效监听事件，所有key 失效都会走此方法
   *
   * @param message
   * @param pattern
   */
  @Override
  public void onMessage(Message message, byte[] pattern) {
    //  获取失效的key
    String expiredKey = message.toString();
    if (expiredKey.indexOf(Constants.ORDER_NO_TIMEOUT_KEY) != -1) {
      String[] orderOn = expiredKey.split(":");
      System.out.println("订单号: "+orderOn[1] +"超时"  );
    }
    System.out.println(expiredKey);
  }

}
