package com.bootdo.coin.test;

import java.math.BigDecimal;

import com.bootdo.coin.Constants;
import com.bootdo.coin.constant.enums.CandlestickIntervalEnum;
import com.bootdo.coin.constant.enums.DepthStepEnum;
import com.bootdo.coin.client.AccountClient;
import com.bootdo.coin.client.GenericClient;
import com.bootdo.coin.client.MarketClient;
import com.bootdo.coin.client.TradeClient;
import com.bootdo.coin.client.WalletClient;
import com.bootdo.coin.client.req.account.AccountBalanceRequest;
import com.bootdo.coin.client.req.generic.CurrencyChainsRequest;
import com.bootdo.coin.client.req.market.CandlestickRequest;
import com.bootdo.coin.client.req.market.MarketDepthRequest;
import com.bootdo.coin.client.req.market.MarketDetailRequest;
import com.bootdo.coin.client.req.market.MarketTradeRequest;
import com.bootdo.coin.client.req.trade.CreateOrderRequest;
import com.bootdo.coin.client.req.trade.FeeRateRequest;
import com.bootdo.coin.client.req.wallet.DepositAddressRequest;
import com.bootdo.coin.client.req.wallet.WithdrawAddressRequest;
import com.bootdo.coin.constant.HuobiOptions;
import com.bootdo.coin.constant.enums.DepthSizeEnum;
import com.bootdo.coin.utils.ConnectionFactory;
import com.bootdo.coin.utils.ConnectionFactory.NetworkLatency;

public class PerformanceTest {


  public static void main(String[] args) {

    ConnectionFactory.setLatencyDebug();

    for (int i = 0; i < 10; i++) {

      testCase();
      System.out.println("======================================");
    }

  }


