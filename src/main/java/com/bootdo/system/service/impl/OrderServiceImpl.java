package com.bootdo.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.app.config.Constants;
import com.bootdo.app.global.NotPayInfoException;
import com.bootdo.app.model.PaymentInfo;
import com.bootdo.app.util.OrderCodeUtil;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.app.zwlenum.OrderStatusEnum;
import com.bootdo.system.domain.BankInfoDO;
import com.bootdo.system.domain.PayAlipayInfoDO;
import com.bootdo.system.domain.PayWechatInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.OrderDao;
import com.bootdo.system.domain.OrderDO;
import com.bootdo.system.service.OrderService;



@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private RedisUtils redisUtils;
	
	@Override
	public OrderDO get(Integer id){
		return orderDao.get(id);
	}
	
	@Override
	public List<OrderDO> list(Map<String, Object> map){
		return orderDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderDao.count(map);
	}
	
	@Override
	public int save(OrderDO order){
		return orderDao.save(order);
	}
	
	@Override
	public int update(OrderDO order){
		return orderDao.update(order);
	}
	
	@Override
	public int remove(Integer id){
		return orderDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return orderDao.batchRemove(ids);
	}

	@Override
	public OrderDO createWechatOrder(PaymentInfo paymentInfo) throws NotPayInfoException {
		Object payInfoObj = redisUtils.getPaymentInfo(Constants.PAYMENTINFO_LIST.replace("{payType}", paymentInfo.getType()));
		if (payInfoObj == null) {
			throw new  NotPayInfoException("暂无可用收款方式 " + paymentInfo.getType());
		}

		PayWechatInfoDO payWechatInfoDO = (PayWechatInfoDO) payInfoObj;
		//判断当前收款方式的状态 @TODO


		OrderDO order = new OrderDO();
		order.setMid(payWechatInfoDO.getMid());
		order.setMerchantNo(paymentInfo.getMerchantNo());
		order.setOrderNo(OrderCodeUtil.getOrderCode(null));
		order.setCreateTime(new Date());
		order.setStatus(OrderStatusEnum.PRE_PAY.getKey());
		order.setAmount(paymentInfo.getAmount());
		order.setReallyAmount(paymentInfo.getAmount());
		order.setPaymentInfo(JSONObject.toJSONString(payWechatInfoDO));
		order.setPayType(paymentInfo.getType());
		order.setMerchantOrderNo(paymentInfo.getMerchantOrderNo());
		save(order);
		redisUtils.set(order.getOrderNo(),order);
		return order;
	}

	@Override
	public OrderDO createAlipayOrder(PaymentInfo paymentInfo) throws NotPayInfoException{
		Object payInfoObj = redisUtils.getPaymentInfo(Constants.PAYMENTINFO_LIST.replace("{payType}", paymentInfo.getType()));
		if (payInfoObj == null) {
			throw new  NotPayInfoException("暂无可用收款方式 " + paymentInfo.getType());
		}

		PayAlipayInfoDO payWechatInfoDO = (PayAlipayInfoDO) payInfoObj;

		OrderDO order = new OrderDO();
		order.setMid(payWechatInfoDO.getMid());
		order.setMerchantNo(paymentInfo.getMerchantNo());
		order.setOrderNo(OrderCodeUtil.getOrderCode(null));
		order.setCreateTime(new Date());
		order.setStatus(OrderStatusEnum.PRE_PAY.getKey());
		order.setAmount(paymentInfo.getAmount());
		order.setReallyAmount(paymentInfo.getAmount());
		order.setPaymentInfo(JSONObject.toJSONString(payWechatInfoDO));
		order.setPayType(paymentInfo.getType());
		order.setMerchantOrderNo(paymentInfo.getMerchantOrderNo());
		save(order);
		redisUtils.set(order.getOrderNo(),order);
		return order;
	}

	@Override
	public OrderDO createBankOrder(PaymentInfo paymentInfo) throws NotPayInfoException{
		Object payInfoObj = redisUtils.getPaymentInfo(Constants.PAYMENTINFO_LIST.replace("{payType}", paymentInfo.getType()));
		if (payInfoObj == null) {
			throw new  NotPayInfoException("暂无可用收款方式 " + paymentInfo.getType());
		}

		BankInfoDO payWechatInfoDO = (BankInfoDO) payInfoObj;
		OrderDO order = new OrderDO();
		order.setMid(payWechatInfoDO.getMid());
		order.setMerchantNo(paymentInfo.getMerchantNo());
		order.setOrderNo(OrderCodeUtil.getOrderCode(null));
		order.setCreateTime(new Date());
		order.setStatus(OrderStatusEnum.PRE_PAY.getKey());
		order.setAmount(paymentInfo.getAmount());
		order.setReallyAmount(paymentInfo.getAmount());
		order.setPaymentInfo(JSONObject.toJSONString(payWechatInfoDO));
		order.setPayType(paymentInfo.getType());
		order.setMerchantOrderNo(paymentInfo.getMerchantOrderNo());
		save(order);
		redisUtils.set(order.getOrderNo(),order);
		return order;
	}

}
