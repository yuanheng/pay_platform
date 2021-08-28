package com.bootdo.system.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.bootdo.app.config.Constants;
import com.bootdo.app.model.StatisticsInfo;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.app.zwlenum.PayTypeEnum;
import com.bootdo.app.zwlenum.RoleTypeEnum;
import com.bootdo.app.zwlenum.StatusEnum;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.adptor.AccountStatusSynchronizer;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.domain.PayAlipayInfoDO;
import com.bootdo.system.domain.UserDO;
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
    private PayWechatInfoService payWechatInfoService;
    private AccountStatusSynchronizer statusSynchronizer;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    public PayWechatInfoController(PayWechatInfoService payWechatInfoService, AccountStatusSynchronizer statusSynchronizer) {
        this.payWechatInfoService = payWechatInfoService;
        this.statusSynchronizer = statusSynchronizer;
    }

    @GetMapping()
    @RequiresPermissions("system:payWechatInfo:payWechatInfo")
    String PayWechatInfo() {
        return "system/payWechatInfo/payWechatInfo";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:payWechatInfo:payWechatInfo")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        UserDO userDO = ShiroUtils.getUser();
        final RoleTypeEnum currentRoleEnum = roleService.distinguishByLoginInfo(userDO);
        if (currentRoleEnum != RoleTypeEnum.ALL) {
            params.put("mid", userDO.getUserId());
        }
        //查询列表数据
        Query query = new Query(params);
        List<PayWechatInfoDO> payWechatInfoList = payWechatInfoService.list(query);
        if (payWechatInfoList != null && payWechatInfoList.size() > 0) {
            payWechatInfoList.stream().forEach(payAlipay -> {
                String payStatisticsInfoKey = "payStatisticsInfoKey_" + payAlipay.getMid() + "_" + PayTypeEnum.WECHAT_CODE.getKey() + ":" + payAlipay.getId();
                if (redisUtils.hasKey(payStatisticsInfoKey)) {
                    StatisticsInfo statisticsInfo = (StatisticsInfo) redisUtils.get(payStatisticsInfoKey);
                    payAlipay.setSucceedTxTimes(statisticsInfo.getPayedTotalOrderNum());
                    payAlipay.setTotalTxTimes(statisticsInfo.getTotalOrderNum());
                    payAlipay.setTotalReceivedAmount(new BigDecimal(statisticsInfo.getPayedTotalAmount()));
                }
            });
        }
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
        if (flag.equals(StatusEnum.ENABLE.getKey())) {
            String payListKey = Constants.getPayInfoListKey(PayTypeEnum.WECHAT_CODE.getKey());
            redisUtils.addPaymentInfo(payListKey,payWechatInfo);
        }

        String payInfoKey = Constants.getPayInfoKey(PayTypeEnum.WECHAT_CODE.getKey(),payWechatInfo.getMid(),id);
        redisUtils.set(payInfoKey,payWechatInfo);

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
        payWechatInfo.setStatus(StatusEnum.DISABLE.getKey());
        if (payWechatInfoService.save(payWechatInfo) > 0) {
           // String payInfoKey = Constants.getPayInfoKey(PayTypeEnum.WECHAT_CODE.getKey(), payWechatInfo.getId().intValue());
           // redisUtils.set(payInfoKey,payWechatInfo);
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
        payWechatInfo.setStatus(payWechatInfo.getStatus());
        payWechatInfoService.update(payWechatInfo);
        UserDO userDO = ShiroUtils.getUser();
        String payInfoKey = Constants.getPayInfoKey(PayTypeEnum.WECHAT_CODE.getKey(), userDO.getUserId(), payWechatInfo.getId().intValue());
        redisUtils.set(payInfoKey,payWechatInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:payWechatInfo:remove")
    public R remove(Integer id) {
        UserDO userDO = ShiroUtils.getUser();
        if (payWechatInfoService.remove(id) > 0) {
            String payInfoKey = Constants.getPayInfoKey(PayTypeEnum.WECHAT_CODE.getKey(), userDO.getUserId(), id);
            redisUtils.remove(payInfoKey);
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
