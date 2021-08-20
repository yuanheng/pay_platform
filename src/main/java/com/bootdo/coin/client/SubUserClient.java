package com.bootdo.coin.client;

import java.util.List;

import com.bootdo.coin.client.req.account.TransferSubuserRequest;
import com.bootdo.coin.client.req.subuser.GetApiKeyListRequest;
import com.bootdo.coin.client.req.subuser.GetSubUserAccountListRequest;
import com.bootdo.coin.client.req.subuser.GetSubUserDepositRequest;
import com.bootdo.coin.client.req.subuser.GetSubUserListRequest;
import com.bootdo.coin.client.req.subuser.SubUserApiKeyDeletionRequest;
import com.bootdo.coin.client.req.subuser.SubUserApiKeyGenerationRequest;
import com.bootdo.coin.client.req.subuser.SubUserApiKeyModificationRequest;
import com.bootdo.coin.client.req.subuser.SubUserCreationRequest;
import com.bootdo.coin.client.req.subuser.SubUserManagementRequest;
import com.bootdo.coin.client.req.subuser.SubUserTradableMarketRequest;
import com.bootdo.coin.client.req.subuser.SubUserTransferabilityRequest;
import com.bootdo.coin.constant.Options;
import com.bootdo.coin.constant.enums.ExchangeEnum;
import com.bootdo.coin.model.account.AccountBalance;
import com.bootdo.coin.model.account.SubuserAggregateBalance;
import com.bootdo.coin.model.subuser.GetApiKeyListResult;
import com.bootdo.coin.model.subuser.GetSubUserAccountListResult;
import com.bootdo.coin.model.subuser.GetSubUserDepositResult;
import com.bootdo.coin.model.subuser.GetSubUserListResult;
import com.bootdo.coin.model.subuser.SubUserApiKeyGenerationResult;
import com.bootdo.coin.model.subuser.SubUserApiKeyModificationResult;
import com.bootdo.coin.model.subuser.SubUserCreationInfo;
import com.bootdo.coin.model.subuser.SubUserManagementResult;
import com.bootdo.coin.model.subuser.SubUserState;
import com.bootdo.coin.model.subuser.SubUserTradableMarketResult;
import com.bootdo.coin.model.subuser.SubUserTransferabilityResult;
import com.bootdo.coin.model.wallet.DepositAddress;
import com.bootdo.coin.service.huobi.HuobiSubUserService;
import com.bootdo.coin.exception.SDKException;

public interface SubUserClient {


  List<SubUserCreationInfo> subuserCreation(SubUserCreationRequest request);


  GetSubUserListResult getSubUserList(GetSubUserListRequest request);

  SubUserState getSubuserState(Long subUid);

  SubUserManagementResult subuserManagement(SubUserManagementRequest request);

  GetSubUserAccountListResult getSubuserAccountList(GetSubUserAccountListRequest request);

  SubUserTransferabilityResult subuserTransferability(SubUserTransferabilityRequest request);

  SubUserTradableMarketResult subuserTradableMarket(SubUserTradableMarketRequest request);

  SubUserApiKeyGenerationResult subuserApiKeyGeneration(SubUserApiKeyGenerationRequest request);

  SubUserApiKeyModificationResult subuserApiKeyModification(SubUserApiKeyModificationRequest request);

  void subuserApiKeyDeletion(SubUserApiKeyDeletionRequest request);

  GetApiKeyListResult getApiKeyList(GetApiKeyListRequest request);

  List<DepositAddress> getSubUserDepositAddress(Long subUid, String currency);

  GetSubUserDepositResult getSubUserDeposit(GetSubUserDepositRequest request);
  /**
   * Transfer to sub-user
   * @param request
   * @return
   */
  long transferSubuser(TransferSubuserRequest request);

  /**
   * Get sub-user's account balance
   * @param subuserId
   * @return
   */
  List<AccountBalance> getSubuserAccountBalance(Long subuserId);

  /**
   * Get the aggregated balance of all sub-accounts of the current user.
   * @return
   */
  List<SubuserAggregateBalance> getSubuserAggregateBalance();

  static SubUserClient create(Options options) {

    if (options.getExchange().equals(ExchangeEnum.HUOBI)) {
      return new HuobiSubUserService(options);
    }
    throw new SDKException(SDKException.INPUT_ERROR, "Unsupport Exchange.");
  }

  long getUid();
}
