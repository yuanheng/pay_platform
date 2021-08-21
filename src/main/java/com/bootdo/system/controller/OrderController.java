package com.bootdo.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.MerchantDO;
import com.bootdo.system.domain.RoleDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.MerchantService;
import com.bootdo.system.service.RoleService;
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

import com.bootdo.system.domain.OrderDO;
import com.bootdo.system.service.OrderService;
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
@RequestMapping("/system/order")
public class OrderController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private MerchantService merchantService;

	@GetMapping()
	@RequiresPermissions("system:order:order")
	String Order(){
	    return "system/order/order";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:order:order")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//获取当前用户的角色
		UserDO userDO = ShiroUtils.getUser();
		List<RoleDO> roles = roleService.list(userDO.getUserId());
		//暂时通过id 来判断 是否是管理员，商户，码商
		for (RoleDO role : roles) {
			params.put("roleId", role.getRoleId());
		}
		//查询列表数据
		Query query = new Query(params);
		List<OrderDO> orderList = orderService.list(query);
		int total = orderService.count(query);
		PageUtils pageUtils = new PageUtils(orderList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:order:add")
	String add(){
	    return "system/order/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:order:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OrderDO order = orderService.get(id);
		model.addAttribute("order", order);
	    return "system/order/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:order:add")
	public R save( OrderDO order){
		if(orderService.save(order)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:order:edit")
	public R update( OrderDO order){
		orderService.update(order);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:order:remove")
	public R remove( Integer id){
		if(orderService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:order:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		orderService.batchRemove(ids);
		return R.ok();
	}
	
}
