package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.system.domain.InvestMoneyDO;
import com.bootdo.system.service.InvestMoneyService;
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
@RequestMapping("/system/investMoney")
public class InvestMoneyController {
	@Autowired
	private InvestMoneyService investMoneyService;
	@Autowired
	private MemberService memberService;

	
	@GetMapping()
	String InvestMoney(){
	    return "system/investMoney/investMoney";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<InvestMoneyDO> investMoneyList = investMoneyService.list(query);
		int total = investMoneyService.count(query);
		if(investMoneyList != null && investMoneyList.size() > 0){
			for(InvestMoneyDO investMoneyDO : investMoneyList){
				MemberDO memberDO = memberService.get(investMoneyDO.getMid());
				if(memberDO != null){
					investMoneyDO.setUsername(memberDO.getReallyName());
				}
			}
		}
		PageUtils pageUtils = new PageUtils(investMoneyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "system/investMoney/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Integer id,Model model){
		InvestMoneyDO investMoney = investMoneyService.get(id);
		model.addAttribute("investMoney", investMoney);
	    return "system/investMoney/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( InvestMoneyDO investMoney){
		if(investMoneyService.save(investMoney)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( InvestMoneyDO investMoney){
		investMoneyService.update(investMoney);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Integer id){
		if(investMoneyService.remove(id)>0){
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
		investMoneyService.batchRemove(ids);
		return R.ok();
	}
	
}
