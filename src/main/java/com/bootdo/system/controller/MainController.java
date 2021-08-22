package com.bootdo.system.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.MathUtil;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.BankInfoDO;
import com.bootdo.system.domain.StatisticInfoDO;
import com.bootdo.system.service.BankInfoService;
import com.bootdo.system.service.PayAlipayInfoService;
import com.bootdo.system.service.PayWechatInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zhu YongQi
 * @date 2021/8/22.
 */
@Controller
@RequestMapping("/main")
public class MainController extends BaseController {

    private BankInfoService bankInfoService;
    private PayAlipayInfoService alipayInfoService;
    private PayWechatInfoService wechatInfoService;

    @Autowired
    public MainController(BankInfoService bankInfoService, PayAlipayInfoService alipayInfoService, PayWechatInfoService wechatInfoService) {
        this.bankInfoService = bankInfoService;
        this.alipayInfoService = alipayInfoService;
        this.wechatInfoService = wechatInfoService;
    }

    @GetMapping()
    String main() {
        return "main";
    }

    @Log("主页统计信息")
    @GetMapping({"/statistic"})
    @ResponseBody
    Object statistic() {
        // 统计商户信息
        final Long userId = ShiroUtils.getUserId();



        final List<BankInfoDO> bankInfoList = bankInfoService.getByUserId(userId);

        // TODO Alipay

        // TODO WePay

        StatisticInfoDO statInfoDO = new StatisticInfoDO().emptyInstance();
        if (!CollectionUtils.isEmpty(bankInfoList)) {
            BigDecimal totalReceivedAmount = MathUtil.addUpDigit(bankInfoList.stream().filter(e -> e.getTotalReceivedAmount() != null)
                    .map(BankInfoDO::getTotalReceivedAmount).collect(Collectors.toList()));
            statInfoDO.setTotalReceivedAmount(totalReceivedAmount);
        }
        return statInfoDO;
    }

}
