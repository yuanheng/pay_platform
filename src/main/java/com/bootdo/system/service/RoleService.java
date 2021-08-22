package com.bootdo.system.service;

import java.util.List;

import com.bootdo.app.zwlenum.RoleTypeEnum;
import com.bootdo.system.domain.UserDO;
import org.springframework.stereotype.Service;

import com.bootdo.system.domain.RoleDO;

@Service
public interface RoleService {

	RoleDO get(Long id);

	List<RoleDO> list();

	int save(RoleDO role);

	int update(RoleDO role);

	int remove(Long id);

	List<RoleDO> list(Long userId);

	int batchremove(Long[] ids);

    RoleTypeEnum distinguishByLoginInfo(UserDO user);
}
