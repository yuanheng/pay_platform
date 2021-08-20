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

import com.bootdo.system.domain.MemberBankDO;
import com.bootdo.system.service.MemberBankService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-11-23 21:54:22
 */
 
@Controller
@RequestMapping("/system/memberBank")
public class MemberBankController {
	@Autowired
	private MemberBankService memberBankService;
	
	@GetMapping()
	@RequiresPermissions("system:memberBank:memberBank")
	String MemberBank(){
	    return "system/memberBank/memberBank";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:memberBank:memberBank")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MemberBankDO> memberBankList = memberBankService.list(query);
		int total = memberBankService.count(query);
		PageUtils pageUtils = new PageUtils(memberBankList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:memberBank:add")
	String add(){
	    return "system/memberBank/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:memberBank:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		MemberBankDO memberBank = memberBankService.get(id);
		model.addAttribute("memberBank", memberBank);
	    return "system/memberBank/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:memberBank:add")
	public R save( MemberBankDO memberBank){
		if(memberBankService.save(memberBank)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:memberBank:edit")
	public R update( MemberBankDO memberBank){
		memberBankService.update(memberBank);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:memberBank:remove")
	public R remove( Integer id){
		if(memberBankService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:memberBank:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		memberBankService.batchRemove(ids);
		return R.ok();
	}
	
}
