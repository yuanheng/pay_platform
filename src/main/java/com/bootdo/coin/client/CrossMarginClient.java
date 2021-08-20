package com.bootdo.coin.client;

import java.util.List;

import com.bootdo.coin.client.req.crossmargin.CrossMarginApplyLoanRequest;
import com.bootdo.coin.client.req.crossmargin.CrossMarginLoanOrdersRequest;
import com.bootdo.coin.client.req.crossmargin.CrossMarginRepayLoanRequest;
import com.bootdo.coin.client.req.crossmargin.CrossMarginTransferRequest;
import com.bootdo.coin.client.req.crossmargin.GeneralLoanOrdersRequest;
import com.bootdo.coin.client.req.crossmargin.GeneralRepayLoanRequest;
import com.bootdo.coin.constant.enums.ExchangeEnum;
import com.bootdo.coin.model.crossmargin.CrossMarginAccount;
import com.bootdo.coin.model.crossmargin.CrossMarginCurrencyInfo;
import com.bootdo.coin.model.crossmargin.CrossMarginLoadOrder;
import com.bootdo.coin.model.crossmargin.GeneralRepayLoanRecord;
import com.bootdo.coin.model.crossmargin.GeneralRepayLoanResult;
import com.bootdo.coin.service.huobi.HuobiCrossMarginService;
import com.bootdo.coin.constant.Options;
import com.bootdo.coin.exception.SDKException;

public interface CrossMarginClient {

  Long transfer(CrossMarginTransferRequest request);

  Long applyLoan(CrossMarginApplyLoanRequest request);

  void repayLoan(CrossMarginRepayLoanRequest request);

  List<CrossMarginLoadOrder> getLoanOrders(CrossMarginLoanOrdersRequest request);

  CrossMarginAccount getLoanBalance();

  List<CrossMarginCurrencyInfo> getLoanInfo();

  static CrossMarginClient create(Options options) {

    if (options.getExchange().equals(ExchangeEnum.HUOBI)) {
      return new HuobiCrossMarginService(options);
    }
    throw new SDKException(SDKException.INPUT_ERROR, "Unsupport Exchange.");
  }

  List<GeneralRepayLoanResult> repayLoan(GeneralRepayLoanRequest request);

  List<GeneralRepayLoanRecord> getRepaymentLoanRecords(GeneralLoanOrdersRequest request);

}
