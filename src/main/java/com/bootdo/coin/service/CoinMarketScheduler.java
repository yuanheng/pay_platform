package com.bootdo.coin.service;

import com.bootdo.app.config.Constants;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.coin.client.MarketClient;
import com.bootdo.coin.model.market.MarketTicker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Configuration
@EnableScheduling
public class CoinMarketScheduler {

    @Autowired
    private MarketClient marketClient;
    @Autowired
    private RedisUtils redisUtils;


    private static Logger logger = LoggerFactory.getLogger(CoinMarketScheduler.class);

    @Scheduled(cron = "*/30 * * * * *")
    public void getMarkteCoin() {
        List<String> coins = (List<String>)redisUtils.get(Constants.COIN_LIST);
        List<MarketTicker> tickerList = marketClient.getTickers();
        tickerList.forEach(marketTicker -> {
            String symbol =  marketTicker.getSymbol();
            if (coins.contains(symbol.toUpperCase())) {
                BigDecimal bigDecimal = marketTicker.getBid();
                redisUtils.set(Constants.CURRENT_SYMBOL_PRICE_PRE + symbol.toUpperCase(), bigDecimal.toPlainString());
                logger.info("symbol={},price={}", symbol,bigDecimal.toPlainString());
            }

        });
    }

}
