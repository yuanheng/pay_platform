package com.bootdo.system.dao;

import com.bootdo.system.domain.MemberDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 会员表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-20 15:19:53
 */
@Mapper
public interface MemberDao {

	MemberDO get(Integer id);
	
	List<MemberDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MemberDO member);
	
	int update(MemberDO member);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

    void updateMemberMoney(@Param("mid") Integer mid, @Param("amount") String amount);

    Map<String, String> getTotalIn2Outcome(Integer id);

	String getTotalIncome(Integer id);

	String getTotalOutcome(Integer id);

	int updateMemberAiFreeAmount(@Param("mid") Integer mid, @Param("amount") String amount);
}
