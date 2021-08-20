package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 *
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-11-23 19:37:54
 */
public class UstdLogDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//id
	private Integer id;
	//会员id
	private Integer mid;
	//会员名称
	private String username;
	//银行卡
	private String bankName;
	//支行信息
	private String bankBranch;
	// 卡号
	private String bankNo;
	private String reallyName;

	//订单号
	private String no;
	//u币个数
	private String amount;
	//状态
	private int status;

	//状态
	private String statusDesc;


	//备注
	private String remark;
	//来源地址
	private String source;
	//目标地址
	private String target;
	//创建时间
	private Date createTime;
	//完成时间
	private Date finishedTime;
	//指导价格
	private String cnyPrice;
	//总u币数
	private String totalAmount;
	//奖励金额
	private String profileAmount;
	//返现的总利润
	private String backTotalAmount;
	//操作者
	private String operator;

	private Date currentDate;


	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
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
	 * 设置：订单号
	 */
	public void setNo(String no) {
		this.no = no;
	}
	/**
	 * 获取：订单号
	 */
	public String getNo() {
		return no;
	}
	/**
	 * 设置：u币个数
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * 获取：u币个数
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public int getStatus() {
		return status;
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
	 * 设置：来源地址
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * 获取：来源地址
	 */
	public String getSource() {
		return source;
	}
	/**
	 * 设置：目标地址
	 */
	public void setTarget(String target) {
		this.target = target;
	}
	/**
	 * 获取：目标地址
	 */
	public String getTarget() {
		return target;
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
	 * 设置：指导价格
	 */
	public void setCnyPrice(String cnyPrice) {
		this.cnyPrice = cnyPrice;
	}
	/**
	 * 获取：指导价格
	 */
	public String getCnyPrice() {
		return cnyPrice;
	}
	/**
	 * 设置：总u币数
	 */
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * 获取：总u币数
	 */
	public String getTotalAmount() {
		return totalAmount;
	}
	/**
	 * 设置：奖励金额
	 */
	public void setProfileAmount(String profileAmount) {
		this.profileAmount = profileAmount;
	}
	/**
	 * 获取：奖励金额
	 */
	public String getProfileAmount() {
		return profileAmount;
	}
	/**
	 * 设置：返现的总利润
	 */
	public void setBackTotalAmount(String backTotalAmount) {
		this.backTotalAmount = backTotalAmount;
	}
	/**
	 * 获取：返现的总利润
	 */
	public String getBackTotalAmount() {
		return backTotalAmount;
	}
	/**
	 * 设置：操作者
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	/**
	 * 获取：操作者
	 */
	public String getOperator() {
		return operator;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getReallyName() {
		return reallyName;
	}

	public void setReallyName(String reallyName) {
		this.reallyName = reallyName;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
}
