package com.bootdo.system.service.impl;

import com.bootdo.app.model.PayNotifyResult;
import com.bootdo.app.util.AmountUtil;
import com.bootdo.app.util.NumberUtil;
import com.bootdo.app.util.OrderCodeUtil;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.app.zwlenum.PayStatusEnum;
import com.bootdo.app.zwlenum.TradeTypeEnum;
import com.bootdo.system.dao.InvestMoneyDao;
import com.bootdo.system.dao.MemberDao;
import com.bootdo.system.dao.WithdrawDao;
import com.bootdo.system.domain.InvestMoneyDO;
import com.bootdo.system.domain.MemberDO;
import com.bootdo.system.domain.WithdrawDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.TradeLogDao;
import com.bootdo.system.domain.TradeLogDO;
import com.bootdo.system.service.TradeLogService;

@Service
public class TradeLogServiceImpl implements TradeLogService {

	@Autowired
	private TradeLogDao tradeLogDao;
	@Autowired
	private InvestMoneyDao investMoneyDao;
	@Autowired
	private WithdrawDao withdrawDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private RedisUtils redisUtils;
	
	@Override
	public TradeLogDO get(Integer id){
		return tradeLogDao.get(id);
	}
	
	@Override
	public List<TradeLogDO> list(Map<String, Object> map){
		return tradeLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tradeLogDao.count(map);
	}
	
	@Override
	public int save(TradeLogDO tradeLog){
		return tradeLogDao.save(tradeLog);
	}
	
	@Override
	public int update(TradeLogDO tradeLog){
		return tradeLogDao.update(tradeLog);
	}
	
	@Override
	public int remove(Integer id){
		return tradeLogDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return tradeLogDao.batchRemove(ids);
	}

	@Override
	public TradeLogDO recharge(MemberDO memberDO, String amount) {
		//生成交易记录并生成充值记录
		String tempAmount = AmountUtil.changeY2F(amount);

		TradeLogDO tradeLogDO = generateTradeLog(memberDO,tempAmount,TradeTypeEnum.INVEST_MONEY.toString());


		tradeLogDO.setStatus(PayStatusEnum.PREPAY.toString());
		save(tradeLogDO);

		//生成充值记录
		InvestMoneyDO investMoneyDO = new InvestMoneyDO();
		investMoneyDO.setAmount(tempAmount);
		investMoneyDO.setCreateTime(tradeLogDO.getCreateTime());
		investMoneyDO.setMid(memberDO.getId());
		investMoneyDO.setMobile(memberDO.getUsername());
		investMoneyDO.setStatus(PayStatusEnum.PREPAY.toString());
		investMoneyDO.setTradeId(tradeLogDO.getId());
		investMoneyDao.save(investMoneyDO);

		return tradeLogDO;
	}

	@Override
	public void withDraw(MemberDO memberDO, String amount) {

		String tempAmount = AmountUtil.changeY2F(amount);
		TradeLogDO tradeLogDO = generateTradeLog(memberDO, tempAmount, TradeTypeEnum.WITHDRAW.toString());

		//更新会员余额
		//可用余额，总额减少
		String totalAmount = NumberUtil.subtract(memberDO.getTotalAmount(), tempAmount);
		String freeAmount = NumberUtil.subtract(memberDO.getFreeAmount(), tempAmount);
		memberDO.setTotalAmount(totalAmount);
		memberDO.setFreeAmount(freeAmount);
		memberDao.update(memberDO);

		//扣除手续费
		String factAmount = NumberUtil.multiply(tradeLogDO.getAmount(), "0.995");
		tradeLogDO.setFactAmount(factAmount);
		tradeLogDO.setStatus(PayStatusEnum.PRE_TRANSFER.toString());
		tradeLogDO.setmFreeTotal(freeAmount);
		save(tradeLogDO);
		//生成提现记录
		WithdrawDO withdrawDO = new WithdrawDO();
		withdrawDO.setFactAmount(factAmount);
		withdrawDO.setAmount(tempAmount);
		withdrawDO.setBankName(memberDO.getBankName());
		withdrawDO.setBankNo(memberDO.getBankNo());
		withdrawDO.setCreateTime(tradeLogDO.getCreateTime());
		withdrawDO.setMid(memberDO.getId());
		withdrawDO.setTradeId(tradeLogDO.getId());
		withdrawDO.setStatus(PayStatusEnum.PRE_TRANSFER.toString());
		withdrawDO.setRemark(memberDO.getReallyName());
		withdrawDao.save(withdrawDO);

	}

