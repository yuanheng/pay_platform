package com.bootdo.system.service;

import com.bootdo.system.domain.MerchantDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-08-20 21:24:35
 */
public interface MerchantService {
	
	MerchantDO get(Integer id);
	
	List<MerchantDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MerchantDO merchant);
	
	int update(MerchantDO merchant);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
