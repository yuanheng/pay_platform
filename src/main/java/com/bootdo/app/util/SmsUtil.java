package com.bootdo.app.util;


import com.alibaba.fastjson.JSONObject;
import com.bootdo.app.qapple.util.HttpClient;
import com.bootdo.app.zwlenum.SmsTypeKeyEnum;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.system.service.MemberService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信相关通用方法
 * @author Sylow
 * @version v1.0,2016年7月6日
 * @since v6.1
 */
@Component
public class SmsUtil {

	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	private MemberService memberService;

	private String accessKeyId = "LTAI5t7sf616QPtPdNee4zji";
	private String accessKeySecret = "tM4HiIOVzgVL9Tq2iszfLq1nvEX3zq";

	// 短信验证码session前缀
	private static final String SMS_CODE_PREFIX = "es_sms_";

	// 短信验证间隔时间session前缀
	private static final String INTERVAL_TIME_PREFIX = "es_interval_";

	// 发送时间间隔
	private static final double SEND_INTERVAL = 60d;

	// 商标前缀
	private static final String BRAND_PREFIX = "ZWL";

	private static final Logger LOGGER = LoggerFactory.getLogger(SmsUtil.class);

	//短信超时时间前缀
	private static final String SENDTIME_PREFIX = "es_sendtime";
	//短信过期时间
	private static final Long SMS_CODE_TIMEOUT = 120L;


