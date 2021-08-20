package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.InvestMoneyDao;
import com.bootdo.system.domain.InvestMoneyDO;
import com.bootdo.system.service.InvestMoneyService;



@Service
public class InvestMoneyServiceImpl implements InvestMoneyService {
	@Autowired
	private InvestMoneyDao investMoneyDao;
	
	@Override
	public InvestMoneyDO get(Integer id){
		return investMoneyDao.get(id);
	}
	
	@Override
	public List<InvestMoneyDO> list(Map<String, Object> map){
		return investMoneyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return investMoneyDao.count(map);
	}
	
	@Override
	public int save(InvestMoneyDO investMoney){
		return investMoneyDao.save(investMoney);
	}
	
	@Override
	public int update(InvestMoneyDO investMoney){
		return investMoneyDao.update(investMoney);
	}
	
	@Override
	public int remove(Integer id){
		return investMoneyDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return investMoneyDao.batchRemove(ids);
	}
	
}
