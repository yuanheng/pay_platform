package com.bootdo.coin.examples;

import java.math.BigDecimal;
import java.util.List;

import com.bootdo.coin.Constants;
import com.bootdo.coin.constant.enums.AccountTransferAccountTypeEnum;
import com.bootdo.coin.constant.enums.AccountTypeEnum;
import com.bootdo.coin.constant.enums.AccountUpdateModeEnum;
import com.bootdo.coin.model.account.Account;
import com.bootdo.coin.model.account.AccountAssetValuationResult;
import com.bootdo.coin.model.account.AccountBalance;
import com.bootdo.coin.model.account.AccountFuturesTransferResult;
import com.bootdo.coin.model.account.AccountHistory;
import com.bootdo.coin.model.account.AccountLedgerResult;
import com.bootdo.coin.model.account.AccountTransferResult;
import com.bootdo.coin.model.account.Point;
import com.bootdo.coin.model.account.PointTransferResult;
import com.bootdo.coin.client.AccountClient;
import com.bootdo.coin.client.req.account.AccountAssetValuationRequest;
import com.bootdo.coin.client.req.account.AccountBalanceRequest;
import com.bootdo.coin.client.req.account.AccountFuturesTransferRequest;
import com.bootdo.coin.client.req.account.AccountHistoryRequest;
import com.bootdo.coin.client.req.account.AccountLedgerRequest;
import com.bootdo.coin.client.req.account.AccountTransferRequest;
import com.bootdo.coin.client.req.account.PointRequest;
import com.bootdo.coin.client.req.account.PointTransferRequest;
import com.bootdo.coin.client.req.account.SubAccountUpdateRequest;
import com.bootdo.coin.constant.HuobiOptions;
import com.bootdo.coin.constant.enums.AccountFuturesTransferTypeEnum;

public class AccountClientExample {

  public static void main(String[] args) {

    Long accountId = 9616660L;
    AccountClient accountService = AccountClient.create(HuobiOptions.builder()
        .apiKey(Constants.API_KEY)
        .secretKey(Constants.SECRET_KEY)
        .build());

    List<Account> accountList = accountService.getAccounts();
    accountList.forEach(account -> {
      System.out.println(account.toString());
    });


    AccountBalance accountBalance = accountService.getAccountBalance(AccountBalanceRequest.builder()
        .accountId(accountId)
        .build());

    System.out.println(accountBalance.getId());
    System.out.println(accountBalance.getType());
    System.out.println(accountBalance.getState());
    accountBalance.getList().forEach(balance -> {
      System.out.println(balance.toString());
    });

    List<AccountHistory> historyList = accountService.getAccountHistory(AccountHistoryRequest.builder().accountId(accountId).build());
    historyList.forEach(history->{
      System.out.println(history);
    });

    AccountLedgerResult accountLedgerResult = accountService.getAccountLedger(AccountLedgerRequest.builder()
        .accountId(accountId)
        .limit(2)
        .build());
    System.out.println("leger nextId: " + accountLedgerResult.getNextId());
    accountLedgerResult.getLedgerList().forEach(ledger -> {
      System.out.println(ledger);
    });


    accountService.subAccountsUpdate(SubAccountUpdateRequest.builder()
        .accountUpdateMode(AccountUpdateModeEnum.ACCOUNT_CHANGE).build(), event -> {
      System.out.println(event.toString());
    });


    AccountTransferResult accountTransferResult = accountService.accountTransfer(AccountTransferRequest.builder()
        .fromUser(123L)
        .fromAccount(456L)
        .fromAccountType(AccountTransferAccountTypeEnum.SPOT)
        .toUser(678L)
        .toAccount(789L)
        .toAccountType(AccountTransferAccountTypeEnum.MARGIN)
        .currency("usdt")
        .amount(new BigDecimal("10"))
        .build());

    System.out.println("account transfer result:"+accountTransferResult.toString());

    AccountFuturesTransferResult accountFuturesTransferResult = accountService.accountFuturesTransfer(AccountFuturesTransferRequest.builder()
        .currency("xrp")
        .amount(new BigDecimal("5"))
        .type(AccountFuturesTransferTypeEnum.PRO_TO_FUTURES)
        .build());

    System.out.println("account futures result:"+accountFuturesTransferResult.toString());

    Point point = accountService.getPoint(PointRequest.builder().build());
    System.out.println("get point: " + point);


    PointTransferResult pointTransferResult = accountService.pointTransfer(PointTransferRequest.builder()
      .fromUid(123L)
      .toUid(123L)
      .groupId(123L)
      .amount(BigDecimal.ONE)
      .build());
    System.out.println(pointTransferResult);

    AccountAssetValuationResult accountAssetValuationResult = accountService.accountAssetValuation(AccountAssetValuationRequest.builder().accountType(AccountTypeEnum.SPOT).build());
    System.out.println(accountAssetValuationResult);


  }

}
