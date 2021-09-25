package com.bootdo.system.controller;

import cn.hutool.json.JSONUtil;
import com.bootdo.app.config.Constants;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.common.config.Constant;
import com.bootdo.common.utils.JSONUtils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.TbOrderCookieDO;
import com.bootdo.system.domain.TbOrderDO;
import com.bootdo.system.domain.UserDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-09-08 22:32:27
 */
@Controller
@RequestMapping("/system/tbOrderCookie")
public class TbOrderCookieController {
    private RedisUtils redisUtils;

    private static final int SOLID_MID = 10000;

    @Autowired
    public void setRedisUtils(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @GetMapping()
    @RequiresPermissions("system:tbOrderCookie:tbOrderCookie")
    String TbOrder(Model model) {
        return "system/tbOrderCookie/tbOrderCookie";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:tbOrderCookie:tbOrderCookie")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        final String key = Constants.getTbOrderCookieKey(String.valueOf(SOLID_MID));
        List<Object> objects = redisUtils.lGet(key, query.getOffset(), query.getOffset() + query.getLimit() - 1);
        List<TbOrderCookieDO> list = new ArrayList<>(objects.size());
        objects.forEach(e -> {
            list.add(JSONUtil.toBean(String.valueOf(e), TbOrderCookieDO.class));
        });
        PageUtils pageUtils = new PageUtils(list, (int) redisUtils.lGetListSize(key));
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("system:tbOrderCookie:add")
    String add() {
        return "system/tbOrderCookie/add";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:tbOrder:add")
    public R save(TbOrderCookieDO orderCookieDO) {
        try {
            UserDO currentUser = ShiroUtils.getUser();
            final long mid = currentUser.getUserId();
            // 暂时使用固定商户维护cookie
            final String ckCacheKey = Constants.getTbOrderCookieKey(SOLID_MID + "");
            orderCookieDO.setCreateTime(new Date());
            redisUtils.addCookieInfo(ckCacheKey, JSONUtils.beanToJson(orderCookieDO));
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:tbOrder:remove")
    public R remove(Integer id) {
        try {
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error();
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
