package com.bootdo.app.zwlenum;

/**
 * 发送短信类型枚举
 * @author Sylow
 * @version v1.0,2016年7月6日
 * @since v6.1
 */
public enum TradeTypeEnum {
	//充值
	INVEST_MONEY("invest_money","会员充值"),
	GIF_MONEY("gif_money","新手红包"),
	//提现
	WITHDRAW("withdraw","会员提现"),
	//购买
	PURCHASE("purchase","签约代还"),
	//购买返现
	PURCHASE_BACK("purchase_back","代还回报"),
	//购买返现
	MEMBER_PURCHASE_BACK("member_purchase_back","下级代还奖励"),
	//保障金
	CASH_DEPOSIT("cash_deposit","代还保证金"),
	//解冻
	UN_FREEZE("un_freeze","保证金解冻");


	TradeTypeEnum(String key,String desc) {
		this.key = key;
		this.typeDesc = desc;
	}

    public static String getTypeDescByType(String  type){
		return TradeTypeEnum.valueOf(type).typeDesc;
	}
    @Override
    public String toString(){
		return this.key;
	}

    private String key;
	private String typeDesc;

}
