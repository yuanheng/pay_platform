package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.app.task.RepaymentScheduler;
import com.bootdo.system.domain.TradeLogDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.AgreementDO;
import com.bootdo.system.service.AgreementService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-08 16:50:14
 */
 
@Controller
@RequestMapping("/system/agreement")
public class AgreementController {

	private static Logger logger = LoggerFactory.getLogger(AgreementController.class);

	@Autowired
	private AgreementService agreementService;
	@Autowired
	private RepaymentScheduler repaymentScheduler;
	
	@GetMapping()
	String Agreement(){
	    return "system/agreement/agreement";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AgreementDO> agreementList = agreementService.list(query);
		int total = agreementService.count(query);
		PageUtils pageUtils = new PageUtils(agreementList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "system/agreement/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Integer id,Model model){
		AgreementDO agreement = agreementService.get(id);
		model.addAttribute("agreement", agreement);
	    return "system/agreement/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( AgreementDO agreement){
		if(agreementService.save(agreement)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( AgreementDO agreement){
		agreementService.update(agreement);
		return R.ok();
	}
	
	/**
	 * 返现
	 */
	@PostMapping( "/agreementBack")
	@ResponseBody
	public R agreementBack(Integer id){

		AgreementDO agreementDO = agreementService.get(id);
		if(agreementDO == null){
			return R.error("无效id");
		}
		repaymentScheduler.handlerAgreementBack(agreementDO);
		return R.ok();
	}


	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Integer[] ids){
		agreementService.batchRemove(ids);
		return R.ok();
	}


	
}
