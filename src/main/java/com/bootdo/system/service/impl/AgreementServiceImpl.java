package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.AgreementDao;
import com.bootdo.system.domain.AgreementDO;
import com.bootdo.system.service.AgreementService;



@Service
public class AgreementServiceImpl implements AgreementService {
	@Autowired
	private AgreementDao agreementDao;
	
	@Override
	public AgreementDO get(Integer id){
		return agreementDao.get(id);
	}
	
	@Override
	public List<AgreementDO> list(Map<String, Object> map){
		return agreementDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return agreementDao.count(map);
	}
	
	@Override
	public int save(AgreementDO agreement){
		return agreementDao.save(agreement);
	}
	
	@Override
	public int update(AgreementDO agreement){
		return agreementDao.update(agreement);
	}
	
	@Override
	public int remove(Integer id){
		return agreementDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return agreementDao.batchRemove(ids);
	}

	@Override
	public List<AgreementDO> getExpireAgreements(Date endTime) {
		return agreementDao.getExpireAgreements(endTime);
	}

	@Override
	public AgreementDO getMemberAgreetment(Integer mid) {
		Map<String,Object> params = new HashMap<>();
		params.put("mid",mid);
		params.put("status","enable");
		params.put("endTime",new Date());
		List<AgreementDO> temp = agreementDao.getMemberAgreement(params);
		if(temp != null && temp.size() > 0){
			return temp.get(0);
		}
		return null;
	}

}
