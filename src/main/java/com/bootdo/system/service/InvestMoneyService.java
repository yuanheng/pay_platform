package com.bootdo.system.service;

import com.bootdo.system.domain.InvestMoneyDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-02 11:24:17
 */
public interface InvestMoneyService {
	
	InvestMoneyDO get(Integer id);
	
	List<InvestMoneyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(InvestMoneyDO investMoney);
	
	int update(InvestMoneyDO investMoney);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
