package com.bootdo.system.controller;

import com.bootdo.app.zwlenum.PayTypeEnum;
import com.bootdo.app.zwlenum.StatusEnum;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.adptor.AccountStatusSynchronizer;
import com.bootdo.system.domain.BankInfoDO;
import com.bootdo.system.service.BankInfoService;
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

import java.util.List;
import java.util.Map;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-08-20 21:24:35
 */

@Controller
@RequestMapping("/system/bankInfo")
public class BankInfoController {
    private BankInfoService bankInfoService;
    private AccountStatusSynchronizer statusSynchronizer;

    @Autowired
    public BankInfoController(BankInfoService bankInfoService, AccountStatusSynchronizer statusSynchronizer) {
        this.bankInfoService = bankInfoService;
        this.statusSynchronizer = statusSynchronizer;
    }

    @GetMapping()
    @RequiresPermissions("system:bankInfo:bankInfo")
    String BankInfo() {
        return "system/bankInfo/bankInfo";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:bankInfo:bankInfo")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<BankInfoDO> bankInfoList = bankInfoService.list(query);
        int total = bankInfoService.count(query);
        PageUtils pageUtils = new PageUtils(bankInfoList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("system:bankInfo:add")
    String add() {
        return "system/bankInfo/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:bankInfo:edit")
    String edit(@PathVariable("id") Integer id, Model model) {
        BankInfoDO bankInfo = bankInfoService.get(id);
        model.addAttribute("bankInfo", bankInfo);
        return "system/bankInfo/edit";
    }

    /**
     * 启用|禁用
     */
    @PostMapping("/changeStatus")
    @ResponseBody
    @RequiresPermissions("system:bankInfo:edit")
    public R changeStatus(Integer id, String flag) {
        BankInfoDO bankInfo = bankInfoService.get(id);
        bankInfo.setStatus(flag);
        bankInfoService.update(bankInfo);
        if (flag.equals(StatusEnum.ENABLE.getKey())) {
            statusSynchronizer.sync(PayTypeEnum.BANK_CODE, bankInfo.getId(), bankInfo);
        }
        if (flag.equals(StatusEnum.DISABLE.getKey())) {
            statusSynchronizer.remove(PayTypeEnum.BANK_CODE, bankInfo.getId());
        }
        return R.ok();
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:bankInfo:add")
    public R save(BankInfoDO bankInfo) {
        bankInfo.setMid(ShiroUtils.getUserId());
        if (bankInfoService.save(bankInfo) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:bankInfo:edit")
    public R update(BankInfoDO bankInfo) {
        bankInfoService.update(bankInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:bankInfo:remove")
    public R remove(Integer id) {
        if (bankInfoService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("system:bankInfo:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        bankInfoService.batchRemove(ids);
        return R.ok();
    }

}
