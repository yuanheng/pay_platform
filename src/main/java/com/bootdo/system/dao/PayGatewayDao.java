package com.bootdo.system.dao;

import com.bootdo.system.domain.PayGatewayDO;

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
public interface PayGatewayDao {

	PayGatewayDO get(Integer id);
	
	List<PayGatewayDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PayGatewayDO payGateway);
	
	int update(PayGatewayDO payGateway);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
