package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-08-20 21:24:35
 */
public class OrderDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String orderNo;
	//商户No
	private String merchantNo;
	//订单额度
	private String amount;
	//实际支付额度
	private String reallyAmount;
	//创建时间
	private Date createTime;
	//支付完成
	private Date finishTime;
	//状态 
待支付 pre_pay
已支付 finished_pay
已取消 cancel
回调成功 callback_success
回调失败 callback_failed
	private String status;
	//收款信息
	private String paymentInfo;
	//备注信息
	private String remark;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取：
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * 设置：商户No
	 */
	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
	/**
	 * 获取：商户No
	 */
	public String getMerchantNo() {
		return merchantNo;
	}
	/**
	 * 设置：订单额度
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * 获取：订单额度
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * 设置：实际支付额度
	 */
	public void setReallyAmount(String reallyAmount) {
		this.reallyAmount = reallyAmount;
	}
	/**
	 * 获取：实际支付额度
	 */
	public String getReallyAmount() {
		return reallyAmount;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：支付完成
	 */
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	/**
	 * 获取：支付完成
	 */
	public Date getFinishTime() {
		return finishTime;
	}
	/**
	 * 设置：状态 
待支付 pre_pay
已支付 finished_pay
已取消 cancel
回调成功 callback_success
回调失败 callback_failed
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态 
待支付 pre_pay
已支付 finished_pay
已取消 cancel
回调成功 callback_success
回调失败 callback_failed
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：收款信息
	 */
	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	/**
	 * 获取：收款信息
	 */
	public String getPaymentInfo() {
		return paymentInfo;
	}
	/**
	 * 设置：备注信息
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注信息
	 */
	public String getRemark() {
		return remark;
	}
}
