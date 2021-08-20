package com.bootdo.app.zwlenum;

/**
 * 发送短信类型枚举
 * @author Sylow
 * @version v1.0,2016年7月6日
 * @since v6.1
 */
public enum SmsTypeKeyEnum {
	
	//普通校验
	CHECK("check","SMS_215170122"),
	//登录
	LOGIN("login","SMS_215170121"),
	//绑定
	BINDING("binding","SMS_215170122"),
	//注册
	REGISTER("register","SMS_215170119"),
	//找回密码
	BACKPASSWORD("back_password","SMS_215170118"),
	//提现
	WITH_DRAW("with_draw","SMS_215170122"),
	//修改密码
	UPDATE_PASSWORD("update_password","SMS_215170118");
	
	// 构造方法
	SmsTypeKeyEnum(String key,String code) {
        this.key = key;
        this.code = code;
    }
	

    @Override
    public String toString(){
		return this.key;
	}

	public String getCode() {
		return this.code;
	}

    private String key;
    private String code;
	
}
