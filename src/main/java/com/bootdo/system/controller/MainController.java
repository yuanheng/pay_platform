package com.bootdo.system.controller;

import com.bootdo.app.model.StatisticsInfo;
import com.bootdo.app.util.DistributedLock;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.app.zwlenum.RoleTypeEnum;
import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.MathUtil;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.BankInfoDO;
import com.bootdo.system.domain.MerchantDO;
import com.bootdo.system.domain.StatisticInfoDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.BankInfoService;
import com.bootdo.system.service.MerchantService;
import com.bootdo.system.service.PayAlipayInfoService;
import com.bootdo.system.service.PayWechatInfoService;
import com.bootdo.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Prometheus
 * @date 2021/8/22.
 */
@Controller
@RequestMapping("/main")
public class MainController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private RedisUtils redisUtils;


    @GetMapping()
    String main(Model model) {
        // 统计信息
        UserDO user = ShiroUtils.getUser();
        final Long userId = user.getUserId();
        // 看当前登录人是啥角色
        final RoleTypeEnum currentRoleEnum = roleService.distinguishByLoginInfo(user);
        model.addAttribute("roleType", currentRoleEnum);
        // 商户信息
        if (currentRoleEnum == RoleTypeEnum.MERCHANT) {
            MerchantDO byMid = merchantService.getByMid(userId);
            model.addAttribute("merchantInfo", byMid);
        }
        return "main";
    }

    @Log("主页统计信息")
    @GetMapping({"/statistics"})
    @ResponseBody
    public StatisticsInfo statistics() {
        // 统计信息
        UserDO user = ShiroUtils.getUser();
        final Long userId = user.getUserId();

        // 看当前登录人是啥角色
        final RoleTypeEnum currentRoleEnum = roleService.distinguishByLoginInfo(user);

        StatisticInfoDO statInfoDO = new StatisticInfoDO().emptyInstance(-1);
        statInfoDO.setCurrentRoleEnum(currentRoleEnum);

        // 做对应统计
        if (currentRoleEnum == RoleTypeEnum.MERCHANT) {
            MerchantDO merchantDO = merchantService.getByMid(userId);
            String merchantStatisticsInfoKey = "merchantStatisticsInfoKey_" + merchantDO.getMerchantNo();
            if (redisUtils.hasKey(merchantStatisticsInfoKey)) {
                StatisticsInfo statisticsInfo = (StatisticsInfo) redisUtils.get(merchantStatisticsInfoKey);
                return statisticsInfo;
            }
        }

        return new StatisticsInfo();
    }


}
