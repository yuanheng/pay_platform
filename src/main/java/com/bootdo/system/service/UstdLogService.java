package com.bootdo.system.service;

import com.bootdo.system.domain.MemberDO;
import com.bootdo.system.domain.UstdLogDO;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-11-23 19:37:54
 */
public interface UstdLogService {

	UstdLogDO get(Long id);

	List<UstdLogDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(UstdLogDO ustdLog);

	int update(UstdLogDO ustdLog);

	int remove(Long id);

	int batchRemove(Long[] ids);

	int auditing(Long id, int status, String username);

	UstdLogDO createUstdLogDO(MemberDO memberDO, String amount, String cnyPrice) throws Exception;
}
