package com.bootdo.system.service.impl;

import com.bootdo.app.util.OrderCodeUtil;
import com.bootdo.app.zwlenum.USDTLogStatusEnum;
import com.bootdo.system.domain.MemberDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.UstdLogDao;
import com.bootdo.system.domain.UstdLogDO;
import com.bootdo.system.service.UstdLogService;



@Service
public class UstdLogServiceImpl implements UstdLogService {

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private UstdLogDao ustdLogDao;

	@Override
	public UstdLogDO get(Long id){
		return ustdLogDao.get(id);
	}

	@Override
	public List<UstdLogDO> list(Map<String, Object> map){
		return ustdLogDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return ustdLogDao.count(map);
	}

	@Override
	public int save(UstdLogDO ustdLog){
		return ustdLogDao.save(ustdLog);
	}

	@Override
	public int update(UstdLogDO ustdLog){
		return ustdLogDao.update(ustdLog);
	}

	@Override
	public int remove(Long id){
		return ustdLogDao.remove(id);
	}

	@Override
	public int batchRemove(Long[] ids){
		return ustdLogDao.batchRemove(ids);
	}

	@Override
	public int auditing(Long id, int status, String operator) {
		return ustdLogDao.auditing(id,status,operator);
	}

	@Override
	public UstdLogDO createUstdLogDO(MemberDO memberDO, String amount, String cnyPrice) throws Exception{
		UstdLogDO ustdLogDO = new UstdLogDO();
		ustdLogDO.setNo(OrderCodeUtil.getOrderCode(Long.valueOf(memberDO.getId())));
		ustdLogDO.setMid(memberDO.getId());
		ustdLogDO.setAmount(amount);
		ustdLogDO.setTotalAmount(amount);
		ustdLogDO.setProfileAmount("0");
		ustdLogDO.setCnyPrice(cnyPrice);
		ustdLogDO.setCreateTime(new Date());
		ustdLogDO.setStatus(USDTLogStatusEnum.pre_pay.getId());
		ustdLogDO.setBankName(memberDO.getBankName());
		ustdLogDO.setBankBranch(memberDO.getBankBranchName());
		ustdLogDO.setBankNo(memberDO.getBankNo());
		ustdLogDO.setReallyName(memberDO.getReallyName());
		ustdLogDO.setCurrentDate(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
		ustdLogDao.save(ustdLogDO);
		return ustdLogDO;
	}

}
