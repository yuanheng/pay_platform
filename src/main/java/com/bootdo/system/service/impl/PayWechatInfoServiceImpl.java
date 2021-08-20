package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.PayWechatInfoDao;
import com.bootdo.system.domain.PayWechatInfoDO;
import com.bootdo.system.service.PayWechatInfoService;



@Service
public class PayWechatInfoServiceImpl implements PayWechatInfoService {
	@Autowired
	private PayWechatInfoDao payWechatInfoDao;
	
	@Override
	public PayWechatInfoDO get(Integer id){
		return payWechatInfoDao.get(id);
	}
	
	@Override
	public List<PayWechatInfoDO> list(Map<String, Object> map){
		return payWechatInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return payWechatInfoDao.count(map);
	}
	
	@Override
	public int save(PayWechatInfoDO payWechatInfo){
		return payWechatInfoDao.save(payWechatInfo);
	}
	
	@Override
	public int update(PayWechatInfoDO payWechatInfo){
		return payWechatInfoDao.update(payWechatInfo);
	}
	
	@Override
	public int remove(Integer id){
		return payWechatInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return payWechatInfoDao.batchRemove(ids);
	}
	
}
