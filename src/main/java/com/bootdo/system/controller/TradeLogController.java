package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.app.model.PayNotifyResult;
import com.bootdo.app.service.impl.AuthManager;
import com.bootdo.app.task.RepaymentScheduler;
import com.bootdo.app.zwlenum.PayStatusEnum;
import com.bootdo.system.domain.MemberDO;
import com.bootdo.system.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.TradeLogDO;
import com.bootdo.system.service.TradeLogService;
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
@RequestMapping("/system/tradeLog")
public class TradeLogController {
	@Autowired
	private TradeLogService tradeLogService;
	@Autowired
	private AuthManager authManager;
	@Autowired
	private RepaymentScheduler repaymentScheduler;


	
	@GetMapping()
	String TradeLog(){
	    return "system/tradeLog/tradeLog";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TradeLogDO> tradeLogList = tradeLogService.list(query);

		int total = tradeLogService.count(query);

		PageUtils pageUtils = new PageUtils(tradeLogList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "system/tradeLog/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Integer id,Model model){
		TradeLogDO tradeLog = tradeLogService.get(id);
		model.addAttribute("tradeLog", tradeLog);
	    return "system/tradeLog/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( TradeLogDO tradeLog){
		if(tradeLogService.save(tradeLog)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( TradeLogDO tradeLog){
		tradeLogService.update(tradeLog);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Integer id){
		if(tradeLogService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}



	/**
	 * 上分
	 */
	@PostMapping( "/addMoney")
	@ResponseBody
	public R addMoney(Integer id){
		TradeLogDO tradeLogDO = tradeLogService.get(id);

		if(tradeLogDO.getStatus().equals(PayStatusEnum.PREPAY.toString())){
			PayNotifyResult payNotifyResult = new PayNotifyResult();
			payNotifyResult.setAmount(tradeLogDO.getAmount());
			payNotifyResult.setFactAmount(tradeLogDO.getAmount());
			payNotifyResult.setTradeNo(tradeLogDO.getTradeNo());
			payNotifyResult.setTradeOutNo("客服手动上分");
			MemberDO memberDO = tradeLogService.paySucess(payNotifyResult);
			authManager.refeshMemberInfo(memberDO);
			//删除paymid

			return R.ok("上分成功");
		}



		return R.error("操作错误");
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Integer[] ids){
		tradeLogService.batchRemove(ids);
		return R.ok();
	}



	/**
	 * 购买
	 */
	@PostMapping("/purchase")
	@ResponseBody
	public R purchase(){
		repaymentScheduler.purchase();
		return R.ok("操作成功");
	}


	/**
	 * 返现单独交易
	 */
	@PostMapping("/purchaseBack")
	@ResponseBody
	public R purchaseBack(Integer id){
		TradeLogDO tradeLogDO = tradeLogService.get(id);
		repaymentScheduler.handlerPurchaseBack(tradeLogDO.getMid(),tradeLogDO.getId());
		return R.ok("操作成功");
	}

	/**
	 * 返现单独交易
	 */
	@PostMapping("/purchaseBackAll")
	@ResponseBody
	public R purchaseBackAll(){
		repaymentScheduler.purchaseBack();
		return R.ok("操作成功");
	}
	
}
