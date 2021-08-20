package com.bootdo.system.dao;

import com.bootdo.system.domain.WithdrawDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-02 11:24:17
 */
@Mapper
public interface WithdrawDao {

	WithdrawDO get(Integer id);
	
	List<WithdrawDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(WithdrawDO withdraw);
	
	int update(WithdrawDO withdraw);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
