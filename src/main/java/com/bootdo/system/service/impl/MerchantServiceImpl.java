package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.MerchantDao;
import com.bootdo.system.domain.MerchantDO;
import com.bootdo.system.service.MerchantService;



@Service
public class MerchantServiceImpl implements MerchantService {
	@Autowired
	private MerchantDao merchantDao;
	
	@Override
	public MerchantDO get(Integer id){
		return merchantDao.get(id);
	}
	
	@Override
	public List<MerchantDO> list(Map<String, Object> map){
		return merchantDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return merchantDao.count(map);
	}
	
	@Override
	public int save(MerchantDO merchant){
		return merchantDao.save(merchant);
	}
	
	@Override
	public int update(MerchantDO merchant){
		return merchantDao.update(merchant);
	}
	
	@Override
	public int remove(Integer id){
		return merchantDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return merchantDao.batchRemove(ids);
	}
	
}
