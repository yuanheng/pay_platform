package com.bootdo.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.app.config.Constants;
import com.bootdo.app.model.StatisticsInfo;
import com.bootdo.app.util.DateUtil;
import com.bootdo.app.util.DistributedLock;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.app.zwlenum.OrderStatusEnum;
import com.bootdo.app.zwlenum.StatusEnum;
import com.bootdo.common.utils.*;
import com.bootdo.system.domain.*;
import com.bootdo.system.service.MerchantService;
import com.bootdo.system.service.OrderService;
import com.bootdo.system.service.RoleService;
import com.bootdo.system.service.TbOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-08-20 21:24:35
 */

@Controller
@RequestMapping("/system/order")
public class OrderController {

    private RoleService roleService;
    private OrderService orderService;
    private MerchantService merchantService;

    @Autowired
    private TbOrderService tbOrderService;

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private DistributedLock distributedLock;


    @Autowired
    public OrderController(RoleService roleService, OrderService orderService, MerchantService merchantService) {
        this.roleService = roleService;
        this.orderService = orderService;
        this.merchantService = merchantService;

    }

    @GetMapping()
    @RequiresPermissions("system:order:order")
    String Order(Model model) {
        UserDO userDO = ShiroUtils.getUser();
        List<RoleDO> roles = roleService.list(userDO.getUserId());

        for (RoleDO role : roles) {
            if (role.getRoleSign().equals("true")) {
                if (role.getRoleId() - 60L == 0) {
                    model.addAttribute("roleId","60");
                }
            }

        }
        model.addAttribute("chanleId",userDO.getUserId());

        return "system/order/order";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:order:order")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //获取当前用户的角色
        UserDO userDO = ShiroUtils.getUser();
        List<RoleDO> roles = roleService.list(userDO.getUserId());
        //暂时通过id 来判断 是否是管理员，商户，码商
        for (RoleDO role : roles) {
            if (role.getRoleSign().equals("true")) {
                params.put("roleId", role.getRoleId());
                if (role.getRoleId() - 60L == 0) {
                    MerchantDO merchantDO = merchantService.getByMid(userDO.getUserId());
                    params.put("merchantNo",merchantDO.getMerchantNo());
                }
                if (role.getRoleId() - 61L == 0) {
                    params.put("mid", userDO.getUserId());
                }
            }

        }
        //查询列表数据
        Query query = new Query(params);
        List<OrderDO> orderList = orderService.list(query);
        int total = orderService.count(query);
        PageUtils pageUtils = new PageUtils(orderList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("system:order:add")
    String add() {
        return "system/order/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:order:edit")
    String edit(@PathVariable("id") Integer id, Model model) {
        OrderDO order = orderService.get(id);
        model.addAttribute("order", order);
        return "system/order/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:order:add")
    public R save(OrderDO order) {
        if (orderService.save(order) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:order:edit")
    public R update(OrderDO order) {
        orderService.update(order);
        return R.ok();
    }

    /**
     * 确认收款
     */
    @ResponseBody
    @RequestMapping("/confirm")
    @RequiresPermissions("system:order:edit")
    public R confirm(Integer id) {
        OrderDO orderDO = orderService.get(id);
        Assert.notNull(orderDO, "不存在的订单，请确认");
        if (orderDO.getStatus().equals(OrderStatusEnum.PRE_PAY.getKey())){
            // 更新订单状态
            orderDO.setStatus(OrderStatusEnum.FINISHED_PAY.getKey());
            orderDO.setFinishTime(new Date());
            orderService.update(orderDO);
            String paymentInfo = orderDO.getPaymentInfo();
            JSONObject payJson = JSONObject.parseObject(paymentInfo);
            Integer tbOrderId = payJson.getInteger("id");
            TbOrderDO tempTbOrder = new TbOrderDO();
            tempTbOrder.setId(tbOrderId);
            tempTbOrder.setStatus(StatusEnum.FINISHED.getKey());
            tempTbOrder.setEndTime(new Date());
            tbOrderService.update(tempTbOrder);
            orderService.notifyMerchant(orderDO);
            if (orderDO.getStatus().equals(OrderStatusEnum.CALLBACK_SUCCESS.getKey())){
                orderService.update(orderDO);
                // 触发统计
                staticsSuccessOrder(orderDO);
            }
            return R.ok();
        } else {
            return R.error("已经处理过");
        }



    }

    /**
     * 重新发起回调
     */
    @ResponseBody
    @RequestMapping("/callbackOrder")
    @RequiresPermissions("system:order:edit")
    public R callbackOrder(Integer id) {
        OrderDO orderDO = orderService.get(id);
        Assert.notNull(orderDO, "不存在的订单，请确认");
        if (orderDO.getStatus().equals(OrderStatusEnum.CALLBACK_FAILED.getKey())) {
            // 更新订单状态
            orderDO.setStatus(OrderStatusEnum.FINISHED_PAY.getKey());
            orderDO.setFinishTime(new Date());
            orderService.notifyMerchant(orderDO);
            orderService.update(orderDO);
            if (orderDO.getStatus().equals(OrderStatusEnum.CALLBACK_SUCCESS.getKey())){
                // 触发统计
                staticsSuccessOrder(orderDO);
            } else {
                return R.error();
            }

        }

        return R.ok();
    }


    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:order:remove")
    public R remove(Integer id) {
        if (orderService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("system:order:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        orderService.batchRemove(ids);
        return R.ok();
    }


    public void staticsSuccessOrder (OrderDO orderDO){
        String payInfo = orderDO.getPaymentInfo();
        JSONObject payInfoJSON = JSONObject.parseObject(payInfo);
        String type = orderDO.getPayType();
        String payId = payInfoJSON.getString("id");
        //String payStatisticsInfoKey = "payStatisticsInfoKey_" + orderDO.getMid() + "_" + type + ":" + payId;
       // updateStatisticsInfo(payStatisticsInfoKey,orderDO);
       // updateStatisticsInfoCuccrentDay(payStatisticsInfoKey,orderDO.getAmount());
        String merchantStatisticsInfoKey = "merchantStatisticsInfoKey_" + orderDO.getMerchantNo();
        updateStatisticsInfo(merchantStatisticsInfoKey,orderDO);
        updateStatisticsInfoCuccrentDay(merchantStatisticsInfoKey,orderDO.getAmount());
    }


    private void updateStatisticsInfo(String key, OrderDO orderDO) {
        if (redisUtils.hasKey(key)) {
            try {
                distributedLock.getLock(key);
                StatisticsInfo statisticsInfo = (StatisticsInfo) redisUtils.get(key);
                statisticsInfo.setPayedTotalAmount(statisticsInfo.getPayedTotalAmount() + Integer.parseInt(orderDO.getAmount()));
                statisticsInfo.setPayedTotalOrderNum(statisticsInfo.getPayedTotalOrderNum() + 1);
                redisUtils.set(key,statisticsInfo);
            } finally {
                distributedLock.releaseLock(key);
            }
        }
    }

    private void updateStatisticsInfoCuccrentDay(String key, String amount) {
        key = Constants.getTodayKey(key);
        if (redisUtils.hasKey(key)) {
            try {
                distributedLock.getLock(key);
                Integer tempAmount = (Integer) redisUtils.get(key);
                redisUtils.set(key,Integer.parseInt(amount)+tempAmount);
            } finally {
                distributedLock.releaseLock(key);
            }
        } else {
            try {
                distributedLock.getLock(key);
                redisUtils.set(key,Integer.parseInt(amount));
            } finally {
                distributedLock.releaseLock(key);
            }
        }
    }

}
