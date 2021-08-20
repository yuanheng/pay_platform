package com.bootdo.coin.constant;

import com.bootdo.coin.constant.enums.ExchangeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class HuobiOptions implements Options {

  @Builder.Default
  private String restHost = "https://api.huobi.li";

  @Builder.Default
  private String websocketHost = "wss://api.huobi.li";

  private String apiKey;

  private String secretKey;

  @Builder.Default
  private boolean websocketAutoConnect = true;

  @Override
  public String getApiKey() {
    return this.apiKey;
  }

  @Override
  public String getSecretKey() {
    return this.secretKey;
  }

  @Override
  public ExchangeEnum getExchange() {
    return ExchangeEnum.HUOBI;
  }

  @Override
  public String getRestHost() {
    return this.restHost;
  }

  @Override
  public String getWebSocketHost() {
    return this.websocketHost;
  }

  @Override
  public boolean isWebSocketAutoConnect() {
    return this.websocketAutoConnect;
  }

}
