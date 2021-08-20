package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.WithdrawDao;
import com.bootdo.system.domain.WithdrawDO;
import com.bootdo.system.service.WithdrawService;



@Service
public class WithdrawServiceImpl implements WithdrawService {
	@Autowired
	private WithdrawDao withdrawDao;
	
	@Override
	public WithdrawDO get(Integer id){
		return withdrawDao.get(id);
	}
	
	@Override
	public List<WithdrawDO> list(Map<String, Object> map){
		return withdrawDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return withdrawDao.count(map);
	}
	
	@Override
	public int save(WithdrawDO withdraw){
		return withdrawDao.save(withdraw);
	}
	
	@Override
	public int update(WithdrawDO withdraw){
		return withdrawDao.update(withdraw);
	}
	
	@Override
	public int remove(Integer id){
		return withdrawDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return withdrawDao.batchRemove(ids);
	}
	
}
