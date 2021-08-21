package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.MerchantDO;
import com.bootdo.system.service.MerchantService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-08-20 21:24:35
 */
 
@Controller
@RequestMapping("/system/merchant")
public class MerchantController {
	@Autowired
	private MerchantService merchantService;
	
	@GetMapping()
	@RequiresPermissions("system:merchant:merchant")
	String Merchant(){
	    return "system/merchant/merchant";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:merchant:merchant")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MerchantDO> merchantList = merchantService.list(query);
		int total = merchantService.count(query);
		PageUtils pageUtils = new PageUtils(merchantList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:merchant:add")
	String add(){
	    return "system/merchant/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:merchant:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		MerchantDO merchant = merchantService.get(id);
		model.addAttribute("merchant", merchant);
	    return "system/merchant/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:merchant:add")
	public R save(MerchantDO merchant){
		if(merchantService.save(merchant)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:merchant:edit")
	public R update( MerchantDO merchant){
		merchantService.update(merchant);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:merchant:remove")
	public R remove( Integer id){
		if(merchantService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:merchant:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		merchantService.batchRemove(ids);
		return R.ok();
	}
	
}
