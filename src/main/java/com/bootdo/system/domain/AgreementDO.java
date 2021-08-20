package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-08 16:50:14
 */
public class AgreementDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//会员id
	private Integer mid;
	//类型
	private String type;
	//开始时间
	private Date startTime;
	//结束时间
	private Date endTime;
	//佣金比例
	private String percent;
	//手机号
	private String mobile;
	//合约状态
	private String status;
	//合约的金额
	private String amount;
	//冻结的金额
	private String freeAmount;


	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：会员id
	 */
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	/**
	 * 获取：会员id
	 */
	public Integer getMid() {
		return mid;
	}
	/**
	 * 设置：类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置：佣金比例
	 */
	public void setPercent(String percent) {
		this.percent = percent;
	}
	/**
	 * 获取：佣金比例
	 */
	public String getPercent() {
		return percent;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：合约状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：合约状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：合约的金额
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * 获取：合约的金额
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * 设置：冻结的金额
	 */
	public void setFreeAmount(String freeAmount) {
		this.freeAmount = freeAmount;
	}
	/**
	 * 获取：冻结的金额
	 */
	public String getFreeAmount() {
		return freeAmount;
	}

	@Override
	public String toString() {
		return "AgreementDO{" +
						"id=" + id +
						", mid=" + mid +
						", type='" + type + '\'' +
						", startTime=" + startTime +
						", percent='" + percent + '\'' +
						", mobile='" + mobile + '\'' +
						", status='" + status + '\'' +
						", amount='" + amount + '\'' +
						", freeAmount='" + freeAmount + '\''+
						'}';
	}
}
