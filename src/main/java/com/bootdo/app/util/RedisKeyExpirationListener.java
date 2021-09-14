package com.bootdo.app.util;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.app.config.Constants;
import com.bootdo.app.zwlenum.OrderStatusEnum;
import com.bootdo.app.zwlenum.StatusEnum;
import com.bootdo.system.domain.OrderDO;
import com.bootdo.system.domain.TbOrderDO;
import com.bootdo.system.service.OrderService;
import com.bootdo.system.service.TbOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
  @Autowired
  private OrderService orderService;
  @Autowired
  private TbOrderService tbOrderService;

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
      String[] orderNo = expiredKey.split(":");
      String temp = orderNo[1];
      OrderDO tempOrderDO = orderService.getOrderByNo(temp);
      if (tempOrderDO.getStatus().equals(OrderStatusEnum.PRE_PAY.getKey())) {
        String paymentInfo = tempOrderDO.getPaymentInfo();
        JSONObject payJson = JSONObject.parseObject(paymentInfo);
        Integer tbOrderId = payJson.getInteger("id");
        TbOrderDO tempTbOrder = new TbOrderDO();
        tempTbOrder.setId(tbOrderId);
        tempTbOrder.setStatus(StatusEnum.DISABLE.getKey());
        tempTbOrder.setEndTime(new Date());
        tbOrderService.update(tempTbOrder);
      }
      System.out.println(expiredKey);
    }

  }

}

