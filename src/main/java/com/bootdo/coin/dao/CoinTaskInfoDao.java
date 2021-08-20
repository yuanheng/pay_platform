package com.bootdo.coin.dao;
import java.util.List;
import java.util.Map;

import com.bootdo.coin.domain.CoinTaskInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-03-13 16:56:17
 */
@Mapper
public interface CoinTaskInfoDao {

	CoinTaskInfoDO get(Integer id);
	
	List<CoinTaskInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CoinTaskInfoDO coinTaskInfo);
	
	int update(CoinTaskInfoDO coinTaskInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	int enableTaskInfo(CoinTaskInfoDO coinTaskInfoDO);

	int closeTaskInfo(CoinTaskInfoDO coinTaskInfoDO);

	List<CoinTaskInfoDO> listRuningTask(CoinTaskInfoDO coinTaskInfoDO);
}
