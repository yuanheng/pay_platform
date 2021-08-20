package com.bootdo.coin.client;

import java.util.List;

import com.bootdo.coin.client.req.margin.IsolatedMarginAccountRequest;
import com.bootdo.coin.client.req.margin.IsolatedMarginApplyLoanRequest;
import com.bootdo.coin.client.req.margin.IsolatedMarginLoanInfoRequest;
import com.bootdo.coin.client.req.margin.IsolatedMarginLoanOrdersRequest;
import com.bootdo.coin.client.req.margin.IsolatedMarginRepayLoanRequest;
import com.bootdo.coin.client.req.margin.IsolatedMarginTransferRequest;
import com.bootdo.coin.constant.enums.ExchangeEnum;
import com.bootdo.coin.model.isolatedmargin.IsolatedMarginAccount;
import com.bootdo.coin.model.isolatedmargin.IsolatedMarginLoadOrder;
import com.bootdo.coin.model.isolatedmargin.IsolatedMarginSymbolInfo;
import com.bootdo.coin.service.huobi.HuobiIsolatedMarginService;
import com.bootdo.coin.constant.Options;
import com.bootdo.coin.exception.SDKException;

public interface IsolatedMarginClient {

  Long transfer(IsolatedMarginTransferRequest request);

  Long applyLoan(IsolatedMarginApplyLoanRequest request);

  Long repayLoan(IsolatedMarginRepayLoanRequest request);

  List<IsolatedMarginLoadOrder> getLoanOrders(IsolatedMarginLoanOrdersRequest request);

  List<IsolatedMarginAccount> getLoanBalance(IsolatedMarginAccountRequest request);

  List<IsolatedMarginSymbolInfo> getLoanInfo(IsolatedMarginLoanInfoRequest request);

  static IsolatedMarginClient create(Options options) {

    if (options.getExchange().equals(ExchangeEnum.HUOBI)) {
      return new HuobiIsolatedMarginService(options);
    }
    throw new SDKException(SDKException.INPUT_ERROR, "Unsupport Exchange.");
  }
}
