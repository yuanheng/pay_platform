package com.bootdo.coin.client.req.account;

import com.bootdo.coin.constant.enums.AccountUpdateModeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubAccountUpdateRequest {

  private AccountUpdateModeEnum accountUpdateMode;

}
