package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.PayAlipayInfoDO;
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

import com.bootdo.system.domain.PayWechatInfoDO;
import com.bootdo.system.service.PayWechatInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-08-20 21:24:35
 */

@Controller
@RequestMapping("/system/payWechatInfo")
public class PayWechatInfoController {
    @Autowired
    private PayWechatInfoService payWechatInfoService;

    @GetMapping()
    @RequiresPermissions("system:payWechatInfo:payWechatInfo")
    String PayWechatInfo() {
        return "system/payWechatInfo/payWechatInfo";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:payWechatInfo:payWechatInfo")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<PayWechatInfoDO> payWechatInfoList = payWechatInfoService.list(query);
        int total = payWechatInfoService.count(query);
        PageUtils pageUtils = new PageUtils(payWechatInfoList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("system:payWechatInfo:add")
    String add() {
        return "system/payWechatInfo/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:payWechatInfo:edit")
    String edit(@PathVariable("id") Integer id, Model model) {
        PayWechatInfoDO payWechatInfo = payWechatInfoService.get(id);
        model.addAttribute("payWechatInfo", payWechatInfo);
        return "system/payWechatInfo/edit";
    }

    /**
     * 启用|禁用
     */
    @PostMapping("/changeStatus")
    @ResponseBody
    @RequiresPermissions("system:payWechatInfo:edit")
    public R changeStatus(Integer id, String flag) {
        PayWechatInfoDO payWechatInfo = payWechatInfoService.get(id);
        payWechatInfo.setStatus(flag);
        payWechatInfoService.update(payWechatInfo);
        return R.ok();
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:payWechatInfo:add")
    public R save(PayWechatInfoDO payWechatInfo) {
        payWechatInfo.setMid(ShiroUtils.getUserId());
        if (payWechatInfoService.save(payWechatInfo) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:payWechatInfo:edit")
    public R update(PayWechatInfoDO payWechatInfo) {
        payWechatInfoService.update(payWechatInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:payWechatInfo:remove")
    public R remove(Integer id) {
        if (payWechatInfoService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("system:payWechatInfo:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        payWechatInfoService.batchRemove(ids);
        return R.ok();
    }

}
