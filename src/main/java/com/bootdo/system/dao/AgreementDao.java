package com.bootdo.system.dao;

import com.bootdo.system.domain.AgreementDO;

import java.util.Date;
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
public interface AgreementDao {

	AgreementDO get(Integer id);
	
	List<AgreementDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AgreementDO agreement);
	
	int update(AgreementDO agreement);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

    List<AgreementDO> getExpireAgreements(Date endTime);

    List<AgreementDO> getMemberAgreement(Map<String, Object> params);
}
