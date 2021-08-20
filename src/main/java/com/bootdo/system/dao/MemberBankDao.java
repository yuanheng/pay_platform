package com.bootdo.system.dao;

import com.bootdo.system.domain.MemberBankDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-11-23 21:54:22
 */
@Mapper
public interface MemberBankDao {

	MemberBankDO get(Integer id);

	List<MemberBankDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	int save(MemberBankDO memberBank);

	int update(MemberBankDO memberBank);

	int remove(Integer id);

	int batchRemove(Integer[] ids);

    List<MemberBankDO> listEnableBank(MemberBankDO memberBankDO);
}
