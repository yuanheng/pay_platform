package com.bootdo.system.controller;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.bootdo.app.config.Constants;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.UserDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	@Autowired
	private RedisUtils redisUtils;
	
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
			redisUtils.set(Constants.getMerchantNoKey(merchant.getMerchantNo()),merchant);
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
		MerchantDO merchantDO = merchantService.get(merchant.getId().intValue());
		redisUtils.set(Constants.getMerchantNoKey(merchant.getMerchantNo()),merchantDO);
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



	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
	public R updateInfo(String secretKey, String callbackUrl){
		UserDO userDO = ShiroUtils.getUser();
		MerchantDO merchant = merchantService.getByMid(userDO.getUserId());
		merchant.setCallbackUrl(callbackUrl);
		merchant.setSecretKey(secretKey);
		merchantService.update(merchant);
		String merchantKey = Constants.getMerchantNoKey(merchant.getMerchantNo());
		redisUtils.set(merchantKey,merchant);
		return R.ok();
	}
}
