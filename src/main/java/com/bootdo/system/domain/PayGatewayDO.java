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
public class PayGatewayDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//支付名称
	private String name;
	//商户名称
	private String mchId;
	//秘钥
	private String key;
	//支付类型
	private String type;
	//logo
	private String logo;
	//回调url
	private String notify;
	//结果
	private String result;

	private String remark;

	//enable ，disabled
	private String status;

	private String description;


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	 * 设置：支付名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：支付名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：商户名称
	 */
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	/**
	 * 获取：商户名称
	 */
	public String getMchId() {
		return mchId;
	}
	/**
	 * 设置：秘钥
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * 获取：秘钥
	 */
	public String getKey() {
		return key;
	}
	/**
	 * 设置：支付类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：支付类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：logo
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	/**
	 * 获取：logo
	 */
	public String getLogo() {
		return logo;
	}
	/**
	 * 设置：回调url
	 */
	public void setNotify(String notify) {
		this.notify = notify;
	}
	/**
	 * 获取：回调url
	 */
	public String getNotify() {
		return notify;
	}
	/**
	 * 设置：结果
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * 获取：结果
	 */
	public String getResult() {
		return result;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

