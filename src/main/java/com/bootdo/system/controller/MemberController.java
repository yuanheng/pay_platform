package com.bootdo.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.bootdo.app.config.Constants;
import com.bootdo.app.model.PayNotifyResult;
import com.bootdo.app.service.impl.AuthManager;
import com.bootdo.app.util.ShareCodeUtil;
import com.bootdo.common.service.JobService;
import com.bootdo.system.domain.TradeLogDO;
import com.bootdo.system.service.AgreementService;
import com.bootdo.system.service.TradeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.MemberDO;
import com.bootdo.system.service.MemberService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 会员表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-20 15:19:53
 */
 
@Controller
@RequestMapping("/system/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private TradeLogService tradeLogService;
	@Autowired
	private AuthManager authManager;
	@Autowired
	private AgreementService agreementService;
	@Autowired
	private JobService taskScheduleJobService;

	private AtomicInteger atomicInteger = new AtomicInteger(4000);

	
	@GetMapping()
	String Member(){
	    return "system/member/member";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MemberDO> memberList = memberService.list(query);
		if(memberList != null && memberList.size() > 0){
			//获取会员的总充值和提现的总额度
			for(MemberDO memberDO : memberList){
				String totalIncome = memberService.getTotalIncome(memberDO.getId());
				memberDO.setTotalIncome(totalIncome);
				String totalOutcome = memberService.getTotalOutcome(memberDO.getId());
				memberDO.setTotalOutcome(totalOutcome);
			}
		}
		int total = memberService.count(query);
		PageUtils pageUtils = new PageUtils(memberList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "system/member/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Integer id,Model model){
		MemberDO member = memberService.get(id);
		model.addAttribute("member", member);
	    return "system/member/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( MemberDO member){
		if(memberService.save(member)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( MemberDO member){
		memberService.update(member);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Integer id){
		if(memberService.remove(id)>0){
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
		memberService.batchRemove(ids);
		return R.ok();
	}



	@ResponseBody
	@GetMapping("/initUser")
	public R initUser() {
		//1.初始化50个一级会员
		// 2.每个一级会员发展 50个
		// 3.每个在发展10个

		for(String mobile : Constants.masterUsers){
			String code = ShareCodeUtil.toSerialCode(Long.parseLong(mobile));
			//初始化根用户
			memberService.register(mobile,"123456",null);
		}

		return R.ok();


	}



	@ResponseBody
	@GetMapping("/addMoeny")
	public R addMoney() {

		Map<String,Object> params = new HashMap<>();

		List<MemberDO> memberDOs = memberService.list(params);
		for(MemberDO memberDO : memberDOs){
			//1.生成交易记录
			TradeLogDO tradeLogDO = tradeLogService.recharge(memberDO,"30000");
			PayNotifyResult payNotifyResult = new PayNotifyResult();
			payNotifyResult.setAmount(tradeLogDO.getAmount());
			payNotifyResult.setFactAmount(tradeLogDO.getAmount());
			payNotifyResult.setTradeNo(tradeLogDO.getTradeNo());
			payNotifyResult.setTradeOutNo("测试上分");
			MemberDO result = tradeLogService.paySucess(payNotifyResult);
			System.out.println("*************"+result);
		}


		return R.ok();
	}

	@ResponseBody
	@GetMapping("/buy")
	public R buy() {

		Map<String,Object> params = new HashMap<>();

		List<MemberDO> memberDOs = memberService.list(params);
		for(MemberDO memberDO : memberDOs){
			//1.生成交易记录
			TradeLogDO tradeLogDO = tradeLogService.recharge(memberDO,"20000");
			PayNotifyResult payNotifyResult = new PayNotifyResult();
			payNotifyResult.setAmount(tradeLogDO.getAmount());
			payNotifyResult.setFactAmount(tradeLogDO.getAmount());
			payNotifyResult.setTradeNo(tradeLogDO.getTradeNo());
			payNotifyResult.setTradeOutNo("测试上分");
			MemberDO result = tradeLogService.paySucess(payNotifyResult);
			System.out.println("*************"+result);
		}

		return R.ok();

	}


	@ResponseBody
	@GetMapping("/openbank")
	public R openbank() {

		Map<String,Object> params = new HashMap<>();

		List<MemberDO> memberDOs = memberService.list(params);
		for(MemberDO memberDO : memberDOs){
			memberDO.setReallyName("张港风");
			memberDO.setCardNo("24854385945435345345");
			memberDO.setBankNo("122512312386754");
			memberDO.setBankAddress("南京市");
			memberDO.setBankName("招商银行");
			memberDO.setBankBranchName("北京支行");
			memberService.update(memberDO);
		}

		return R.ok();
	}

}
