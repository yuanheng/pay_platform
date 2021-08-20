package com.bootdo.coin.examples;

import java.math.BigDecimal;
import java.util.List;

import com.bootdo.coin.Constants;
import com.bootdo.coin.constant.enums.DepositWithdrawTypeEnum;
import com.bootdo.coin.model.wallet.DepositAddress;
import com.bootdo.coin.model.wallet.DepositWithdraw;
import com.bootdo.coin.model.wallet.WithdrawAddressResult;
import com.bootdo.coin.service.huobi.HuobiWalletService;
import com.bootdo.coin.client.req.wallet.CreateWithdrawRequest;
import com.bootdo.coin.client.req.wallet.DepositAddressRequest;
import com.bootdo.coin.client.req.wallet.DepositWithdrawRequest;
import com.bootdo.coin.client.req.wallet.WithdrawAddressRequest;
import com.bootdo.coin.client.req.wallet.WithdrawQuotaRequest;
import com.bootdo.coin.constant.HuobiOptions;
import com.bootdo.coin.model.wallet.WithdrawQuota;

public class WalletClientExample {

  public static void main(String[] args) {

    String withdrawAddress = "address";
    String withdrawAddressTag = "tag";

    HuobiWalletService walletService = new HuobiWalletService(HuobiOptions.builder()
        .apiKey(Constants.API_KEY)
        .secretKey(Constants.SECRET_KEY)
        .build());

    List<DepositAddress> addressList = walletService.getDepositAddress(DepositAddressRequest.builder().currency("usdt").build());
    addressList.forEach(depositAddress -> {
      System.out.println(depositAddress.toString());
    });

    WithdrawQuota withdrawQuota = walletService.getWithdrawQuota(WithdrawQuotaRequest.builder().currency("usdt").build());
    System.out.println("currency:"+withdrawQuota.getCurrency());
    withdrawQuota.getChains().forEach(withdrawChainQuota -> {
      System.out.println(withdrawChainQuota.toString());
    });


    long withdrawId = walletService.createWithdraw(CreateWithdrawRequest.builder()
        .address(withdrawAddress)
        .addrTag(withdrawAddressTag)
        .currency("eos")
        .amount(new BigDecimal("1"))
        .fee(new BigDecimal("0.1"))
        .build());

    System.out.println("-----------create withdraw : "+withdrawId+"------------------");

    long res = walletService.cancelWithdraw(withdrawId);
    System.out.println("-----------cancel withdraw : "+res+"------------------");


    List<DepositWithdraw> depositWithdrawList = walletService.getDepositWithdraw(DepositWithdrawRequest.builder()
        .type(DepositWithdrawTypeEnum.WITHDRAW)
        .build());

    depositWithdrawList.forEach(depositWithdraw -> {
      System.out.println(depositWithdraw.toString());
    });

    WithdrawAddressResult withdrawAddressResult = walletService.getWithdrawAddress(WithdrawAddressRequest.builder()
        .currency("usdt")
        .limit(1)
        .build());
    System.out.println(" withdraw address nextId : " + withdrawAddressResult.getNextId());
    withdrawAddressResult.getWithdrawAddressList().forEach(add -> {
      System.out.println(add);
    });

  }
}
