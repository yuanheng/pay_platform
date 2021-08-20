package com.bootdo.coin.client.req.crossmargin;

import java.util.Date;
import java.util.List;

import com.bootdo.coin.constant.enums.LoanOrderStateEnum;
import com.bootdo.coin.constant.enums.QueryDirectionEnum;
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
public class CrossMarginLoanOrdersRequest {

  private String currency;

  private Date startDate;

  private Date endDate;

  private List<LoanOrderStateEnum> states;

  private Long from;

  private QueryDirectionEnum direction;

  private Integer size;

  public String getStatesString(){
    String stateString = null;
    if (this.getStates() != null && this.getStates().size() > 0) {
      StringBuffer statesBuffer = new StringBuffer();
      this.getStates().forEach(orderType -> {
        statesBuffer.append(orderType.getCode()).append(",");
      });
      stateString = statesBuffer.substring(0, statesBuffer.length() - 1);
    }
    return stateString;
  }

}