  public static void testCase() {
    String symbol = "htusdt";
    String currency = "usdt";
    Long accountId = 290082L;
    GenericClient genericClient = GenericClient.create(new HuobiOptions());
    MarketClient marketClient = MarketClient.create(new HuobiOptions());
    AccountClient accountClient = AccountClient.create(HuobiOptions.builder().apiKey(Constants.API_KEY).secretKey(Constants.SECRET_KEY).build());
    WalletClient walletClient = WalletClient.create(HuobiOptions.builder().apiKey(Constants.API_KEY).secretKey(Constants.SECRET_KEY).build());
    TradeClient tradeClient = TradeClient.create(HuobiOptions.builder().apiKey(Constants.API_KEY).secretKey(Constants.SECRET_KEY).build());
    Long startNano = null;
    Long endNano = null;
    NetworkLatency networkLatency = null;

    // /v1/common/timestamp
    startNano = System.nanoTime();
    genericClient.getTimestamp();
    endNano = System.nanoTime();
    networkLatency = ConnectionFactory.getLatencyDebugQueue().poll();
    print(networkLatency, startNano, endNano);

    // currencies
    startNano = System.nanoTime();
    genericClient.getCurrencyChains(CurrencyChainsRequest.builder().currency(currency).build());
    endNano = System.nanoTime();
    networkLatency = ConnectionFactory.getLatencyDebugQueue().poll();
    print(networkLatency, startNano, endNano);


    // /market/trade
    startNano = System.nanoTime();
    marketClient.getMarketTrade(MarketTradeRequest.builder().symbol(symbol).build());
    endNano = System.nanoTime();
    networkLatency = ConnectionFactory.getLatencyDebugQueue().poll();
    print(networkLatency, startNano, endNano);

    // /market/depth
    startNano = System.nanoTime();
    marketClient.getMarketDepth(MarketDepthRequest.builder().symbol(symbol).step(DepthStepEnum.STEP0).depth(DepthSizeEnum.SIZE_5).build());
    endNano = System.nanoTime();
    networkLatency = ConnectionFactory.getLatencyDebugQueue().poll();
    print(networkLatency, startNano, endNano);

    // /market/kline
    startNano = System.nanoTime();
    marketClient.getCandlestick(CandlestickRequest.builder().symbol(symbol).interval(CandlestickIntervalEnum.MIN15).size(5).build());
    endNano = System.nanoTime();
    networkLatency = ConnectionFactory.getLatencyDebugQueue().poll();
    print(networkLatency, startNano, endNano);

    // /market/detail
    startNano = System.nanoTime();
    marketClient.getMarketDetail(MarketDetailRequest.builder().symbol(symbol).build());
    endNano = System.nanoTime();
    networkLatency = ConnectionFactory.getLatencyDebugQueue().poll();
    print(networkLatency, startNano, endNano);

    // /v1/account/accounts
    startNano = System.nanoTime();
    accountClient.getAccounts();
    endNano = System.nanoTime();
    networkLatency = ConnectionFactory.getLatencyDebugQueue().poll();
    print(networkLatency, startNano, endNano);

    // /v1/account/accounts/{account-id}/balance
    startNano = System.nanoTime();
    accountClient.getAccountBalance(AccountBalanceRequest.builder().accountId(accountId).build());
    endNano = System.nanoTime();
    networkLatency = ConnectionFactory.getLatencyDebugQueue().poll();
    print(networkLatency, startNano, endNano);

    // /v2/account/deposit/address
    startNano = System.nanoTime();
    walletClient.getDepositAddress(DepositAddressRequest.builder().currency(currency).build());
    endNano = System.nanoTime();
    networkLatency = ConnectionFactory.getLatencyDebugQueue().poll();
    print(networkLatency, startNano, endNano);

    // /v2/account/withdraw/address
    startNano = System.nanoTime();
    walletClient.getWithdrawAddress(WithdrawAddressRequest.builder().currency(currency).build());
    endNano = System.nanoTime();
    networkLatency = ConnectionFactory.getLatencyDebugQueue().poll();
    print(networkLatency, startNano, endNano);

    // 下单
    startNano = System.nanoTime();
    Long orderId = tradeClient.createOrder(CreateOrderRequest.spotBuyLimit(accountId, symbol, new BigDecimal("3"), new BigDecimal("2")));
    endNano = System.nanoTime();
    networkLatency = ConnectionFactory.getLatencyDebugQueue().poll();
    print(networkLatency, startNano, endNano);

    // 撤单
    startNano = System.nanoTime();
    tradeClient.cancelOrder(orderId);
    endNano = System.nanoTime();
    networkLatency = ConnectionFactory.getLatencyDebugQueue().poll();
    print(networkLatency, startNano, endNano);

    // 查询订单
    startNano = System.nanoTime();
    tradeClient.getOrder(orderId);
    endNano = System.nanoTime();
    networkLatency = ConnectionFactory.getLatencyDebugQueue().poll();
    print(networkLatency, startNano, endNano);

    // 查询成交明细
    startNano = System.nanoTime();
    tradeClient.getMatchResult(orderId);
    endNano = System.nanoTime();
    networkLatency = ConnectionFactory.getLatencyDebugQueue().poll();
    print(networkLatency, startNano, endNano);

    // 手续费率
    startNano = System.nanoTime();
    tradeClient.getFeeRate(FeeRateRequest.builder().symbols(symbol).build());
    endNano = System.nanoTime();
    networkLatency = ConnectionFactory.getLatencyDebugQueue().poll();
    print(networkLatency, startNano, endNano);
  }



  public static void print(NetworkLatency networkLatency, Long startNanoTime, Long endNanoTime) {

    long nanoToMicrosecond = 1000;

    Long prepareCost = (networkLatency.getStartNanoTime() - startNanoTime) / nanoToMicrosecond;
    Long deserializationCost = (endNanoTime - networkLatency.getEndNanoTime()) / nanoToMicrosecond;
    Long networkCost = (networkLatency.getEndNanoTime() - networkLatency.getStartNanoTime()) / nanoToMicrosecond;
    Long totalCost = (endNanoTime - startNanoTime) / nanoToMicrosecond;
    Long innerCost = (totalCost - networkCost);

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("path:").append(networkLatency.getPath())
        .append(" prepare:").append(prepareCost)
        .append(" deserializtion:").append(deserializationCost)
        .append(" network:").append(networkCost)
        .append(" inner:").append(innerCost)
        .append(" total:").append(totalCost);

//    stringBuilder.append("|").append(networkLatency.getPath())
//        .append(" |").append(prepareCost)
//        .append(" |").append(deserializationCost)
//        .append(" |").append(networkCost)
//        .append(" |").append(innerCost)
//        .append(" |").append(totalCost);

    System.out.println(stringBuilder.toString());


  }

}
