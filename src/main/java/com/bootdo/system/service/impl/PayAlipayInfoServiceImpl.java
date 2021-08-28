package com.bootdo.system.service.impl;

import com.bootdo.system.dao.PayAlipayInfoDao;
import com.bootdo.system.domain.PayAlipayInfoDO;
import com.bootdo.system.service.PayAlipayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class PayAlipayInfoServiceImpl implements PayAlipayInfoService {
    @Autowired
    private PayAlipayInfoDao payAlipayInfoDao;

    @Override
    public PayAlipayInfoDO get(Integer id) {
        return payAlipayInfoDao.get(id);
    }

    @Override
    public List<PayAlipayInfoDO> list(Map<String, Object> map) {
        return payAlipayInfoDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return payAlipayInfoDao.count(map);
    }

    @Override
    public int save(PayAlipayInfoDO payAlipayInfo) {
        return payAlipayInfoDao.save(payAlipayInfo);
    }

    @Override
    public int update(PayAlipayInfoDO payAlipayInfo) {
        return payAlipayInfoDao.update(payAlipayInfo);
    }

    @Override
    public int remove(Integer id) {
        return payAlipayInfoDao.remove(id);
    }

    @Override
    public int batchRemove(Integer[] ids) {
        return payAlipayInfoDao.batchRemove(ids);
    }

}
