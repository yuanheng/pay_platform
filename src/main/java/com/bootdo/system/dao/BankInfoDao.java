package com.bootdo.system.dao;

import com.bootdo.system.domain.BankInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-08-20 21:24:35
 */
@Mapper
public interface BankInfoDao {

    BankInfoDO get(Integer id);

    List<BankInfoDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(BankInfoDO bankInfo);

    int update(BankInfoDO bankInfo);

    int remove(Integer id);

    int batchRemove(Integer[] ids);

    List<BankInfoDO> getByUserId(@Param("userId") Long userId);

}
