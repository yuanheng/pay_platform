package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.BankInfoDO;
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

import com.bootdo.system.domain.PayAlipayInfoDO;
import com.bootdo.system.service.PayAlipayInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-08-20 21:24:35
 */

@Controller
@RequestMapping("/system/payAlipayInfo")
public class PayAlipayInfoController {
    @Autowired
    private PayAlipayInfoService payAlipayInfoService;

    @GetMapping()
    @RequiresPermissions("system:payAlipayInfo:payAlipayInfo")
    String PayAlipayInfo() {
        return "system/payAlipayInfo/payAlipayInfo";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:payAlipayInfo:payAlipayInfo")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<PayAlipayInfoDO> payAlipayInfoList = payAlipayInfoService.list(query);
        int total = payAlipayInfoService.count(query);
        PageUtils pageUtils = new PageUtils(payAlipayInfoList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("system:payAlipayInfo:add")
    String add() {
        return "system/payAlipayInfo/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:payAlipayInfo:edit")
    String edit(@PathVariable("id") Integer id, Model model) {
        PayAlipayInfoDO payAlipayInfo = payAlipayInfoService.get(id);
        model.addAttribute("payAlipayInfo", payAlipayInfo);
        return "system/payAlipayInfo/edit";
    }

    /**
     * 启用|禁用
     */
    @PostMapping("/changeStatus")
    @ResponseBody
    @RequiresPermissions("system:payAlipayInfo:edit")
    public R changeStatus(Integer id, String flag) {
        PayAlipayInfoDO payAlipayInfo = payAlipayInfoService.get(id);
        payAlipayInfo.setStatus(flag);
        payAlipayInfoService.update(payAlipayInfo);
        return R.ok();
    }
    
    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:payAlipayInfo:add")
    public R save(PayAlipayInfoDO payAlipayInfo) {
        payAlipayInfo.setMid(ShiroUtils.getUserId());
        if (payAlipayInfoService.save(payAlipayInfo) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:payAlipayInfo:edit")
    public R update(PayAlipayInfoDO payAlipayInfo) {
        payAlipayInfoService.update(payAlipayInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:payAlipayInfo:remove")
    public R remove(Integer id) {
        if (payAlipayInfoService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("system:payAlipayInfo:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        payAlipayInfoService.batchRemove(ids);
        return R.ok();
    }

}
