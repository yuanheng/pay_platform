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
public class PayAlipayInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//码商id
	private Integer mid;
	//收款人
	private String name;
	//
	private String status;
	//二维码地址
	private String imgUrl;
	//
	private String minAmount;
	//
	private String maxAmount;
	//账号
	private String account;
	//备注
	private String remark;

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
	 * 设置：码商id
	 */
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	/**
	 * 获取：码商id
	 */
	public Integer getMid() {
		return mid;
	}
	/**
	 * 设置：收款人
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：收款人
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：二维码地址
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
	 * 获取：二维码地址
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	/**
	 * 设置：
	 */
	public void setMinAmount(String minAmount) {
		this.minAmount = minAmount;
	}
	/**
	 * 获取：
	 */
	public String getMinAmount() {
		return minAmount;
	}
	/**
	 * 设置：
	 */
	public void setMaxAmount(String maxAmount) {
		this.maxAmount = maxAmount;
	}
	/**
	 * 获取：
	 */
	public String getMaxAmount() {
		return maxAmount;
	}
	/**
	 * 设置：账号
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * 获取：账号
	 */
	public String getAccount() {
		return account;
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
}