	@Override
	public TradeLogDO getTradeLogByNo(String tradeNo) {
		Map<String,Object> params = new HashMap<>();
		params.put("tradeNo",tradeNo);
		List<TradeLogDO> tradeLogDOList = list(params);
		if(tradeLogDOList != null && tradeLogDOList.size() > 0){
			return tradeLogDOList.get(0);
		}
		return null;
	}

	@Override
	public InvestMoneyDO getInvestMoneyByTradeId(Integer tid) {
		Map<String,Object> params = new HashMap<>();
		params.put("tradeId",tid);
		List<InvestMoneyDO> investMoneyDOList = investMoneyDao.list(params);
		if(investMoneyDOList != null && investMoneyDOList.size() > 0){
			return investMoneyDOList.get(0);
		}
		return null;
	}


	@Override
	public MemberDO paySucess(PayNotifyResult payNotifyResult) {
		TradeLogDO tradeLogDO = getTradeLogByNo(payNotifyResult.getTradeNo());
		TradeLogDO tempLog = new TradeLogDO.Builder()
				.id(tradeLogDO.getId())
				.finishedTime(new Date())
				.status(PayStatusEnum.FINISHED.toString())
				.factAmount(payNotifyResult.getFactAmount())
				.tradeOutNo(payNotifyResult.getTradeOutNo())
				.build();

		Integer mid = tradeLogDO.getMid();
		//获取充值记录
		InvestMoneyDO investMoneyDO = getInvestMoneyByTradeId(tradeLogDO.getId());

		InvestMoneyDO tempMoney = new InvestMoneyDO();
		tempMoney.setFinishedTime(new Date());
		tempMoney.setStatus(PayStatusEnum.FINISHED.toString());
		tempMoney.setId(investMoneyDO.getId());

		MemberDO memberDO = memberDao.get(mid);

		synchronized (memberDO.getUsername().intern()) {
			investMoneyDao.update(tempMoney);
			memberDao.updateMemberMoney(memberDO.getId(), payNotifyResult.getAmount());
			memberDO = memberDao.get(memberDO.getId());
			tempLog.setmFreeTotal(memberDO.getFreeAmount());
			tradeLogDao.update(tempLog);
		}

		return memberDO;

	}

	@Override
	public TradeLogDO getTradeLogByAgreeId(Integer agreementId) {

		Map<String,Object> params = new HashMap<>();
		params.put("status",PayStatusEnum.ONLIONING.toString());
		params.put("type",TradeTypeEnum.CASH_DEPOSIT.toString());
		params.put("agreeId",agreementId);
		List<TradeLogDO> tradeLogDOS = tradeLogDao.list(params);
		if(tradeLogDOS != null && tradeLogDOS.size() > 0){
			return  tradeLogDOS.get(0);
		}
		return null;
	}

	@Override
	public boolean gitMoney(MemberDO memberDO, String amount) {
		TradeLogDO tradeLogDO = generateTradeLog(memberDO,amount,TradeTypeEnum.GIF_MONEY.toString());
		tradeLogDO.setFinishedTime(new Date());
		tradeLogDO.setStatus(PayStatusEnum.FINISHED.toString());
		tradeLogDO.setFactAmount(amount);
		tradeLogDO.setmFreeTotal(NumberUtil.add(memberDO.getFreeAmount(),amount));
		tradeLogDao.save(tradeLogDO);
		synchronized (memberDO.getUsername().intern()) {
			memberDao.updateMemberMoney(memberDO.getId(), amount);
		}
		return true;
	}


	private TradeLogDO generateTradeLog(MemberDO memberDO,String amount,String type){
		Date currentDate = new Date();
		TradeLogDO tradeLogDO = new TradeLogDO.Builder()
				.amount(amount)
				.mid(memberDO.getId())
				.mobile(memberDO.getUsername())
				.type(type)
				.createTime(currentDate)
				.tradeNo(OrderCodeUtil.getOrderCode(Long.valueOf(memberDO.getId())))
				.mFreeTotal("0")
				.build();
		tradeLogDO.setAmount(amount);

		return tradeLogDO;
	}

}
