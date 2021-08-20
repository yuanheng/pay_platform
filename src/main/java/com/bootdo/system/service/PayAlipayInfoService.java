package com.bootdo.system.service;

import com.bootdo.system.domain.PayAlipayInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-08-20 21:24:35
 */
public interface PayAlipayInfoService {
	
	PayAlipayInfoDO get(Integer id);
	
	List<PayAlipayInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PayAlipayInfoDO payAlipayInfo);
	
	int update(PayAlipayInfoDO payAlipayInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
