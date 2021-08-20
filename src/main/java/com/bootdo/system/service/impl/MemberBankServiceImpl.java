package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.MemberBankDao;
import com.bootdo.system.domain.MemberBankDO;
import com.bootdo.system.service.MemberBankService;



@Service
public class MemberBankServiceImpl implements MemberBankService {
	@Autowired
	private MemberBankDao memberBankDao;

	@Override
	public MemberBankDO get(Integer id){
		return memberBankDao.get(id);
	}

	@Override
	public List<MemberBankDO> list(Map<String, Object> map){
		return memberBankDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return memberBankDao.count(map);
	}

	@Override
	public int save(MemberBankDO memberBank){
		return memberBankDao.save(memberBank);
	}

	@Override
	public int update(MemberBankDO memberBank){
		return memberBankDao.update(memberBank);
	}

	@Override
	public int remove(Integer id){
		return memberBankDao.remove(id);
	}

	@Override
	public int batchRemove(Integer[] ids){
		return memberBankDao.batchRemove(ids);
	}

	@Override
	public List<MemberBankDO> listEnableBank(MemberBankDO memberBankDO) {
		return memberBankDao.listEnableBank(memberBankDO);
	}

}
