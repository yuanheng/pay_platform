package com.bootdo.system.domain;


import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-02 11:24:17
 */
public class InvestMoneyDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//会员id
	private Integer mid;
	//状态
	private String status;
	//额度
	private String amount;
	//创建时间
	private Date createTime;
	//完成时间
	private Date finishedTime;
	//流水记录id
	private Integer tradeId;
	//备注
	private String remark;
	//手机号
	private String mobile;

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * 获取：交易额度
	 */
	public String getAmount() {
		return this.amount;
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
	 * 设置：完成时间
	 */
	public void setFinishedTime(Date finishedTime) {
		this.finishedTime = finishedTime;
	}
	/**
	 * 获取：完成时间
	 */
	public Date getFinishedTime() {
		return finishedTime;
	}
	/**
	 * 设置：流水记录id
	 */
	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}
	/**
	 * 获取：流水记录id
	 */
	public Integer getTradeId() {
		return tradeId;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
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
}
