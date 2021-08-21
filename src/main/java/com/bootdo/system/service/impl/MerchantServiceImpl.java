package com.bootdo.system.service.impl;

import com.bootdo.app.zwlenum.StatusEnum;
import com.bootdo.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
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
		merchant.setCreateTime(new Date());
		merchant.setStatus(StatusEnum.ENABLE.getKey());
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

	@Override
	public MerchantDO getByMerchantNo(String merchantNo) {
		if (StringUtils.isEmpty(merchantNo)) {
			throw new IllegalArgumentException("merchantNo must be not empty");
		}
		Map<String, Object> params = new HashMap();
		params.put("merchantNo",merchantNo);
		params.put("status", StatusEnum.ENABLE.getKey());
		List<MerchantDO> list = list(params);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public MerchantDO getByMid(Long userId) {
		if (userId == null || userId < 0) {
			throw new IllegalArgumentException("merchantNo must be not empty");
		}
		Map<String, Object> params = new HashMap();
		params.put("mid",userId);
		params.put("status", StatusEnum.ENABLE.getKey());
		List<MerchantDO> list = list(params);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
