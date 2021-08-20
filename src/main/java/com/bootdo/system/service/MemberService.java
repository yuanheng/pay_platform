package com.bootdo.system.service;

import com.bootdo.system.domain.MemberDO;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

/**
 * 会员表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-20 15:19:53
 */
public interface MemberService {
	
	MemberDO get(Integer id);
	
	List<MemberDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MemberDO member);
	
	int update(MemberDO member);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	MemberDO login(String username,String password);

    int checkMobile(String mobile);

    int register(String mobile,String password,String code);

	int updatePwd(String mobile, String password);

	MemberDO getMemberByMobile(String mobile);

	MemberDO getMemberByCode(String code);


	String getTotalIncome(Integer id);

	String getTotalOutcome(Integer id);

	int decreaseAiFreeAmount(Integer mid, String amount);
}
