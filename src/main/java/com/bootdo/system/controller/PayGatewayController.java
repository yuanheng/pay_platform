package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.PayGatewayDO;
import com.bootdo.system.service.PayGatewayService;
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
@RequestMapping("/system/payGateway")
public class PayGatewayController {
	@Autowired
	private PayGatewayService payGatewayService;
	
	@GetMapping()
	String PayGateway(){
	    return "system/payGateway/payGateway";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PayGatewayDO> payGatewayList = payGatewayService.list(query);
		int total = payGatewayService.count(query);
		PageUtils pageUtils = new PageUtils(payGatewayList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "system/payGateway/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Integer id,Model model){
		PayGatewayDO payGateway = payGatewayService.get(id);
		model.addAttribute("payGateway", payGateway);
	    return "system/payGateway/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( PayGatewayDO payGateway){
		if(payGatewayService.save(payGateway)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( PayGatewayDO payGateway){
		payGatewayService.update(payGateway);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Integer id){
		if(payGatewayService.remove(id)>0){
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
		payGatewayService.batchRemove(ids);
		return R.ok();
	}
	
}
