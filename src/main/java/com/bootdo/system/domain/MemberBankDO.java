package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-11-23 21:54:22
 */
public class MemberBankDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//银行名称
	private String backName;
	//支行名称
	private String branchName;
	//银行卡号
	private String cardNo;
	//创建时间
	private Date createTime;
	//状态
	private Integer status;
	//备注
	private String remark;
	//会员id
	private Integer mid;
	//名称
	private String username;

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
	 * 设置：银行名称
	 */
	public void setBackName(String backName) {
		this.backName = backName;
	}
	/**
	 * 获取：银行名称
	 */
	public String getBackName() {
		return backName;
	}
	/**
	 * 设置：支行名称
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * 获取：支行名称
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * 设置：银行卡号
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * 获取：银行卡号
	 */
	public String getCardNo() {
		return cardNo;
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
	 * 设置：状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
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
	 * 设置：名称
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：名称
	 */
	public String getUsername() {
		return username;
	}
}
