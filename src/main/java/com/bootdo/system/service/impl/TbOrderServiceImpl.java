package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.TbOrderDao;
import com.bootdo.system.domain.TbOrderDO;
import com.bootdo.system.service.TbOrderService;



@Service
public class TbOrderServiceImpl implements TbOrderService {
	@Autowired
	private TbOrderDao tbOrderDao;
	
	@Override
	public TbOrderDO get(Integer id){
		return tbOrderDao.get(id);
	}
	
	@Override
	public List<TbOrderDO> list(Map<String, Object> map){
		return tbOrderDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tbOrderDao.count(map);
	}
	
	@Override
	public int save(TbOrderDO tbOrder){
		return tbOrderDao.save(tbOrder);
	}
	
	@Override
	public int update(TbOrderDO tbOrder){
		return tbOrderDao.update(tbOrder);
	}
	
	@Override
	public int remove(Integer id){
		return tbOrderDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return tbOrderDao.batchRemove(ids);
	}
	
}
