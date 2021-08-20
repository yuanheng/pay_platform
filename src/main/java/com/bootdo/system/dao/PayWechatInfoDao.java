package com.bootdo.system.dao;

import com.bootdo.system.domain.PayWechatInfoDO;

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
public interface PayWechatInfoDao {

	PayWechatInfoDO get(Integer id);
	
	List<PayWechatInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PayWechatInfoDO payWechatInfo);
	
	int update(PayWechatInfoDO payWechatInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
