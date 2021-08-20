package com.bootdo.coin.client.req.account;

import java.math.BigDecimal;

import com.bootdo.coin.constant.enums.AccountTransferAccountTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransferRequest {

  private Long fromUser;

  private AccountTransferAccountTypeEnum fromAccountType;

  private Long fromAccount;

  private Long toUser;

  private AccountTransferAccountTypeEnum toAccountType;

  private Long toAccount;

  private String currency;

  private BigDecimal amount;

}
