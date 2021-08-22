package com.bootdo.system.service;

import com.bootdo.app.global.NotPayInfoException;
import com.bootdo.app.model.PaymentInfo;
import com.bootdo.system.domain.OrderDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-08-20 21:24:35
 */
public interface OrderService {
	
	OrderDO get(Integer id);
	
	List<OrderDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderDO order);
	
	int update(OrderDO order);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	OrderDO createWechatOrder(PaymentInfo paymentInfo) throws NotPayInfoException;

	OrderDO createAlipayOrder(PaymentInfo paymentInfo) throws NotPayInfoException;

	OrderDO createBankOrder(PaymentInfo paymentInfo) throws NotPayInfoException;

	OrderDO notifyMerchant(OrderDO orderDO);

  int cancelOrder(OrderDO orderDO);

}
