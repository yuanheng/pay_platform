package com.bootdo.system.controller;

import com.bootdo.app.config.Constants;
import com.bootdo.app.util.AmountUtil;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.app.zwlenum.StatusEnum;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.TbOrderDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.dto.TbCodeStatusDTO;
import com.bootdo.system.service.TbOrderService;
import com.google.common.collect.Lists;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-09-08 22:32:27
 */
@Controller
@RequestMapping("/system/tbOrder")
public class TbOrderController {
	private TbOrderService tbOrderService;
	private RedisUtils redisUtils;

	@Autowired
    public void setTbOrderService(TbOrderService tbOrderService) {
        this.tbOrderService = tbOrderService;
    }

    @Autowired
    public void setRedisUtils(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @GetMapping()
	@RequiresPermissions("system:tbOrder:tbOrder")
	String TbOrder(Model model){
        model.addAttribute("codeStatusInfo", loadCodeStatusFromCache());
        return "system/tbOrder/tbOrder";
	}

	private List<TbCodeStatusDTO> loadCodeStatusFromCache() {
		List<TbCodeStatusDTO> list = Lists.newLinkedList();
		final String gnrMatchExp = String.format("%s*", Constants.TB_PAYINFO_PREFIX);
		final Set keys = redisUtils.keys(gnrMatchExp);
		if (keys != null && !keys.isEmpty()) {
			keys.forEach(e -> {
				long count = redisUtils.lGetListSize(String.valueOf(e));
				final String _k = String.valueOf(e);
				final String amount = _k.substring(_k.lastIndexOf("_") + 1, _k.length());
				list.add(new TbCodeStatusDTO(AmountUtil.changeF2Y(amount),
						StatusEnum.ENABLE.getTypeDesc(), String.valueOf(count)));
			});
		}
		if (list != null && !list.isEmpty()) {
			try {
				list.sort(Comparator.comparing(e -> Double.parseDouble(e.getAmount())));
			} catch (Exception e) {
				System.out.println("数值转化异常，影响显示排序，不影响使用");
			}
		}
		return list;
	}

	@ResponseBody
	@PostMapping("/refreshCodeStatus")
	@RequiresPermissions("system:tbOrder:tbOrder")
	public List<TbCodeStatusDTO> refreshCodeStatus(){
		return loadCodeStatusFromCache();
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
	@PostMapping( "/batchEnable")
	@ResponseBody
	@RequiresPermissions("system:tbOrder:edit")
	public R batchEnable(@RequestParam("ids[]") Integer[] ids){
		for (Integer id : ids) {
			TbOrderDO tbOrderDO = tbOrderService.get(id);
			if (tbOrderDO.getStatus().equals(StatusEnum.FINISHED.getKey()) || tbOrderDO.getStatus().equals(StatusEnum.LOSE.getKey())) {
				continue;
			}
			String key = Constants.getPayTbOrderKey(tbOrderDO.getMid() + "", tbOrderDO.getId() + "");
			String amount = tbOrderDO.getAmount();
			Integer tempAmount = Integer.parseInt(amount) * 100;
			String tbPayinfosKey = Constants.getTbPayinfoKey(tempAmount + "");
			if (tbOrderDO.getStatus().equals(StatusEnum.DISABLE.getKey())) {
				tbOrderDO.setStatus(StatusEnum.ENABLE.getKey());
				if (notExist(tbPayinfosKey, tbOrderDO.getId())) {
					redisUtils.addPaymentInfo(tbPayinfosKey, tbOrderDO);
				}
			} else {
				tbOrderDO.setStatus(StatusEnum.DISABLE.getKey());
			}
			redisUtils.set(key, tbOrderDO);
			tbOrderService.update(tbOrderDO);
		}

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
        if (tbOrderDO.getStatus().equals(StatusEnum.FINISHED.getKey()) || tbOrderDO.getStatus().equals(StatusEnum.LOSE.getKey())) {
					return R.error("订单已经被支付或者过期，不能启用。");
				}

        String key = Constants.getPayTbOrderKey(tbOrderDO.getMid() + "", tbOrderDO.getId() + "");
        String amount = tbOrderDO.getAmount();
        Integer tempAmount = Integer.parseInt(amount) * 100;
        String tbPayinfosKey = Constants.getTbPayinfoKey(tempAmount + "");
        if (tbOrderDO.getStatus().equals(StatusEnum.DISABLE.getKey())) {
					  tbOrderDO.setStatus(StatusEnum.ENABLE.getKey());
            if (notExist(tbPayinfosKey, tbOrderDO.getId())) {
                redisUtils.addPaymentInfo(tbPayinfosKey, tbOrderDO);
            } else {
                return R.error("已存在，请勿重复添加。");
            }
        } else {
					tbOrderDO.setStatus(StatusEnum.DISABLE.getKey());
				}
        redisUtils.set(key, tbOrderDO);
        tbOrderService.update(tbOrderDO);
        return R.ok();
	}

    private boolean notExist(String key, Integer nid) {
        long size = redisUtils.lGetListSize(key);
        if (size == 0) {
            return true;
        }
        List<Object> contentList = redisUtils.lGet(key, 0, size - 1);
        List<TbOrderDO> existenceList = new ArrayList<>(contentList.size());
        contentList.forEach(e -> {
            TbOrderDO o = (TbOrderDO) e;
            existenceList.add(o);
        });
        if (existenceList != null && !existenceList.isEmpty()) {
            final Set<Integer> idSet = existenceList.stream().filter(e -> e.getId() != null)
                    .map(TbOrderDO::getId).collect(Collectors.toSet());
            if (idSet.contains(nid)) {
                return false;
            }
            return true;
        }
        return true;
    }

}
