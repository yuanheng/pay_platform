package com.bootdo.system.service.impl;

import com.bootdo.app.zwlenum.PayStatusEnum;
import com.bootdo.system.dao.MemberDao;
import com.bootdo.system.domain.MemberDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.MemberLevelDao;
import com.bootdo.system.domain.MemberLevelDO;
import com.bootdo.system.service.MemberLevelService;



@Service
public class MemberLevelServiceImpl implements MemberLevelService {
	@Autowired
	private MemberLevelDao memberLevelDao;
	
	@Override
	public MemberLevelDO get(Integer id){
		return memberLevelDao.get(id);
	}
	
	@Override
	public List<MemberLevelDO> list(Map<String, Object> map){
		return memberLevelDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return memberLevelDao.count(map);
	}
	
	@Override
	public int save(MemberLevelDO memberLevel){
		return memberLevelDao.save(memberLevel);
	}
	
	@Override
	public int update(MemberLevelDO memberLevel){
		return memberLevelDao.update(memberLevel);
	}
	
	@Override
	public int remove(Integer id){
		return memberLevelDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return memberLevelDao.batchRemove(ids);
	}

	@Override
	public void updateMemberLevel(MemberDO memberDO) {
		MemberLevelDO memberLevelDO = getByMid(memberDO.getId());
		if(memberLevelDO.getStatus().equals(PayStatusEnum.DISABLE.toString())){
			memberLevelDO.setStatus(PayStatusEnum.ENABLE.toString());
			memberLevelDao.updateMemberLevel(memberLevelDO);
			memberLevelDao.updateLeve1Num(memberLevelDO);
			memberLevelDao.updateLeve2Num(memberLevelDO);
			memberLevelDao.updateLeve3Num(memberLevelDO);
		}

		/*
		memberLevelDO = getByMid(memberDO.getId());
		int num = memberLevelDO.getLevel1();
		if(num >= 5){
			memberDO.setLevel(3);
		}else if(num >= 3){
			memberDO.setLevel(2);
		}else{
			memberDO.setLevel(1);
		}
		memberLevelDao.updateMemberVipLevel(memberDO);

		 */


	}


	@Override
	public MemberLevelDO getByMid(Integer mid) {
		Map<String,Object> params = new HashMap<>();
		params.put("mid",mid);
		List<MemberLevelDO> levelDOS = list(params);
		if(levelDOS != null && levelDOS.size() > 0){
			return levelDOS.get(0);
		}
		return null;
	}

	@Override
	public int getLevel1Num(Integer id) {
		Map<String,Object> params = new HashMap<>();
		params.put("parentMid",id);
		params.put("status",PayStatusEnum.ENABLE.toString());
		int count = memberLevelDao.count(params);
		return count;
	}

	@Override
	public int getLevel2Num(Integer id) {
		Map<String,Object> params = new HashMap<>();
		params.put("grandMid",id);
		params.put("status",PayStatusEnum.ENABLE.toString());
		int count = memberLevelDao.count(params);
		return count;
	}

	@Override
	public int getLevel3Num(Integer id) {
		Map<String,Object> params = new HashMap<>();
		params.put("greatMid",id);
		params.put("status",PayStatusEnum.ENABLE.toString());
		int count = memberLevelDao.count(params);
		return count;
	}

	@Override
	public List<MemberLevelDO> getChildrenMember(Integer mid) {
		List<MemberLevelDO> levelDOChildren = memberLevelDao.getChildrenMember(mid);
		return levelDOChildren;
	}

}
