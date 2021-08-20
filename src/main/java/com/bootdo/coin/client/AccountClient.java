package com.bootdo.coin.client;

import java.util.List;

import com.bootdo.coin.client.req.account.AccountAssetValuationRequest;
import com.bootdo.coin.client.req.account.AccountBalanceRequest;
import com.bootdo.coin.client.req.account.AccountFuturesTransferRequest;
import com.bootdo.coin.client.req.account.AccountHistoryRequest;
import com.bootdo.coin.client.req.account.AccountLedgerRequest;
import com.bootdo.coin.client.req.account.AccountTransferRequest;
import com.bootdo.coin.client.req.account.PointRequest;
import com.bootdo.coin.client.req.account.PointTransferRequest;
import com.bootdo.coin.client.req.account.SubAccountUpdateRequest;
import com.bootdo.coin.constant.Options;
import com.bootdo.coin.constant.enums.ExchangeEnum;
import com.bootdo.coin.model.account.Account;
import com.bootdo.coin.model.account.AccountAssetValuationResult;
import com.bootdo.coin.model.account.AccountBalance;
import com.bootdo.coin.model.account.AccountFuturesTransferResult;
import com.bootdo.coin.model.account.AccountHistory;
import com.bootdo.coin.model.account.AccountLedgerResult;
import com.bootdo.coin.model.account.AccountTransferResult;
import com.bootdo.coin.model.account.AccountUpdateEvent;
import com.bootdo.coin.model.account.Point;
import com.bootdo.coin.model.account.PointTransferResult;
import com.bootdo.coin.service.huobi.HuobiAccountService;
import com.bootdo.coin.utils.ResponseCallback;
import com.bootdo.coin.exception.SDKException;

public interface AccountClient {

  /**
   * Get User Account List
   * @return
   */
  List<Account> getAccounts();

  /**
   * Get User Account Balance
   * @param request
   * @return
   */
  AccountBalance getAccountBalance(AccountBalanceRequest request);

  List<AccountHistory> getAccountHistory(AccountHistoryRequest request);

  AccountLedgerResult getAccountLedger(AccountLedgerRequest request);

  AccountTransferResult accountTransfer(AccountTransferRequest request);

  AccountFuturesTransferResult accountFuturesTransfer(AccountFuturesTransferRequest request);

  Point getPoint(PointRequest request);

  PointTransferResult pointTransfer(PointTransferRequest request);

  AccountAssetValuationResult accountAssetValuation(AccountAssetValuationRequest request);

  void subAccountsUpdate(SubAccountUpdateRequest request, ResponseCallback<AccountUpdateEvent> callback);

  static AccountClient create(Options options) {

    if (options.getExchange().equals(ExchangeEnum.HUOBI)) {
      return new HuobiAccountService(options);
    }
    throw new SDKException(SDKException.INPUT_ERROR, "Unsupport Exchange.");
  }
}
