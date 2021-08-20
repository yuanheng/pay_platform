package com.bootdo.system.dao;

import com.bootdo.system.domain.UstdLogDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-11-23 19:37:54
 */
@Mapper
public interface UstdLogDao {

	UstdLogDO get(Long id);

	List<UstdLogDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	int save(UstdLogDO ustdLog);

	int update(UstdLogDO ustdLog);

	int remove(Long id);

	int batchRemove(Long[] ids);

	int auditing(Long id, int status, String operator);
}
