package com.bootdo.coin.service;

import com.bootdo.coin.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Component
public class MessageProvider {

  @Autowired
  private DelayingQueueService delayingQueueService;

  private static String MEMBER_ORDER_CHANNEL = "MEMBER_ORDER_CHANNEL";

  /**
   * 发送消息
   *
   * @param
   */
  public void sendMessage(String apiKey, String secretKey, Integer mid, Long orderId, Integer taskId, long delay) {
    try {
      if (apiKey != null) {
        String seqId = UUID.randomUUID().toString();
        Message message = new Message();
        //时间戳默认为毫秒 延迟5s即为 5*1000
        long time = System.currentTimeMillis();
        LocalDateTime dateTime = Instant.ofEpochMilli(time).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        message.setApiKey(apiKey);
        message.setSecretKey(secretKey);
        message.setMid(mid);
        message.setOrderId(orderId);
        message.setTaskId(taskId);
        message.setDelayTime(time + (delay * 1000));
        message.setCreateTime(dateTime);
        message.setBody("");
        message.setId(seqId);
        message.setChannel(MEMBER_ORDER_CHANNEL);
        delayingQueueService.push(message);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
