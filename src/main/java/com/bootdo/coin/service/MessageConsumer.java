package com.bootdo.coin.service;

import com.bootdo.app.config.Constants;
import com.bootdo.app.service.impl.AuthManager;
import com.bootdo.app.util.NumberUtil;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.coin.client.TradeClient;
import com.bootdo.coin.constant.HuobiOptions;
import com.bootdo.coin.constant.enums.OrderStateEnum;
import com.bootdo.coin.constant.enums.OrderTypeEnum;
import com.bootdo.coin.domain.CoinTaskInfoDO;
import com.bootdo.coin.domain.Message;
import com.bootdo.coin.enuminfo.CoinTaskStatusEnum;
import com.bootdo.coin.model.trade.Order;
import com.bootdo.system.dao.MemberDao;
import com.bootdo.system.domain.MemberDO;
import com.bootdo.system.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class MessageConsumer {

  private static ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();

  @Autowired
  private  DelayingQueueService delayingQueueService;
  @Autowired
  private RedisUtils redisUtils;

  @Autowired
  private MemberService memberService;
  @Autowired
  private AuthManager authManager;


  /**
   * 定时消费队列中的数据
   * zset会对score进行排序 让最早消费的数据位于最前
   * 拿最前的数据跟当前时间比较 时间到了则消费
   */
  @Scheduled(cron = "*/1 * * * * *")
  public void consumer() throws JsonProcessingException {
    List<Message> msgList = delayingQueueService.pull();
    if (null != msgList) {
      long current = System.currentTimeMillis();

     // msgList.stream().forEach(msg -> {

      //});
      for (Message msg : msgList){
        // 已超时的消息拿出来消费
        if (current >= msg.getDelayTime()) {
          try {
            log.info("消费消息：{}:消息创建时间：{},消费时间：{}", mapper.writeValueAsString(msg), msg.getCreateTime(), LocalDateTime.now());
            String apiKey = msg.getApiKey();
            String secretKey = msg.getSecretKey();
            Long orderId = msg.getOrderId();
            String mid = msg.getMid()+"";
            String taskId = msg.getTaskId() + "";
            TradeClient tradeService = TradeClient.create(HuobiOptions.builder()
                    .apiKey(apiKey)
                    .secretKey(secretKey)
                    .build());

          //  Order order = tradeService.getOrder(orderId);
            Order order = (Order) redisUtils.get(orderId+"");
            String orderKey = Constants.MEMBER_ORDER_PRE.replace("{mid}",mid).replace("{symbol}",order.getSymbol()) + order.getId();
            redisUtils.set(orderKey,order);
            String taskKey = Constants.MEMBER_TASK_PRE.replace("{mid}",mid) + taskId;
            CoinTaskInfoDO coinTaskInfoDO = (CoinTaskInfoDO) redisUtils.get(taskKey);
            //获取当前的订单状态 购买订单
            if (OrderStateEnum.FILLED.getCode().equals(order.getState()) && order.getType().equals(OrderTypeEnum.BUY_LIMIT.getCode())){
              if (coinTaskInfoDO.getCurrentOrderNum() == 0){
                coinTaskInfoDO.setFirstPrice(order.getPrice().toPlainString());
              }
              coinTaskInfoDO.setCurrentOrderNum(coinTaskInfoDO.getCurrentOrderNum() + 1);
              coinTaskInfoDO.setDealNum(coinTaskInfoDO.getDealNum() + 1);
              //算下持仓总额
              String tempAmount  = NumberUtil.multiply(order.getAmount().toPlainString(),order.getPrice().toPlainString());
              coinTaskInfoDO.setTotalAmount(NumberUtil.add(coinTaskInfoDO.getTotalAmount(),tempAmount));

              String tempPosition = order.getAmount().toPlainString();
              coinTaskInfoDO.setTotalPosition(NumberUtil.add(coinTaskInfoDO.getTotalPosition(), tempPosition));
              coinTaskInfoDO.setAvgPrice(NumberUtil.divide(coinTaskInfoDO.getTotalAmount(),coinTaskInfoDO.getTotalPosition()));
              String currentPrice = (String) redisUtils.get(Constants.CURRENT_SYMBOL_PRICE_PRE+ order.getSymbol());
              String tempTotalAmout = NumberUtil.multiply(currentPrice,coinTaskInfoDO.getTotalPosition());
              coinTaskInfoDO.setLastPrice(currentPrice);
              coinTaskInfoDO.setCurrentProfitLossAmount(NumberUtil.subtract(tempTotalAmout, coinTaskInfoDO.getTotalAmount()));
              coinTaskInfoDO.setProfitLossRatio(NumberUtil.divide(coinTaskInfoDO.getCurrentProfitLossAmount(),coinTaskInfoDO.getTotalAmount()));
              if (CoinTaskStatusEnum.DEAILING.toString().equals(coinTaskInfoDO.getStatus())){
                coinTaskInfoDO.setStatus(CoinTaskStatusEnum.ENABLE.toString());
              }
              redisUtils.set(taskKey, coinTaskInfoDO);
            }
            if (OrderStateEnum.FILLED.getCode().equals(order.getState()) && order.getType().equals(OrderTypeEnum.SELL_LIMIT.getCode())){
              BigDecimal tempProfit = order.getProfit();
              if (tempProfit == null) {
                tempProfit = new BigDecimal(100);
              }
              order.setProfit(tempProfit);
              redisUtils.set(orderKey,order);
              //重置任务情况
              coinTaskInfoDO.setCurrentOrderNum(0);
              coinTaskInfoDO.setDealNum(coinTaskInfoDO.getDealNum() + 1);
              coinTaskInfoDO.setTotalAmount("0");
              coinTaskInfoDO.setAvgPrice("0");
              coinTaskInfoDO.setTotalPosition("0");
              coinTaskInfoDO.setCurrentProfitLossAmount("0");
              coinTaskInfoDO.setTotalProfit(NumberUtil.add(coinTaskInfoDO.getTotalProfit(),tempProfit.toPlainString()));

              if (CoinTaskStatusEnum.DEAILING.toString().equals(coinTaskInfoDO.getStatus())){
                coinTaskInfoDO.setStatus(CoinTaskStatusEnum.ENABLE.toString());
              }
              if (CoinTaskStatusEnum.PAUSE_BUY_DEAILING.toString().equals(coinTaskInfoDO.getStatus())) {
                coinTaskInfoDO.setStatus(CoinTaskStatusEnum.CLOSED.toString());
              }
              redisUtils.set(taskKey, coinTaskInfoDO);
            }
            //移除消息
            delayingQueueService.remove(msg);

            if (order.getState().equals(OrderStateEnum.CREATED.getCode()) || order.getState().equals(OrderStateEnum.SUBMITTED.getCode()) || order.getState().equals(OrderStateEnum.PARTIALFILLED.getCode()) ) {
              long time = System.currentTimeMillis();
              msg.setDelayTime(time + (Constants.MEMBER_ORDER_DELAY_TIME * 1000));
              delayingQueueService.push(msg);
            }

            //当前盈利 扣费。。。
            BigDecimal tempP = order.getProfit();
            if (tempP.compareTo(new BigDecimal("0.0")) > 0) {
              //收取费用
              memberService.decreaseAiFreeAmount(msg.getMid(), NumberUtil.multiply(tempP.toPlainString(),"0.2"));
              MemberDO memberDO = new MemberDO();
              memberDO.setId(msg.getMid());
              authManager.refeshMemberInfo(memberDO);
            }

          } catch (JsonProcessingException e) {
            e.printStackTrace();
          }

        }
      }
    }
  }
}
