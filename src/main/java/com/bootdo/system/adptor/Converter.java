package com.bootdo.system.adptor;

import com.bootdo.app.zwlenum.StatusEnum;
import com.bootdo.system.domain.TbOrderDO;
import com.bootdo.system.dto.TBCode4ImportDTO;

/**
 * @author Prometheus
 * @date 2021/9/24.
 */
public class Converter {

    public static TbOrderDO convert(TBCode4ImportDTO dto) throws IllegalArgumentException {
        TbOrderDO orderDO = new TbOrderDO();
        orderDO.setStatus(StatusEnum.DISABLE.getKey());
        orderDO.setNo(dto.getTbOrderNo());
        orderDO.setAccount(dto.getTbAccountNo());
        orderDO.setAmount(String.valueOf(dto.getAmount()));
        orderDO.setCreateTime(dto.getTbOrderCreateTime());
        orderDO.setValidity(5000);
        orderDO.setMid(Long.parseLong(dto.getMid()));
        orderDO.setUrl(dto.getPayUrl());
        return orderDO;
    }

}
