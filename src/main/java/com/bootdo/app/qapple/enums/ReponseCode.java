package com.bootdo.app.qapple.enums;

public enum ReponseCode {
	/**成功*/
	SUCCESS(200,"操作成功")
	/**系统异常*/
	,SYSTEM_ERR(500,"系统异常")
	/** 无效的商家预下单签名异常 */
	,INVALID_PREPAY_SIGN_EXCEPTION(613, "签名验证不通过");

	/**状态码*/
	private Integer status;
	/**消息内容编码*/
	private String msgCode;

	private ReponseCode(Integer status, String msgCode){
		this.status = status;
		this.msgCode = msgCode;
	}

	public Integer getStatus() {
		return status;
	}
	public String getMsgCode() {
		return msgCode;
	}

	public static ReponseCode parse(Integer status){
		if(SUCCESS.status.compareTo(status)==0){return SUCCESS;}
		else if(SYSTEM_ERR.status.compareTo(status)==0){return SYSTEM_ERR;}
		else if (INVALID_PREPAY_SIGN_EXCEPTION.status.compareTo(status)==0){return INVALID_PREPAY_SIGN_EXCEPTION;}
		throw new RuntimeException("错误码:"+status+"未定义");
	}

}
