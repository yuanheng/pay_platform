package com.bootdo.coin.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-03-13 16:56:17
 */
public class CoinTaskInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Integer id;
	//会员id
	private Integer mid;
	//持仓总额
	private String totalAmount;
	//持仓量
	private String totalPosition;
	//
	private Integer currentOrderNum;
	//持仓均价
	private String avgPrice;
	//当前价格
	private String currentPrice;
	//总收益
	private String totalProfit;
	//盈亏幅度
	private String profitLossRatio;
	//当前盈亏
	private String currentProfitLossAmount;
	//交易次数
	private Integer dealNum;
	//首单额度
	private String firstAmount;
	//计划做单数
	private Integer planOrderNum;
	//补仓比例
	private String coverRatio;
	//补仓复位
	private String coverResetRatio;
	//补仓增幅
	private String coverAddRatio;
	//补仓回调比例
	private String coverCallbackRatio;
	//止盈比例
	private String profitRatio;
	//止盈比例回调
	private String profitCallbackRatio;
	//创建时间
	private long createTime;
	//最后更新时间
	private long modifyTime;
	//状态
	private String status;
	//是否删除
	private Integer deleted;
	//买的币种
	private String symbol;

	//任务的类型

	private String type;

	//首单价格
	private String firstPrice;

	//上一次的价格
	private String lastPrice;


	//任务的订单id

	private List<Long> orderIds = new ArrayList<>();


	public String getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(String lastPrice) {
		this.lastPrice = lastPrice;
	}

	public String getFirstPrice() {
		return firstPrice;
	}

	public void setFirstPrice(String firstPrice) {
		this.firstPrice = firstPrice;
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
	/**
	 * 设置：持仓总额
	 */
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * 获取：持仓总额
	 */
	public String getTotalAmount() {
		return totalAmount;
	}
	/**
	 * 设置：持仓量
	 */
	public void setTotalPosition(String totalPosition) {
		this.totalPosition = totalPosition;
	}
	/**
	 * 获取：持仓量
	 */
	public String getTotalPosition() {
		return totalPosition;
	}
	/**
	 * 设置：
	 */
	public void setCurrentOrderNum(Integer currentOrderNum) {
		this.currentOrderNum = currentOrderNum;
	}
	/**
	 * 获取：
	 */
	public Integer getCurrentOrderNum() {
		return currentOrderNum;
	}
	/**
	 * 设置：持仓均价
	 */
	public void setAvgPrice(String avgPrice) {
		this.avgPrice = avgPrice;
	}
	/**
	 * 获取：持仓均价
	 */
	public String getAvgPrice() {
		return avgPrice;
	}
	/**
	 * 设置：当前价格
	 */
	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}
	/**
	 * 获取：当前价格
	 */
	public String getCurrentPrice() {
		return currentPrice;
	}
	/**
	 * 设置：总收益
	 */
	public void setTotalProfit(String totalProfit) {
		this.totalProfit = totalProfit;
	}
	/**
	 * 获取：总收益
	 */
	public String getTotalProfit() {
		return totalProfit;
	}
	/**
	 * 设置：盈亏幅度
	 */
	public void setProfitLossRatio(String profitLossRatio) {
		this.profitLossRatio = profitLossRatio;
	}
	/**
	 * 获取：盈亏幅度
	 */
	public String getProfitLossRatio() {
		return profitLossRatio;
	}
	/**
	 * 设置：当前盈亏
	 */
	public void setCurrentProfitLossAmount(String currentProfitLossAmount) {
		this.currentProfitLossAmount = currentProfitLossAmount;
	}
	/**
	 * 获取：当前盈亏
	 */
	public String getCurrentProfitLossAmount() {
		return currentProfitLossAmount;
	}
	/**
	 * 设置：交易次数
	 */
	public void setDealNum(Integer dealNum) {
		this.dealNum = dealNum;
	}
	/**
	 * 获取：交易次数
	 */
	public Integer getDealNum() {
		return dealNum;
	}
	/**
	 * 设置：首单额度
	 */
	public void setFirstAmount(String firstAmount) {
		this.firstAmount = firstAmount;
	}
	/**
	 * 获取：首单额度
	 */
	public String getFirstAmount() {
		return firstAmount;
	}
	/**
	 * 设置：计划做单数
	 */
	public void setPlanOrderNum(Integer planOrderNum) {
		this.planOrderNum = planOrderNum;
	}
	/**
	 * 获取：计划做单数
	 */
	public Integer getPlanOrderNum() {
		return planOrderNum;
	}
	/**
	 * 设置：补仓比例
	 */
	public void setCoverRatio(String coverRatio) {
		this.coverRatio = coverRatio;
	}
	/**
	 * 获取：补仓比例
	 */
	public String getCoverRatio() {
		return coverRatio;
	}
	/**
	 * 设置：补仓复位
	 */
	public void setCoverResetRatio(String coverResetRatio) {
		this.coverResetRatio = coverResetRatio;
	}
	/**
	 * 获取：补仓复位
	 */
	public String getCoverResetRatio() {
		return coverResetRatio;
	}
	/**
	 * 设置：补仓增幅
	 */
	public void setCoverAddRatio(String coverAddRatio) {
		this.coverAddRatio = coverAddRatio;
	}
	/**
	 * 获取：补仓增幅
	 */
	public String getCoverAddRatio() {
		return coverAddRatio;
	}
	/**
	 * 设置：补仓回调比例
	 */
	public void setCoverCallbackRatio(String coverCallbackRatio) {
		this.coverCallbackRatio = coverCallbackRatio;
	}
	/**
	 * 获取：补仓回调比例
	 */
	public String getCoverCallbackRatio() {
		return coverCallbackRatio;
	}
	/**
	 * 设置：止盈比例
	 */
	public void setProfitRatio(String profitRatio) {
		this.profitRatio = profitRatio;
	}
	/**
	 * 获取：止盈比例
	 */
	public String getProfitRatio() {
		return profitRatio;
	}
	/**
	 * 设置：止盈比例回调
	 */
	public void setProfitCallbackRatio(String profitCallbackRatio) {
		this.profitCallbackRatio = profitCallbackRatio;
	}
	/**
	 * 获取：止盈比例回调
	 */
	public String getProfitCallbackRatio() {
		return profitCallbackRatio;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 设置：是否删除
	 */
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	/**
	 * 获取：是否删除
	 */
	public Integer getDeleted() {
		return deleted;
	}
	/**
	 * 设置：买的币种
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * 获取：买的币种
	 */
	public String getSymbol() {
		return symbol;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Long> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(List<Long> orderIds) {
		this.orderIds = orderIds;
	}
}
