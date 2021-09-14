package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-09-08 22:32:27
 */
public class TbOrderDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//订单号
	private String no;
	//订单金额
	private String amount;
	//账号
	private String account;
	//创建时间
	private Date createTime;
	//有效期
	private Integer validity;
	//状态
	private String status;
	//所属这
	private Long mid;
	//备注
	private String remark;
	//结束时间
	private Date endTime;
	//
	private String url;
	//
	private String extra;

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
	 * 设置：订单金额
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * 获取：订单金额
	 */
	public String getAmount() {
		return amount;
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
	 * 设置：有效期
	 */
	public void setValidity(Integer validity) {
		this.validity = validity;
	}
	/**
	 * 获取：有效期
	 */
	public Integer getValidity() {
		return validity;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：所属这
	 */
	public void setMid(Long mid) {
		this.mid = mid;
	}
	/**
	 * 获取：所属这
	 */
	public Long getMid() {
		return mid;
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
	 * 设置：
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：
	 */
	public void setExtra(String extra) {
		this.extra = extra;
	}
	/**
	 * 获取：
	 */
	public String getExtra() {
		return extra;
	}
}
