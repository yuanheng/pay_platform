package com.bootdo.system.service;

import com.bootdo.system.domain.MemberDO;
import com.bootdo.system.domain.MemberLevelDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-08 16:50:14
 */
public interface MemberLevelService {
	
	MemberLevelDO get(Integer id);
	
	List<MemberLevelDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MemberLevelDO memberLevel);
	
	int update(MemberLevelDO memberLevel);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

    void updateMemberLevel(MemberDO memberDO);

    MemberLevelDO getByMid(Integer mid);

    int getLevel1Num(Integer id);

	int getLevel2Num(Integer id);

	int getLevel3Num(Integer id);

	List<MemberLevelDO> getChildrenMember(Integer id);
}
