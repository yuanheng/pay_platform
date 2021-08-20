package com.bootdo.system.domain;

import com.bootdo.app.util.AmountUtil;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-02 11:24:17
 */
public class WithdrawDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Integer id;
	//会员id
	private Integer mid;
	//提现额度
	private String amount;
	//银行卡号
	private String bankNo;
	//收款人姓名
	private String bankName;
	//第三方账号
	private String thirdAccount;
	//微信或支付宝
	private String thirdType;
	//提现时间
	private Date createTime;
	//完成时间
	private Date finishedTime;
	//备注
	private String remark;
	//交易id
	private Integer tradeId;
	//状态
	private String status;

	private String factAmount;

	public String getFactAmount() {
		return factAmount;
	}

	public void setFactAmount(String factAmount) {
		this.factAmount = factAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 设置：主键id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键id
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
	 * 设置：银行卡号
	 */
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	/**
	 * 获取：银行卡号
	 */
	public String getBankNo() {
		return bankNo;
	}
	/**
	 * 设置：收款人姓名
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * 获取：收款人姓名
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * 设置：第三方账号
	 */
	public void setThirdAccount(String thirdAccount) {
		this.thirdAccount = thirdAccount;
	}
	/**
	 * 获取：第三方账号
	 */
	public String getThirdAccount() {
		return thirdAccount;
	}
	/**
	 * 设置：微信或支付宝
	 */
	public void setThirdType(String thirdType) {
		this.thirdType = thirdType;
	}
	/**
	 * 获取：微信或支付宝
	 */
	public String getThirdType() {
		return thirdType;
	}
	/**
	 * 设置：提现时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：提现时间
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
	 * 设置：交易id
	 */
	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}
	/**
	 * 获取：交易id
	 */
	public Integer getTradeId() {
		return tradeId;
	}
}
