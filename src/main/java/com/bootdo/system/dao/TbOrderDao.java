package com.bootdo.system.dao;

import com.bootdo.system.domain.TbOrderDO;
import com.bootdo.system.dto.TbCodeStatusDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-09-08 22:32:27
 */
@Mapper
public interface TbOrderDao {

    TbOrderDO get(Integer id);

    List<TbOrderDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(TbOrderDO tbOrder);

    int update(TbOrderDO tbOrder);

    int remove(Integer id);

    int batchRemove(Integer[] ids);

    List<TbCodeStatusDTO> statCodeStatus();
}
