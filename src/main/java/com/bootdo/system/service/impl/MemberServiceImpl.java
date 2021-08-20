package com.bootdo.system.service.impl;

import com.bootdo.app.util.InvitationCodeGnerateUtil;
import com.bootdo.app.util.ShareCodeUtil;
import com.bootdo.app.zwlenum.PayStatusEnum;
import com.bootdo.system.dao.MemberLevelDao;
import com.bootdo.system.domain.MemberLevelDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.MemberDao;
import com.bootdo.system.domain.MemberDO;
import com.bootdo.system.service.MemberService;



@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberLevelDao memberLevelDao;

	@Override
	public MemberDO get(Integer id){
		return memberDao.get(id);
	}

	@Override
	public List<MemberDO> list(Map<String, Object> map){
		return memberDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return memberDao.count(map);
	}

	@Override
	public int save(MemberDO member){
		return memberDao.save(member);
	}

	@Override
	public int update(MemberDO member){
		return memberDao.update(member);
	}

	@Override
	public int remove(Integer id){
		return memberDao.remove(id);
	}

	@Override
	public int batchRemove(Integer[] ids){
		return memberDao.batchRemove(ids);
	}

	@Override
	public MemberDO login(String username, String password) {
		Map<String,Object> params = new HashMap<>();
		params.put("username",username);
		params.put("password",password);
		List<MemberDO> memberDOS = list(params);
		if(memberDOS != null && memberDOS.size() > 0){
			return memberDOS.get(0);
		}
		return null;
	}

	@Override
	public int checkMobile(String mobile) {
		Map<String,Object> params = new HashMap<>();
		params.put("username",mobile);
		return memberDao.count(params);
	}

	@Override
	public int register(String mobile, String password, final String code) {

		MemberDO parentMemeber = getMemberByCode(code);
		Integer parentId = null;
		Integer grandId = null;
		Integer greatId = null;


		if(parentMemeber != null){
			parentId = parentMemeber.getId();
			String grandCode = parentMemeber.getSourceInviteCode();
			if(grandCode != null && grandCode.length() > 0){
				MemberDO grandMember = getMemberByCode(grandCode);
				if(grandMember != null){
					grandId = grandMember.getId();
					String greatCode = grandMember.getSourceInviteCode();
					if(greatCode != null && greatCode.length() > 0){
						MemberDO greatMember = getMemberByCode(greatCode);
						if(greatMember != null){
							greatId = greatMember.getId();
						}
					}
				}
			}
		}



		//根据code 查询上级

		final MemberDO memberDO = new MemberDO();
		memberDO.setSourceInviteCode(code);
		memberDO.setUsername(mobile);
		memberDO.setPassword(password);
		memberDO.setCreateTime(new Date());
		memberDO.setOwnerInviteCode(InvitationCodeGnerateUtil.generateInvitationCode());
		memberDO.setDayProfitAmount("0");
		memberDO.setProfitAmount("0");
		memberDO.setTotalAmount("0");
		memberDO.setFreeAmount("0");
		memberDO.setLockAmount("0");
		memberDO.setTransitAmount("0");
		memberDO.setLevel(1);
		save(memberDO);

		final int mid = memberDO.getId();

		MemberLevelDO memberLevelDO = new MemberLevelDO();
		memberLevelDO.setMid(mid);
		memberLevelDO.setType("A");
		memberLevelDO.setStatus(PayStatusEnum.DISABLE.toString());
		memberLevelDO.setLevel1(0);
		memberLevelDO.setLevel2(0);
		memberLevelDO.setLevel3(0);
		memberLevelDO.setParentMid(parentId);
		memberLevelDO.setGrandMid(grandId);
		memberLevelDO.setGreatMid(greatId);
		memberLevelDao.save(memberLevelDO);

		return memberDO.getId();
	}

	@Override
	public int updatePwd(String mobile, String password) {
		MemberDO memberDO = getMemberByMobile(mobile);
		MemberDO temp = new MemberDO();
		temp.setPassword(password);
		temp.setId(memberDO.getId());
		return update(temp);
	}

	@Override
	public MemberDO getMemberByMobile(String mobile) {
		Map<String,Object> params = new HashMap<>();
		params.put("username",mobile);
		List<MemberDO> memberDOS = list(params);
		if(memberDOS != null && memberDOS.size() > 0){
			return memberDOS.get(0);
		}
		return null;
	}

	@Override
	public MemberDO getMemberByCode(String code) {

		Map<String,Object> params = new HashMap<>();
		if(code != null && code.length() < 1){
			return null;
		}
		params.put("ownerInviteCode",code);
		List<MemberDO> memberDOS = list(params);
		if(memberDOS != null && memberDOS.size() > 0){
			return memberDOS.get(0);
		}
		return null;
	}

	@Override
	public String getTotalIncome(Integer id) {
		return memberDao.getTotalIncome(id);
	}

	@Override
	public String getTotalOutcome(Integer id) {
		return memberDao.getTotalOutcome(id);
	}

	@Override
	public int decreaseAiFreeAmount(Integer mid,String amount) {
		return memberDao.updateMemberAiFreeAmount(mid, amount);
	}


}
