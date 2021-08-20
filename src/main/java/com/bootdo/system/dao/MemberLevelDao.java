package com.bootdo.system.dao;

import com.bootdo.system.domain.MemberDO;
import com.bootdo.system.domain.MemberLevelDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-08 16:50:14
 */
@Mapper
public interface MemberLevelDao {

	MemberLevelDO get(Integer id);
	
	List<MemberLevelDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MemberLevelDO memberLevel);
	
	int update(MemberLevelDO memberLevel);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

    void updateMemberLevel(MemberLevelDO memberLevel);

	void updateLeve1Num(MemberLevelDO memberLevelDO);

	void updateLeve2Num(MemberLevelDO memberLevelDO);

	void updateLeve3Num(MemberLevelDO memberLevelDO);

    List<MemberLevelDO> getChildrenMember(Integer mid);

	void updateMemberVipLevel(MemberDO memberDO);
}
