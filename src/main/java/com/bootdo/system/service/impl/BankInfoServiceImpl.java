package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.BankInfoDao;
import com.bootdo.system.domain.BankInfoDO;
import com.bootdo.system.service.BankInfoService;



@Service
public class BankInfoServiceImpl implements BankInfoService {
	@Autowired
	private BankInfoDao bankInfoDao;
	
	@Override
	public BankInfoDO get(Integer id){
		return bankInfoDao.get(id);
	}
	
	@Override
	public List<BankInfoDO> list(Map<String, Object> map){
		return bankInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return bankInfoDao.count(map);
	}
	
	@Override
	public int save(BankInfoDO bankInfo){
		return bankInfoDao.save(bankInfo);
	}
	
	@Override
	public int update(BankInfoDO bankInfo){
		return bankInfoDao.update(bankInfo);
	}
	
	@Override
	public int remove(Integer id){
		return bankInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return bankInfoDao.batchRemove(ids);
	}

	@Override
	public List<BankInfoDO> getByUserId(Long userId) {
		return bankInfoDao.getByUserId(userId);
	}

}
