package com.bootdo.coin.client;

import java.util.List;

import com.bootdo.coin.client.req.wallet.CreateWithdrawRequest;
import com.bootdo.coin.client.req.wallet.DepositAddressRequest;
import com.bootdo.coin.client.req.wallet.DepositWithdrawRequest;
import com.bootdo.coin.client.req.wallet.WithdrawAddressRequest;
import com.bootdo.coin.client.req.wallet.WithdrawQuotaRequest;
import com.bootdo.coin.constant.enums.ExchangeEnum;
import com.bootdo.coin.model.wallet.DepositAddress;
import com.bootdo.coin.model.wallet.DepositWithdraw;
import com.bootdo.coin.model.wallet.WithdrawAddressResult;
import com.bootdo.coin.service.huobi.HuobiWalletService;
import com.bootdo.coin.constant.Options;
import com.bootdo.coin.exception.SDKException;
import com.bootdo.coin.model.wallet.WithdrawQuota;

public interface WalletClient {

  List<DepositAddress> getDepositAddress(DepositAddressRequest request);

  WithdrawQuota getWithdrawQuota(WithdrawQuotaRequest request);

  WithdrawAddressResult getWithdrawAddress(WithdrawAddressRequest request);

  Long createWithdraw(CreateWithdrawRequest request);

  Long cancelWithdraw(Long withdrawId);

  List<DepositWithdraw> getDepositWithdraw(DepositWithdrawRequest request);

  static WalletClient create(Options options) {

    if (options.getExchange().equals(ExchangeEnum.HUOBI)) {
      return new HuobiWalletService(options);
    }
    throw new SDKException(SDKException.INPUT_ERROR, "Unsupport Exchange.");
  }
}
