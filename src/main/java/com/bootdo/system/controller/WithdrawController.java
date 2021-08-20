package com.bootdo.system.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.bootdo.app.config.Constants;
import com.bootdo.app.qapple.enums.ReponseCode;
import com.bootdo.app.qapple.param.PreSellParam;
import com.bootdo.app.qapple.util.HttpClient;
import com.bootdo.app.qapple.util.RsaUtil;
import com.bootdo.app.qapple.vo.PreSellNewVo;
import com.bootdo.app.qapple.vo.ResponseData;
import com.bootdo.app.service.OtcPayService;
import com.bootdo.app.service.UsdtPayService;
import com.bootdo.app.util.AmountUtil;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.app.zwlenum.PayStatusEnum;
import com.bootdo.app.zwlenum.TradeTypeEnum;
import com.bootdo.system.domain.MemberDO;
import com.bootdo.system.domain.TradeLogDO;
import com.bootdo.system.service.MemberService;
import com.bootdo.system.service.TradeLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.WithdrawDO;
import com.bootdo.system.service.WithdrawService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-02 11:24:17
 */
 
@Controller
@RequestMapping("/system/withdraw")
public class WithdrawController {
	private static Logger logger = LoggerFactory.getLogger(WithdrawController.class);
	//商家账号名
	@Value("${MERCHANT_NAME}")
	private String MERCHANT_NAME;
	//商家用户merchant1私钥
	@Value("${MERCHANT_PRIVATE_KEY}")
	private String MERCHANT_PRIVATE_KEY;
	//用青苹果平台公钥
	@Value("${QAPPLE_PUBLIC_KEY}")
	private String QAPPLE_PUBLIC_KEY;
	//青苹果支付服务器地址
	@Value("${QAPPLE_SERVER_URL}")
	private  String QAPPLE_SERVER_URL;

	@Value("${sell_nofity_url}")
	private String notifyUrl;


	@Autowired
	private WithdrawService withdrawService;
	@Autowired
	private TradeLogService tradeLogService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private UsdtPayService usdtPayService;
	@Autowired
	private OtcPayService otcPayService;

	@Autowired
	private RedisUtils redisUtils;