	/**
	 * 发送短信验证码
	 * @param mobile 手机号
	 * @param key 类型key枚举 {@link SmsTypeKeyEnum}
	 * @param isCheckRegister 是否判断已经注册  check用的
	 * @exception RuntimeException 发送短信程序出错异常
	 * @return 发送结果 Map<String, Object> 其中key=state_code值{0=发送失败，1=发送成功,2=发送限制(操作过快等等限制)},key=msg 值为{提示消息}
	 */
	public Map<String, Object> sendMobileSms(String mobile, String key, int isCheckRegister) {

		Map<String, Object> result = new HashMap<>();

		try {

			//防止 空值
			if (key == null || "".equals(key)) {

				// 默认为登录
				key = SmsTypeKeyEnum.LOGIN.toString();
			}
			//如果手机号格式不对
			if (!Validator.isMobile(mobile) ) {
				result.put("state_code", 2);
				result.put("msg", "手机号码格式错误");
				return result;
			}

			// 判断是否允许可以发送
			if (!validIsCanSendSms(key)) {
				result.put("state_code", 2);
				result.put("msg", "您的操作过快，请休息一下");
				return result;
			}

			//随机生成的动态码
			String dynamicCode = "" + (int)((Math.random() * 9 + 1) * 100000);
			//动态码短信内容
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("code",dynamicCode);

			String smsCode = "";

			//收验证码1分钟之内只能发送一次
			if(redisUtils.get(mobile) != null){
				result.put("state_code", 2);
				result.put("msg", "发送验证码过于频繁请稍后试！");
				return result;
			}
			// 1如果是登录
			if (key.equals(SmsTypeKeyEnum.LOGIN.toString())) {

				// 校验手机是否注册过
				if (!validMobileIsRegister(mobile)) {
					result.put("state_code", 2);
					result.put("msg", "当前手机号没有绑定相关帐号");
					return result;
				}
				smsCode = SmsTypeKeyEnum.LOGIN.getCode();

			// 2如果是注册
			} else if (key.equals(SmsTypeKeyEnum.REGISTER.toString())) {

				// 校验手机是否注册过
				if (validMobileIsRegister(mobile)) {
					result.put("state_code", 2);
					result.put("msg", "当前输入手机号码已绑定有帐号，可直接登录");
					return result;
				}

				smsCode = SmsTypeKeyEnum.REGISTER.getCode();

				// 3如果是找回密码
			} else if (key.equals(SmsTypeKeyEnum.BACKPASSWORD.toString())) {
				// 校验手机是否注册过
				if (!validMobileIsRegister(mobile)) {
					result.put("state_code", 2);
					result.put("msg", "当前手机号没有绑定相关帐号");
					return result;
				}
				smsCode = SmsTypeKeyEnum.BACKPASSWORD.getCode();

				// 4是绑定帐号
			} else if (key.equals(SmsTypeKeyEnum.BINDING.toString())) {
				// 校验手机是否注册过
				if (validMobileIsRegister(mobile)) {
					result.put("state_code", 2);
					result.put("msg", "当前输入手机号码已绑定有帐号，请解绑后再绑定");
					return result;
				}
				smsCode = SmsTypeKeyEnum.BINDING.getCode();

			// 5是修改密码
			} else if (key.equals(SmsTypeKeyEnum.UPDATE_PASSWORD.toString())) {
				// 校验手机是否注册过
				if (!validMobileIsRegister(mobile)) {
					result.put("state_code", 2);
					result.put("msg", "没有找到该手机号绑定的账户");
					return result;
				}
				smsCode = SmsTypeKeyEnum.UPDATE_PASSWORD.getCode();

				// 6是普通校验
			} else if (key.equals(SmsTypeKeyEnum.CHECK.toString())) {

				// 如果需要验证用户是否注册
				if (isCheckRegister == 1) {
					// 校验手机是否注册过
					if (!validMobileIsRegister(mobile)) {
						result.put("state_code", 2);
						result.put("msg", "没有找到该手机号绑定的账户");
						return result;
					}
				}
				smsCode = SmsTypeKeyEnum.CHECK.getCode();


			}


			// redist 中的格式是  前缀+key+手机号  例子:  es_sms_login_13123456789
			String smsCodeKey = SMS_CODE_PREFIX + key + mobile;

			redisUtils.set(smsCodeKey,dynamicCode,60 * 10);
			redisUtils.set(mobile,"sending",60);
			LOGGER.info("已发送短信:内容:" + key + " code :" +dynamicCode +",手机号:" + mobile );
			String code = AliyunDayuSmsUitl.sendSms(mobile,"潘多拉科技", smsCode, jsonObject.toJSONString());
			if (!code.equals("OK")) {
				throw new RuntimeException("发送失败");
			}
			result.put("state_code", 1);
			result.put("msg", "发送成功");
		} catch(RuntimeException e) {

			result.put("state_code", 0);
			result.put("msg", "发送失败,短信系统出现异常");
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 验证手机验证码是否正确
	 * @param validCode 验证码
	 * @param mobile 手机号
	 * @param key key 类型key枚举 {@link SmsTypeKeyEnum}
	 * @exception RuntimeException 手机号格式错误出错
	 * @return
	 */
	public boolean validSmsCode(String validCode, String mobile, String key) {


		// 如果手机号格式不对
		if ( !Validator.isMobile(mobile) ) {
			throw new RuntimeException("手机号码格式错误");
		}


		//防止 空值
		if (key == null || "".equals(key)) {

			// 默认为登录
			key = SmsTypeKeyEnum.LOGIN.toString();
		}

		// 如果验证码为空
		if (validCode == null || "".equals(validCode)) {
			return false;
		}
		String code = (String) redisUtils.get(SMS_CODE_PREFIX + key + mobile);

		// 验证码为空
		if (code == null) {
			return false;
		} else {
			// 忽略大小写 判断  不正确
			if (!code.equalsIgnoreCase(validCode)) {
				return false;
			}
		}
		try{
			redisUtils.remove(SMS_CODE_PREFIX + key + mobile);
			redisUtils.remove(mobile);
		}catch (Exception e){
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * 验证手机号有没有注册
	 * @param mobile 手机号
	 * @exception RuntimeException 手机号格式错误出错
	 * @return boolean false=没有注册 true=注册了
	 */
	public boolean validMobileIsRegister(String mobile){
		boolean isExists = memberService.checkMobile(mobile) != 0;
		return isExists;
	}

	/**
	 * 验证是否可以发送信息(做倒计时判断，同一种类型加以校验)
	 * @param key 类型key枚举 {@link SmsTypeKeyEnum}
	 * @return true=允许发送 false=不允许
	 */
	private boolean validIsCanSendSms(String key){

		String code = (String) redisUtils.get(key);
		if(code != null){
			return false;
		}
		return true;
	}

	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] byteDigest = md.digest();
			int i;
			//字符数组转换成字符串
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < byteDigest.length; offset++) {
				i = byteDigest[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// 32位加密
			return buf.toString().toUpperCase();
			// 16位的加密
			//return buf.toString().substring(8, 24).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}


}
