package com.bootdo.system.controller;

import com.bootdo.app.config.Constants;
import com.bootdo.app.model.StatisticsInfo;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.app.zwlenum.PayTypeEnum;
import com.bootdo.app.zwlenum.RoleTypeEnum;
import com.bootdo.app.zwlenum.StatusEnum;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.adptor.AccountStatusSynchronizer;
import com.bootdo.system.domain.BankInfoDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.BankInfoService;
import com.bootdo.system.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
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
    private RoleService roleService;

    @Autowired
    private RedisUtils redisUtils;

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

        UserDO userDO = ShiroUtils.getUser();
        final RoleTypeEnum currentRoleEnum = roleService.distinguishByLoginInfo(userDO);
        if (currentRoleEnum != RoleTypeEnum.ALL) {
            params.put("mid", userDO.getUserId());
        }

        //查询列表数据
        Query query = new Query(params);
        List<BankInfoDO> bankInfoList = bankInfoService.list(query);
        if (bankInfoList != null && bankInfoList.size() > 0) {
            bankInfoList.stream().forEach(payAlipay -> {
                String payStatisticsInfoKey = "payStatisticsInfoKey_" + payAlipay.getMid() + "_" + PayTypeEnum.APLIPAY_CODE.getKey() + ":" + payAlipay.getId();
                if (redisUtils.hasKey(payStatisticsInfoKey)) {
                    StatisticsInfo statisticsInfo = (StatisticsInfo) redisUtils.get(payStatisticsInfoKey);
                    payAlipay.setSucceedTxTimes(statisticsInfo.getPayedTotalOrderNum());
                    payAlipay.setTotalTxTimes(statisticsInfo.getTotalOrderNum());
                    payAlipay.setTotalReceivedAmount(new BigDecimal(statisticsInfo.getPayedTotalAmount()));
                }

            });
        }
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
        UserDO userDO = ShiroUtils.getUser();
        if (flag.equals(StatusEnum.ENABLE.getKey())) {
            String payListKey = Constants.getPayInfoListKey(PayTypeEnum.BANK_CODE.getKey());
            redisUtils.addPaymentInfo(payListKey,bankInfo);
        }
        String payInfoKey = Constants.getPayInfoKey(PayTypeEnum.BANK_CODE.getKey(),userDO.getUserId() ,id);
        redisUtils.set(payInfoKey,bankInfo);

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
        bankInfo.setStatus(StatusEnum.DISABLE.getKey());
        if (bankInfoService.save(bankInfo) > 0) {
            //redisUtils.set(Constants.getPayInfoKey(PayTypeEnum.BANK_CODE.getKey(),bankInfo.getId().intValue()),bankInfo);
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
        bankInfo.setStatus(StatusEnum.DISABLE.getKey());
        bankInfoService.update(bankInfo);
        UserDO userDO = ShiroUtils.getUser();
        redisUtils.set(Constants.getPayInfoKey(PayTypeEnum.BANK_CODE.getKey(),userDO.getUserId(),bankInfo.getId().intValue()),bankInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:bankInfo:remove")
    public R remove(Integer id) {
        UserDO userDO = ShiroUtils.getUser();
        if (bankInfoService.remove(id) > 0) {
            redisUtils.remove(Constants.getPayInfoKey(PayTypeEnum.BANK_CODE.getKey(),userDO.getUserId(),id));
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
