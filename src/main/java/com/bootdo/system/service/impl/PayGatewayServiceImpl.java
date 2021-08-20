package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.PayGatewayDao;
import com.bootdo.system.domain.PayGatewayDO;
import com.bootdo.system.service.PayGatewayService;



@Service
public class PayGatewayServiceImpl implements PayGatewayService {
	@Autowired
	private PayGatewayDao payGatewayDao;
	
	@Override
	public PayGatewayDO get(Integer id){
		return payGatewayDao.get(id);
	}
	
	@Override
	public List<PayGatewayDO> list(Map<String, Object> map){
		return payGatewayDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return payGatewayDao.count(map);
	}
	
	@Override
	public int save(PayGatewayDO payGateway){
		return payGatewayDao.save(payGateway);
	}
	
	@Override
	public int update(PayGatewayDO payGateway){
		return payGatewayDao.update(payGateway);
	}
	
	@Override
	public int remove(Integer id){
		return payGatewayDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return payGatewayDao.batchRemove(ids);
	}
	
}
