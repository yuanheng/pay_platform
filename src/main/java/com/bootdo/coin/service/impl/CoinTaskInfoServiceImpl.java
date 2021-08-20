package com.bootdo.coin.service.impl;

import com.bootdo.coin.dao.CoinTaskInfoDao;
import com.bootdo.coin.domain.CoinTaskInfoDO;
import com.bootdo.coin.enuminfo.CoinTaskStatusEnum;
import com.bootdo.coin.service.CoinTaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CoinTaskInfoServiceImpl implements CoinTaskInfoService {
	@Autowired
	private CoinTaskInfoDao coinTaskInfoDao;
	
	@Override
	public CoinTaskInfoDO get(Integer id){
		return coinTaskInfoDao.get(id);
	}
	
	@Override
	public List<CoinTaskInfoDO> list(Map<String, Object> map){
		return coinTaskInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return coinTaskInfoDao.count(map);
	}
	
	@Override
	public int save(CoinTaskInfoDO coinTaskInfo){
		return coinTaskInfoDao.save(coinTaskInfo);
	}
	
	@Override
	public int update(CoinTaskInfoDO coinTaskInfo){
		return coinTaskInfoDao.update(coinTaskInfo);
	}
	
	@Override
	public int remove(Integer id){
		return coinTaskInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return coinTaskInfoDao.batchRemove(ids);
	}

	@Override
	public int enableTaskInfo(Integer id, Integer mid) {
		CoinTaskInfoDO coinTaskInfoDO = new CoinTaskInfoDO();
		coinTaskInfoDO.setId(id);
		coinTaskInfoDO.setStatus(CoinTaskStatusEnum.CLOSED.toString());
		coinTaskInfoDO.setMid(mid);
		return coinTaskInfoDao.enableTaskInfo(coinTaskInfoDO);
	}

	@Override
	public int closedTaskInfo(Integer id, Integer mid) {
		CoinTaskInfoDO coinTaskInfoDO = new CoinTaskInfoDO();
		coinTaskInfoDO.setId(id);
		coinTaskInfoDO.setStatus(CoinTaskStatusEnum.CLOSED.toString());
		coinTaskInfoDO.setMid(mid);
		return coinTaskInfoDao.closeTaskInfo(coinTaskInfoDO);
	}

}
