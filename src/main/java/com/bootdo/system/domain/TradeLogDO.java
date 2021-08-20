package com.bootdo.system.domain;


import javax.xml.crypto.KeySelector.Purpose;
import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-02 11:24:17
 */
public class TradeLogDO implements Serializable {

	private static final long serialVersionUID = 1L;
	//主键
	private Integer id;
	//流水单号
	private String tradeNo;
	//交易额度
	private String amount;
	//真实到账
	private String factAmount;
	//交易类型
	private String type;
	//交易时间
	private Date createTime;
	//完成时间
	private Date finishedTime;
	//外部的交易单号
	private String tradeOutNo;
	//备注
	private String remark;
	//会员id
	private Integer mid;
	//状态
	private String status;
	//当前用户手机号
	private String mobile;
	private String mFreeTotal;

	private Integer agreeId;

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getmFreeTotal() {
		return mFreeTotal;
	}

	public void setmFreeTotal(String mFreeTotal) {
		this.mFreeTotal = mFreeTotal;
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
	 * 设置：流水单号
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	/**
	 * 获取：流水单号
	 */
	public String getTradeNo() {
		return tradeNo;
	}
	/**
	 * 设置：交易额度
	 */
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
	 * 设置：真实到账
	 */
	public void setFactAmount(String factAmount) {
		this.factAmount = factAmount;
	}
	/**
	 * 获取：真实到账
	 */
	public String getFactAmount() {
		return this.factAmount;
	}
	/**
	 * 设置：交易类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：交易类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：交易时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：交易时间
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
	 * 设置：外部的交易单号
	 */
	public void setTradeOutNo(String tradeOutNo) {
		this.tradeOutNo = tradeOutNo;
	}
	/**
	 * 获取：外部的交易单号
	 */
	public String getTradeOutNo() {
		return tradeOutNo;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 设置：当前用户手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：当前用户手机号
	 */
	public String getMobile() {
		return this.mobile;
	}


	public Integer getAgreeId() {
		return this.agreeId;
	}

	public void setAgreeId(Integer agreeId) {
		this.agreeId = agreeId;
	}


	public TradeLogDO() {

	}

	public TradeLogDO(Builder builder) {
		this.id = builder.id;
		this.tradeNo = builder.tradeNo;
		this.amount = builder.amount;
		this.factAmount = builder.factAmount;
		this.type = builder.type;
		this.createTime = builder.createTime;
		this.finishedTime = builder.finishedTime;
		this.tradeOutNo = builder.tradeOutNo;
		this.remark = builder.remark;
		this.mid = builder.mid;
		this.status = builder.status;
		this.mobile = builder.mobile;
		this.mFreeTotal = builder.mFreeTotal;
		this.agreeId = builder.agreeId;
	}



	public static class Builder{

		//主键
		private Integer id;
		//流水单号
		private String tradeNo;
		//交易额度
		private String amount;
		//真实到账
		private String factAmount;
		//交易类型
		private String type;
		//交易时间
		private Date createTime;
		//完成时间
		private Date finishedTime;
		//外部的交易单号
		private String tradeOutNo;
		//备注
		private String remark;
		//会员id
		private Integer mid;
		//状态
		private String status;
		//当前用户手机号
		private String mobile;
		private String mFreeTotal;

		private Integer agreeId;



		public Builder id(Integer id) {
			this.id = id;
			return this;
		}
		public Builder tradeNo(String tradeNo) {
			this.tradeNo = tradeNo;
			return this;
		}
		public Builder amount(String amount) {
			this.amount = amount;
			return this;
		}
		public Builder factAmount(String  factAmount) {
			this.factAmount = factAmount;
			return this;
		}
		public Builder type(String type) {
			this.type = type;
			return this;
		}
		public Builder createTime(Date createTime) {
			this.createTime  = createTime;
			return this;
		}
		public Builder finishedTime(Date finishedTime) {
			this.finishedTime = finishedTime;
			return this;
		}
		public Builder tradeOutNo(String tradeOutNo) {
			this.tradeOutNo = tradeOutNo;
			return this;
		}
		public Builder remark(String remark) {
			this.remark = remark;
			return this;
		}
		public Builder mid(Integer mid) {
			this.mid = mid;
			return this;
		}
		public Builder status(String status) {
			this.status = status;
			return this;
		}
		public Builder mobile(String mobile) {
			this.mobile = mobile;
			return this;
		}
		public Builder mFreeTotal(String mFreeTotal) {
			this.mFreeTotal = mFreeTotal;
			return this;
		}
		public Builder agreeId(Integer agreeId) {
			this.agreeId = agreeId;
			return this;
		}


		public TradeLogDO build() {
			return new TradeLogDO(this);
		}

	}


}
