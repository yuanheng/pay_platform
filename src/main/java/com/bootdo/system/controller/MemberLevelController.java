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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.MemberLevelDO;
import com.bootdo.system.service.MemberLevelService;
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
@RequestMapping("/system/memberLevel")
public class MemberLevelController {
	@Autowired
	private MemberLevelService memberLevelService;
	
	@GetMapping()
	String MemberLevel(){
	    return "system/memberLevel/memberLevel";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MemberLevelDO> memberLevelList = memberLevelService.list(query);
		int total = memberLevelService.count(query);
		PageUtils pageUtils = new PageUtils(memberLevelList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "system/memberLevel/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Integer id,Model model){
		MemberLevelDO memberLevel = memberLevelService.get(id);
		model.addAttribute("memberLevel", memberLevel);
	    return "system/memberLevel/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( MemberLevelDO memberLevel){
		if(memberLevelService.save(memberLevel)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( MemberLevelDO memberLevel){
		memberLevelService.update(memberLevel);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Integer id){
		if(memberLevelService.remove(id)>0){
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
		memberLevelService.batchRemove(ids);
		return R.ok();
	}
	
}
