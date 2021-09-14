package com.bootdo.system.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.bootdo.app.config.Constants;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.app.zwlenum.StatusEnum;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.dto.TbCodeStatusDTO;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.TbOrderDO;
import com.bootdo.system.service.TbOrderService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 *
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-09-08 22:32:27
 */
@Slf4j
@Controller
@RequestMapping("/system/tbOrder")
public class TbOrderController {
	@Autowired
	private TbOrderService tbOrderService;
	@Autowired
	private RedisUtils redisUtils;

	@GetMapping()
	@RequiresPermissions("system:tbOrder:tbOrder")
	String TbOrder(Model model){
		final List<TbCodeStatusDTO> statusCountList = tbOrderService.statCodeStatus();
		Map<String, List<TbCodeStatusDTO>> statusMap = statusCountList.stream()
				.filter(e -> StringUtils.isNotEmpty(e.getStatus()))
				.collect(Collectors.groupingBy(TbCodeStatusDTO::getStatus));
		statusMap.entrySet().forEach(e -> e.getValue()
				.forEach(ex -> ex.setStatus(StatusEnum.getStatusEnumByKey(ex.getStatus()).getTypeDesc())));
		List<TbCodeStatusDTO> list = Lists.newLinkedList();
		for (Map.Entry<String, List<TbCodeStatusDTO>> entry : statusMap.entrySet()) {
			List<TbCodeStatusDTO> value = entry.getValue();
			if (!CollectionUtils.isEmpty(value)) {
				try {
					value.sort(Comparator.comparing(e -> Long.parseLong(e.getAmount())));
				} catch (Exception e) {
					log.error("数值转化异常，影响显示排序，不影响使用");
				}
				list.addAll(value);
			}
		}
		model.addAttribute("codeStatusInfo", list);
	    return "system/tbOrder/tbOrder";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:tbOrder:tbOrder")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TbOrderDO> tbOrderList = tbOrderService.list(query);
		int total = tbOrderService.count(query);
		PageUtils pageUtils = new PageUtils(tbOrderList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("system:tbOrder:add")
	String add(){
	    return "system/tbOrder/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:tbOrder:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TbOrderDO tbOrder = tbOrderService.get(id);
		model.addAttribute("tbOrder", tbOrder);
	    return "system/tbOrder/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:tbOrder:add")
	public R save( TbOrderDO tbOrder){
		tbOrder.setCreateTime(new Date());
		UserDO currentUser = ShiroUtils.getUser();
		tbOrder.setMid(currentUser.getUserId());
		tbOrder.setStatus(StatusEnum.DISABLE.getKey());
		if(tbOrderService.save(tbOrder)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:tbOrder:edit")
	public R update( TbOrderDO tbOrder){
		tbOrderService.update(tbOrder);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:tbOrder:remove")
	public R remove( Integer id){
		if(tbOrderService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:tbOrder:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		tbOrderService.batchRemove(ids);
		return R.ok();
	}




	/**
	 * 启用禁用
	 */
	@PostMapping( "/changeStatus")
	@ResponseBody
	@RequiresPermissions("system:tbOrder:edit")
	public R changStatus(Integer id, String status){
		TbOrderDO tbOrderDO = tbOrderService.get(id);
		tbOrderDO.setStatus(StatusEnum.getStatusEnumByKey(status).getKey());
		tbOrderService.update(tbOrderDO);
		String key = Constants.getPayTbOrderKey(tbOrderDO.getMid()+"", tbOrderDO.getId()+"");
		redisUtils.set(key, tbOrderDO);
		String amount = tbOrderDO.getAmount();
		Integer tempAmount = Integer.parseInt(amount) * 100;
		String tbPayinfosKey = Constants.getTbPayinfoKey(tempAmount+"");
		if (tbOrderDO.getStatus().equals(StatusEnum.ENABLE.getKey())) {
			redisUtils.addPaymentInfo(tbPayinfosKey, tbOrderDO);
			return R.ok();
		}
		return R.ok();
	}

}