	@GetMapping()
	String Withdraw(){
	    return "system/withdraw/withdraw";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<WithdrawDO> withdrawList = withdrawService.list(query);
		int total = withdrawService.count(query);
		PageUtils pageUtils = new PageUtils(withdrawList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "system/withdraw/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Integer id,Model model){
		WithdrawDO withdraw = withdrawService.get(id);
		model.addAttribute("withdraw", withdraw);
	    return "system/withdraw/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( WithdrawDO withdraw){
		if(withdrawService.save(withdraw)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( WithdrawDO withdraw){
		withdrawService.update(withdraw);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Integer id){
		if(withdrawService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}


	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Integer[] ids){
		withdrawService.batchRemove(ids);
		return R.ok();
	}


	/**
	 * 删除
	 */
	@PostMapping( "/auditTradeLogApple")
	@ResponseBody
	public R auditTradeLog(Integer id,String type){
		WithdrawDO withdrawDO = withdrawService.get(id);

		TradeLogDO tradeLogDO = tradeLogService.get(withdrawDO.getTradeId());



		if(tradeLogDO == null || !tradeLogDO.getType().equals(TradeTypeEnum.WITHDRAW.toString())){
			return R.error();
		}


		tradeLogDO.setFinishedTime(new Date());
		withdrawDO.setFinishedTime(new Date());
		if("Y".equals(type)){
			withdrawDO.setStatus(PayStatusEnum.FINISHED.toString());
		}else{

			withdrawDO.setStatus(PayStatusEnum.DISABLE.toString());
		}

		withdrawService.update(withdrawDO);

		if(!"Y".equals(type)){
			return R.error();
		}

		MemberDO memberDO = memberService.get(tradeLogDO.getMid());
		String username = memberDO.getUsername();
		String tempName = username.substring(4);

		try {
			/** 预下单测试地址  */
			String prePayUrl = QAPPLE_SERVER_URL + "/merchant/merchantcenter/fastSell/preSell";
			PreSellParam preSellParam = new PreSellParam();
			preSellParam.setAmountRmb(new BigDecimal(AmountUtil.changeF2Y(tradeLogDO.getFactAmount()))); //款金额
			preSellParam.setMerchantName(MERCHANT_NAME); // 商家账号
			preSellParam.setOutTradeNo(tradeLogDO.getTradeNo());   //订单号

			preSellParam.setNotifyUrl(notifyUrl);
			preSellParam.setMemberName(tempName);   //会员名称
			preSellParam.setCardType("BANK_CARD");     //收款卡类型
			preSellParam.setPayee(memberDO.getReallyName());        //银行卡账户类型
			preSellParam.setCardNo(memberDO.getBankNo());        //卡号
			preSellParam.setBankName(memberDO.getBankName());      //总行名称
			preSellParam.setBranchName(memberDO.getBankBranchName());    //分行名称
			preSellParam.setSignType("RSA");      //签名类型®
			preSellParam.setSign("");           //签名参数
			preSellParam.setSign(RsaUtil.sign(preSellParam.makeSignJoinStr(), MERCHANT_PRIVATE_KEY)); //签名
			//发起请求
			TypeReference<ResponseData<PreSellNewVo>> responseType = new TypeReference<ResponseData<PreSellNewVo>>() {};
			String result = HttpClient.doPost((JSONObject) JSON.toJSON(preSellParam), prePayUrl);
			ResponseData<PreSellNewVo> responseData = JSON.parseObject(result, responseType);

			//判断请求响应是否成功
			if (!responseData.getCode().equals(ReponseCode.SUCCESS.getStatus())) {
				logger.error(String.format("请求失败 错误码%s,错误信息%s", responseData.getCode(), responseData.getMessage()));
				logger.error(("调试信息：" + responseData.getMsgDebug() + "，跟踪号:" + responseData.getTransNo()));
				return R.error("出款失败！");
			}
			PreSellNewVo preSellNewVo = responseData.getData();
			//用青苹果平台公钥验签
			boolean checkSign = RsaUtil.doCheck(preSellNewVo.makeSignJoinStr(), preSellNewVo.getSign(), QAPPLE_PUBLIC_KEY);
			if (checkSign) {
				String tradeOutNo = preSellNewVo.getTradeNo();
				tradeLogDO.setTradeOutNo(tradeOutNo);
				tradeLogService.update(tradeLogDO);
				logger.info("验签成功！"+tradeLogDO.getTradeNo()+notifyUrl);
			} else {
				logger.error("验签失败！"+tradeLogDO.getTradeNo());
				return R.error("出款失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("出款失败！"+tradeLogDO.getTradeNo());
			//处理异常
			return R.error("出款失败！");
		}


		return R.ok();
	}





	/**
	 * 删除
	 */
	@PostMapping( "/auditTradeLog")
	@ResponseBody
	public R auditTradeLogUtsdb(Integer id,String type){
		WithdrawDO withdrawDO = withdrawService.get(id);

		TradeLogDO tradeLogDO = tradeLogService.get(withdrawDO.getTradeId());

		if(tradeLogDO == null || !tradeLogDO.getType().equals(TradeTypeEnum.WITHDRAW.toString())){
			return R.error();
		}


		tradeLogDO.setFinishedTime(new Date());
		withdrawDO.setFinishedTime(new Date());
		if("Y".equals(type)){
			withdrawDO.setStatus(PayStatusEnum.FINISHED.toString());
		}else{

			withdrawDO.setStatus(PayStatusEnum.DISABLE.toString());
		}

		withdrawService.update(withdrawDO);

		if(!"Y".equals(type)){
			return R.error();
		}

		MemberDO memberDO = memberService.get(tradeLogDO.getMid());
		String result="";
		try {
			String payType = (String) redisUtils.get(Constants.APP_PAY_TYPE);
			if(payType.equals("OTC_PAY")){
				result = otcPayService.withdraw(memberDO,tradeLogDO);
			}
			if(payType.equals("U_PAY")){
				result = "0000";
			}

			if(!result.contains("0000")){
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("出款失败！"+tradeLogDO.getTradeNo() + "msg:"+result);
			//处理异常
			return R.error("出款失败！"+result);
		}


		return R.ok();
	}













	
}
