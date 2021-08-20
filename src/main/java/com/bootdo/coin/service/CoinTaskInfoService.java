package com.bootdo.coin.service;


import com.bootdo.coin.domain.CoinTaskInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-03-13 16:56:17
 */
public interface CoinTaskInfoService {
	
	CoinTaskInfoDO get(Integer id);
	
	List<CoinTaskInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CoinTaskInfoDO coinTaskInfo);
	
	int update(CoinTaskInfoDO coinTaskInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

  	int enableTaskInfo(Integer id, Integer mid);

	int closedTaskInfo(Integer id, Integer mid);
}
