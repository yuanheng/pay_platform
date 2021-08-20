package com.bootdo.system.domain;

import java.io.Serializable;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-08 16:50:14
 */
public class MemberLevelDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//会员id
	private Integer mid;
	//父会员id
	private Integer parentMid;
	//祖父id
	private Integer grandMid;
	//曾祖父
	private Integer greatMid;

	//是否有效投资
	private String status;
	//备注信息
	private String remark;
	//代理级别
	private String type;
	//直接会员数
	private Integer level1;
	//间接会员数
	private Integer level2;
	//第三级会员数量
	private Integer level3;




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
	 * 设置：父会员id
	 */
	public void setParentMid(Integer parentMid) {
		this.parentMid = parentMid;
	}
	/**
	 * 获取：父会员id
	 */
	public Integer getParentMid() {
		return parentMid;
	}
	/**
	 * 设置：祖父id
	 */
	public void setGrandMid(Integer grandMid) {
		this.grandMid = grandMid;
	}
	/**
	 * 获取：祖父id
	 */
	public Integer getGrandMid() {
		return grandMid;
	}
	/**
	 * 设置：是否有效投资
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：是否有效投资
	 */
	public String getStatus() {
		return status;
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
	/**
	 * 设置：代理级别
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：代理级别
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：直接会员数
	 */
	public void setLevel1(Integer level1) {
		this.level1 = level1;
	}
	/**
	 * 获取：直接会员数
	 */
	public Integer getLevel1() {
		return level1;
	}
	/**
	 * 设置：间接会员数
	 */
	public void setLevel2(Integer level2) {
		this.level2 = level2;
	}
	/**
	 * 获取：间接会员数
	 */
	public Integer getLevel2() {
		return level2;
	}


	public Integer getGreatMid() {
		return greatMid;
	}

	public void setGreatMid(Integer greatMid) {
		this.greatMid = greatMid;
	}

	public Integer getLevel3() {
		return level3;
	}

	public void setLevel3(Integer level3) {
		this.level3 = level3;
	}


}
