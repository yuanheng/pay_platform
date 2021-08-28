package com.bootdo.system.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.bootdo.app.config.Constants;
import com.bootdo.app.model.StatisticsInfo;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.app.zwlenum.PayTypeEnum;
import com.bootdo.app.zwlenum.RoleTypeEnum;
import com.bootdo.app.zwlenum.StatusEnum;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.adptor.AccountStatusSynchronizer;
import com.bootdo.system.domain.BankInfoDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
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
    private PayAlipayInfoService payAlipayInfoService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    public PayAlipayInfoController(PayAlipayInfoService payAlipayInfoService) {
        this.payAlipayInfoService = payAlipayInfoService;

    }

    @GetMapping()
    @RequiresPermissions("system:payAlipayInfo:payAlipayInfo")
    String PayAlipayInfo() {
        return "system/payAlipayInfo/payAlipayInfo";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:payAlipayInfo:payAlipayInfo")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        UserDO userDO = ShiroUtils.getUser();
        final RoleTypeEnum currentRoleEnum = roleService.distinguishByLoginInfo(userDO);
        if (currentRoleEnum != RoleTypeEnum.ALL) {
            params.put("mid", userDO.getUserId());
        }
        //查询列表数据
        Query query = new Query(params);
        List<PayAlipayInfoDO> payAlipayInfoList = payAlipayInfoService.list(query);
        if (payAlipayInfoList != null && payAlipayInfoList.size() > 0) {
            payAlipayInfoList.stream().forEach(payAlipay -> {
                String payStatisticsInfoKey = "payStatisticsInfoKey_" + payAlipay.getMid() + "_" + PayTypeEnum.APLIPAY_CODE.getKey() + ":" + payAlipay.getId();
                if (redisUtils.hasKey(payStatisticsInfoKey)) {
                    StatisticsInfo statisticsInfo = (StatisticsInfo) redisUtils.get(payStatisticsInfoKey);
                    payAlipay.setSucceedTxTimes(statisticsInfo.getPayedTotalOrderNum());
                    payAlipay.setTotalTxTimes(statisticsInfo.getTotalOrderNum());
                    payAlipay.setTotalReceivedAmount(new BigDecimal(statisticsInfo.getPayedTotalAmount()));
                }
            });
        }
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
        if (flag.equals(StatusEnum.ENABLE.getKey())) {
            String payListKey = Constants.getPayInfoListKey(PayTypeEnum.APLIPAY_CODE.getKey());
            redisUtils.addPaymentInfo(payListKey,payAlipayInfo);
        }
        String payInfoKey = Constants.getPayInfoKey(PayTypeEnum.APLIPAY_CODE.getKey(),payAlipayInfo.getMid(), id);
        redisUtils.set(payInfoKey,payAlipayInfo);

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
        payAlipayInfo.setStatus(StatusEnum.DISABLE.getKey());
        if (payAlipayInfoService.save(payAlipayInfo) > 0) {
            //redisUtils.set(Constants.getPayInfoKey(PayTypeEnum.APLIPAY_CODE.getKey(),payAlipayInfo.getId().intValue()),payAlipayInfo);
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
        UserDO userDO = ShiroUtils.getUser();
        payAlipayInfo.setStatus(StatusEnum.DISABLE.getKey());
        payAlipayInfoService.update(payAlipayInfo);
        redisUtils.set(Constants.getPayInfoKey(PayTypeEnum.APLIPAY_CODE.getKey(),userDO.getUserId(),payAlipayInfo.getId().intValue()),payAlipayInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:payAlipayInfo:remove")
    public R remove(Integer id) {
        UserDO userDO = ShiroUtils.getUser();
        if (payAlipayInfoService.remove(id) > 0) {
            redisUtils.remove(Constants.getPayInfoKey(PayTypeEnum.APLIPAY_CODE.getKey(),userDO.getUserId(),id));
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
