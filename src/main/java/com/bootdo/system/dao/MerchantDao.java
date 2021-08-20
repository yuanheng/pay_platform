package com.bootdo.system.dao;

import com.bootdo.system.domain.MerchantDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-08-20 21:24:35
 */
@Mapper
public interface MerchantDao {

	MerchantDO get(Integer id);
	
	List<MerchantDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(MerchantDO merchant);
	
	int update(MerchantDO merchant);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
