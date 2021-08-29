package com.bootdo.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.app.config.Constants;
import com.bootdo.app.global.NotPayInfoException;
import com.bootdo.app.model.PaymentInfo;
import com.bootdo.app.util.Encript;
import com.bootdo.app.util.OrderCodeUtil;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.app.zwlenum.OrderStatusEnum;
import com.bootdo.app.zwlenum.PayTypeEnum;
import com.bootdo.app.zwlenum.StatusEnum;
import com.bootdo.system.domain.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.OrderDao;
import com.bootdo.system.service.OrderService;



@Service
public class OrderServiceImpl implements OrderService {
	private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

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

		boolean flag = false;
		Integer num = 1;
		while (!flag) {
			Object payInfoObj = redisUtils.getPaymentInfo(Constants.PAYMENTINFO_LIST.replace("{payType}", PayTypeEnum.WECHAT_CODE.getKey()));
			if (payInfoObj == null) {
				throw new  NotPayInfoException("暂无可用收款方式 " + paymentInfo.getType());
			}

			PayWechatInfoDO payWechatInfoDO = (PayWechatInfoDO) payInfoObj;
			Long userid = payWechatInfoDO.getMid();
			String key = Constants.getPayInfoKey(PayTypeEnum.WECHAT_CODE.getKey(),userid,payWechatInfoDO.getId().intValue());
			if (redisUtils.hasKey(key)) {
				PayWechatInfoDO tempPayWechatInfoDO = (PayWechatInfoDO) redisUtils.get(key);
				if (tempPayWechatInfoDO.getStatus().equals(StatusEnum.ENABLE.getKey())) {
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
					order.setRemark(paymentInfo.getRemark());
					save(order);
					redisUtils.addPaymentInfo(Constants.PAYMENTINFO_LIST.replace("{payType}", PayTypeEnum.WECHAT_CODE.getKey()) ,payWechatInfoDO);
					return  order;
				} else {
					if (num > 5) {
						throw new  NotPayInfoException("暂无可用收款方式 " + paymentInfo.getType());
					}
					num ++;
					try {
						Thread.sleep(500);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		return  null;
	}

	@Override
	public OrderDO createAlipayOrder(PaymentInfo paymentInfo) throws NotPayInfoException{
		boolean flag = false;
		Integer num = 1;
		while (!flag) {
			String payListKey = Constants.getPayInfoListKey(PayTypeEnum.APLIPAY_CODE.getKey());
			Object payInfoObj = redisUtils.getPaymentInfo(payListKey);
			if (payInfoObj == null) {
				throw new  NotPayInfoException("暂无可用收款方式 " + paymentInfo.getType());
			}

			PayAlipayInfoDO payWechatInfoDO = (PayAlipayInfoDO) payInfoObj;
			Long userid = payWechatInfoDO.getMid();
			String key = Constants.getPayInfoKey(PayTypeEnum.APLIPAY_CODE.getKey(),userid,payWechatInfoDO.getId().intValue());
			if (redisUtils.hasKey(key)) {
				PayAlipayInfoDO tempPayWechatInfoDO = (PayAlipayInfoDO) redisUtils.get(key);
				if (tempPayWechatInfoDO.getStatus().equals(StatusEnum.ENABLE.getKey())) {
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
					order.setRemark(paymentInfo.getRemark());
					save(order);
					redisUtils.addPaymentInfo(Constants.PAYMENTINFO_LIST.replace("{payType}", PayTypeEnum.APLIPAY_CODE.getKey()) , payWechatInfoDO);
					return  order;
				} else {
					if (num > 5) {
						throw new  NotPayInfoException("暂无可用收款方式 " + paymentInfo.getType());
					}
					num++;
					try {
						Thread.sleep(500);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		return  null;
	}

	@Override
	public OrderDO createBankOrder(PaymentInfo paymentInfo) throws NotPayInfoException{
		boolean flag = false;
		Integer num = 1;
		while (!flag) {
			Object payInfoObj = redisUtils.getPaymentInfo(Constants.PAYMENTINFO_LIST.replace("{payType}", PayTypeEnum.BANK_CODE.getKey()));
			if (payInfoObj == null) {
				throw new  NotPayInfoException("暂无可用收款方式 " + paymentInfo.getType());
			}

			BankInfoDO payWechatInfoDO = (BankInfoDO) payInfoObj;
			Long userid = payWechatInfoDO.getMid();
			String key = Constants.getPayInfoKey(PayTypeEnum.BANK_CODE.getKey(), userid ,payWechatInfoDO.getId().intValue());
			if (redisUtils.hasKey(key)) {
				BankInfoDO tempPayWechatInfoDO = (BankInfoDO) redisUtils.get(key);
				if (tempPayWechatInfoDO.getStatus().equals(StatusEnum.ENABLE.getKey())) {
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
					order.setRemark(paymentInfo.getRemark());
					save(order);
					redisUtils.addPaymentInfo(Constants.PAYMENTINFO_LIST.replace("{payType}", PayTypeEnum.BANK_CODE.getKey()) , payWechatInfoDO);
					return  order;
				} else {
					if (num > 5) {
						throw new  NotPayInfoException("暂无可用收款方式 " + paymentInfo.getType());
					}
					num++;
					try {
						Thread.sleep(500);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return  null;
	}


	@Override
	public OrderDO notifyMerchant(OrderDO orderDO) {
		String merchantKey = Constants.getMerchantNoKey(orderDO.getMerchantNo());
		MerchantDO merchantDO = (MerchantDO) redisUtils.get(merchantKey);
		String secreKey = merchantDO.getSecretKey();
		if (orderDO.getStatus().equals(OrderStatusEnum.FINISHED_PAY.getKey())) {
			//支付成功 回调商户
			String callback = merchantDO.getCallbackUrl();
			try {
				String result = Jsoup.connect(callback)
								.ignoreContentType(true)
								.method(Connection.Method.POST)
								.timeout(3000)
								.data("sign",getSign(secreKey,orderDO))
								.data("merchantNo",orderDO.getMerchantNo())
								.data("merchantOrderNo", orderDO.getMerchantOrderNo())
								.data("amount",orderDO.getAmount()).execute().body();

				if (result.equals("OK")) {
					orderDO.setStatus(OrderStatusEnum.CALLBACK_SUCCESS.getKey());
					orderDO.setFinishTime(new Date());
					orderDO.setRemark("OK");
				} else {
					orderDO.setStatus(OrderStatusEnum.CALLBACK_FAILED.getKey());
					orderDO.setFinishTime(new Date());
					orderDO.setRemark(result);
				}
			} catch (Exception e) {
				logger.error("callback merchant faild, retry ", e );
				orderDO.setRemark(e.getMessage());
			}
		}
		return orderDO;
	}

	@Override
	public int cancelOrder(OrderDO orderDO) {
		orderDO.setFinishTime(new Date());
		return orderDao.cancelOrder(orderDO);
	}



	private String getSign(String secretKey, OrderDO orderDO) {
		String signstr = "amount="+orderDO.getAmount()+"&merchantNo="+orderDO.getMerchantNo()+"&merchantOrderNo="+orderDO.getMerchantOrderNo()+"&secretKey="+secretKey;
		return Encript.md5(signstr);
	}


}
