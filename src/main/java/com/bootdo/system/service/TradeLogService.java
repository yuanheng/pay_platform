package com.bootdo.system.service;

import com.bootdo.app.model.PayNotifyResult;
import com.bootdo.system.domain.InvestMoneyDO;
import com.bootdo.system.domain.MemberDO;
import com.bootdo.system.domain.TradeLogDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-02 11:24:17
 */
public interface TradeLogService {
	
	TradeLogDO get(Integer id);
	
	List<TradeLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TradeLogDO tradeLog);
	
	int update(TradeLogDO tradeLog);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

    TradeLogDO recharge(MemberDO memberDO, String amount);

	void withDraw(MemberDO memberDO, String amount);

    TradeLogDO getTradeLogByNo(String tradeNo);

	InvestMoneyDO getInvestMoneyByTradeId(Integer tid);

	MemberDO paySucess(PayNotifyResult payNotifyResult);

    TradeLogDO getTradeLogByAgreeId(Integer agreementId);

    boolean gitMoney(MemberDO memberDO, String amount);
}
