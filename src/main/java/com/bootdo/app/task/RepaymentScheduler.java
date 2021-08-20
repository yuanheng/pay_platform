package com.bootdo.app.task;

import com.bootdo.app.config.Constants;
import com.bootdo.app.service.impl.AuthManager;
import com.bootdo.app.util.NumberUtil;
import com.bootdo.app.util.OrderCodeUtil;
import com.bootdo.app.zwlenum.PayStatusEnum;
import com.bootdo.app.zwlenum.TradeTypeEnum;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.system.domain.AgreementDO;
import com.bootdo.system.domain.MemberDO;
import com.bootdo.system.domain.MemberLevelDO;
import com.bootdo.system.domain.TradeLogDO;
import com.bootdo.system.service.AgreementService;
import com.bootdo.system.service.MemberLevelService;
import com.bootdo.system.service.MemberService;
import com.bootdo.system.service.TradeLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Configuration
@EnableScheduling
public class RepaymentScheduler {

    private static Logger logger = LoggerFactory.getLogger(RepaymentScheduler.class);

    @Autowired
    private AgreementService agreementService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private TradeLogService tradeLogService;
    @Autowired
    private AuthManager authManager;

    @Autowired
    private MemberLevelService memberLevelService;


    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    //每天9点半和15点半执行一次
    @Scheduled(cron = "0 30 9,14 * * ?")
    public void purchase() {

        Date currentDate = new Date();

        logger.info("开始执行自动抢单任务：{}", DateUtils.format(currentDate, DateUtils.DATE_TIME_PATTERN));
        Map<String, Object> params = new HashMap<>();
        params.put("endTime", currentDate);
        params.put("status", PayStatusEnum.ENABLE.toString());

        //查询当前时间前的生效合约
        List<AgreementDO> agreementDOList = agreementService.list(params);

        if (agreementDOList == null || agreementDOList.size() == 0) {
            logger.info("当前定时任务没有智能合约，执行结果为空{}", DateUtils.format(currentDate, DateUtils.DATE_TIME_PATTERN));
            return;
        }

        for (AgreementDO agreementDO : agreementDOList) {
            try{
                //判断当前的金额是否大于签约合同的额度。
                String agreementAmount = agreementDO.getAmount();
                final MemberDO memberDO = memberService.get(agreementDO.getMid());
                synchronized (memberDO.getUsername().intern()) {
                    String freeAmount = memberDO.getFreeAmount();
                    if (NumberUtil.compare(agreementAmount, freeAmount) > 0) {
                        logger.info("会员手机号{},的余额不足，签约金额{},可用余额{}", memberDO.getUsername(), agreementAmount, freeAmount);
                        continue;
                    }
                    //冻结金额增加 可用余额减少
                    String freezeAmout = NumberUtil.add(memberDO.getLockAmount(), agreementAmount);
                    String tempFreeAmount = NumberUtil.subtract(freeAmount, agreementAmount);

                    //生成购买记录
                    TradeLogDO tradeLogDOBuy = new TradeLogDO();
                    tradeLogDOBuy.setmFreeTotal(tempFreeAmount);
                    tradeLogDOBuy.setStatus(PayStatusEnum.ONLIONING.toString());
                    tradeLogDOBuy.setType(TradeTypeEnum.PURCHASE.toString());
                    tradeLogDOBuy.setCreateTime(currentDate);
                    tradeLogDOBuy.setFactAmount(agreementAmount);
                    tradeLogDOBuy.setAmount(agreementAmount);
                    tradeLogDOBuy.setTradeNo(OrderCodeUtil.getOrderCode(Long.valueOf(memberDO.getId())));
                    tradeLogDOBuy.setAgreeId(agreementDO.getId());
                    tradeLogDOBuy.setMid(memberDO.getId());
                    tradeLogDOBuy.setMobile(memberDO.getUsername());
                    tradeLogService.save(tradeLogDOBuy);

                    //更新会员可用余额和冻结金额
                    memberDO.setFreeAmount(tempFreeAmount);
                    memberDO.setLockAmount(freezeAmout);
                    memberService.update(memberDO);
                    try{
                        authManager.refeshMemberInfo(memberDO);
                    }catch (Exception e){
                        e.printStackTrace();
                    }




                }

                new Thread() {
                    @Override
                    public void run() {
                        memberLevelService.updateMemberLevel(memberDO);
                    }
                }.start();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }



    //每天13点半和18点半执行一次
    @Scheduled(cron = "0 30 13,18 * * ?")
    public void purchaseBack() {
        //获取当前尚未返现的购买记录
        Map<String,Object> params = new HashMap<>();
        params.put("status",PayStatusEnum.ONLIONING.toString());
        params.put("type",TradeTypeEnum.PURCHASE.toString());
        List<TradeLogDO> tradeLogDOS = tradeLogService.list(params);
        if(tradeLogDOS != null && tradeLogDOS.size() > 0){
            for(TradeLogDO tradeLogDO : tradeLogDOS){
                try{
                    handlerPurchaseBack(tradeLogDO.getMid(),tradeLogDO.getId());
                }catch (Exception e){
                    logger.error("返现失败：tid="+tradeLogDO.getId());
                    e.printStackTrace();
                }

            }
        }
    }




    /**
     * 10分钟1次判断 当前合约是否失效
     *
     **/
  //  @Scheduled(cron = "0 */10 * * * ?")
    private void checkAgreetmentBack() {
        logger.info("轮询检测过期合约");
        Date current = new Date();

        logger.info("轮询检测过期合约"+sdf.format(current));
        List<AgreementDO> agreementDOS = agreementService.getExpireAgreements(current);


        if(agreementDOS == null){
            logger.info("轮询检测过期合约：{},没有过期合约",sdf.format(current));
        }else{
            logger.info("轮询检测过期合约：{},过期合约:{}",sdf.format(current),agreementDOS.toString());
        }

        if(agreementDOS != null && agreementDOS.size() > 0){
            for(AgreementDO agreementDO : agreementDOS){
                try{
                    handlerAgreementBack(agreementDO);
                }catch (Exception e){
                    logger.error("合约解冻失败 agreeid="+agreementDO.getId());
                    e.printStackTrace();
                }

            }
        }

    }

    public void handlerAgreementBack(AgreementDO agreementDO) {
        Date current = new Date();

        Integer mid = agreementDO.getMid();
        Integer agreementId = agreementDO.getId();
        //获取当前合约对应的交易记录
        //agreement 状态更新
        agreementDO.setId(agreementId);
        agreementDO.setStatus(PayStatusEnum.DISABLE.toString());
        agreementService.update(agreementDO);


        TradeLogDO tradeLogDO = tradeLogService.getTradeLogByAgreeId(agreementId);
        if(tradeLogDO == null){
            logger.error("未找到 合约id={}对应的交易的记录",agreementId);
            return;
        }

        if(tradeLogDO.getStatus().equals(PayStatusEnum.FINISHED.toString())){
            logger.error("已经处理过了合约id={}对应的交易的记录",agreementId);
            return;
        }
        //保证金回退
        String amount = tradeLogDO.getAmount();

        MemberDO memberDO = memberService.get(mid);

        TradeLogDO backTradeLog = new TradeLogDO.Builder()
                .tradeNo(OrderCodeUtil.getOrderCode(Long.valueOf(memberDO.getId())))
                .mobile(memberDO.getUsername())
                .mid(memberDO.getId())
                .remark("原交易单号：" + tradeLogDO.getTradeNo())
                .mFreeTotal(NumberUtil.add(memberDO.getFreeAmount(),amount))
                .type(TradeTypeEnum.UN_FREEZE.toString())
                .createTime(current)
                .finishedTime(current)
                .amount(amount)
                .status(PayStatusEnum.FINISHED.toString())
                .factAmount(amount)
                .agreeId(agreementId)
                .build();

        tradeLogService.save(backTradeLog);

        tradeLogDO.setStatus(PayStatusEnum.FINISHED.toString());
        tradeLogDO.setFinishedTime(new Date());
        tradeLogService.update(tradeLogDO);

        synchronized (memberDO.getUsername().intern()){
            memberDO.setFreeAmount(NumberUtil.add(memberDO.getFreeAmount(),amount));
            memberDO.setLockAmount(NumberUtil.subtract(memberDO.getLockAmount(),amount));
            memberService.update(memberDO);
            authManager.refeshMemberInfo(memberDO);
        }
    }


    /**
     * 每天凌晨12点清空用户的当日收益
     */
    @Scheduled(cron = "0 0 0 * * ?")
    private void refreshMember() {
        Map<String, Object> params = new HashMap<>();

        List<MemberDO> memberDOS = memberService.list(params);
        if (memberDOS != null && memberDOS.size() > 0) {
            for (MemberDO memberDO : memberDOS) {
                memberDO.setDayProfitAmount("0.00");
                memberDO.setTeamProfitAmount("0.00");
                memberService.update(memberDO);
                authManager.refeshMemberInfo(memberDO);
            }
        }
    }











    public void handlerPurchaseBack(Integer mid, Integer tid) {

        Date current = new Date();

        MemberDO memberDO = memberService.get(mid);

        logger.info("会员{}，代还回报开始计算，交易id:{}", memberDO.getUsername(), tid);

        /*
         * 获取当前用户的推荐会员数
         */
        MemberLevelDO memberLevelDO = memberLevelService.getByMid(mid);

        int num = memberLevelDO.getLevel1();

        synchronized (memberDO.getUsername().intern()) {

            String percent = Constants.PROFIT_PERCENT_A;
            if(num > 0 && num < Constants.NUM_A){
                percent = Constants.PROFIT_PERCENT_B;
            }else if(num >= Constants.NUM_A){
                percent = Constants.PROFIT_PERCENT_C;
            }

            TradeLogDO tradeLogDOBuyed = tradeLogService.get(tid);

            if(!tradeLogDOBuyed.getType().equals(TradeTypeEnum.PURCHASE.toString())){
                return;
            }
            if(tradeLogDOBuyed.getStatus().equals(PayStatusEnum.FINISHED.toString())){
                return;
            }
            tradeLogDOBuyed.setStatus(PayStatusEnum.FINISHED.toString());
            tradeLogDOBuyed.setFinishedTime(current);
            tradeLogService.update(tradeLogDOBuyed);

            String amount = tradeLogDOBuyed.getAmount();
            String profit = NumberUtil.multiply(amount, percent);
            String totalProfit = NumberUtil.add(amount, profit);
            TradeLogDO backTradeLog = new TradeLogDO.Builder()
                    .tradeNo(OrderCodeUtil.getOrderCode(Long.valueOf(memberDO.getId())))
                    .mobile(memberDO.getUsername())
                    .mid(memberDO.getId())
                    .remark("原交易单号：" + tradeLogDOBuyed.getTradeNo())
                    .mFreeTotal(NumberUtil.add(memberDO.getFreeAmount(), totalProfit))
                    .type(TradeTypeEnum.PURCHASE_BACK.toString())
                    .createTime(current)
                    .finishedTime(current)
                    .amount(totalProfit)
                    .status(PayStatusEnum.FINISHED.toString())
                    .factAmount(amount)
                    .build();

            tradeLogService.save(backTradeLog);

            //增加用户的总额，可用余额 ，以及总收益，以及当日收益，   减少冻结金额
            String lockAmount = NumberUtil.subtract(memberDO.getLockAmount(), amount);
            String freeAmount = NumberUtil.add(memberDO.getFreeAmount(), totalProfit);
            String totalAmount = NumberUtil.add(memberDO.getTotalAmount(), profit);
            String memberTotalProfit = NumberUtil.add(memberDO.getProfitAmount(), profit);
            String todayProfit = NumberUtil.add(memberDO.getDayProfitAmount(), profit);

            memberDO.setDayProfitAmount(todayProfit);
            memberDO.setProfitAmount(memberTotalProfit);
            memberDO.setLockAmount(lockAmount);
            memberDO.setFreeAmount(freeAmount);
            memberDO.setTotalAmount(totalAmount);
            memberDO.setDayProfitAmount(todayProfit);

            logger.info("会员{}，的回报后的总金额;{}，可用余额：{}，总收益：{}，当日收益：{}，冻结金额：{}",
                    memberDO.getUsername(),
                    memberDO.getTotalAmount(),
                    memberDO.getFreeAmount(),
                    memberDO.getProfitAmount(),
                    memberDO.getDayProfitAmount(),
                    memberDO.getLockAmount());

            try{
                memberService.update(memberDO);
                authManager.refeshMemberInfo(memberDO);
            }catch (Exception e){
                e.printStackTrace();
            }


            //增加parent 的利益
            if (memberLevelDO.getParentMid() != null) {
                MemberDO parentMember = memberService.get(memberLevelDO.getParentMid());
                if(parentMember != null){
                    generatorProfit(parentMember,amount,current,backTradeLog.getTradeNo(),Constants.PROFIT_GRAND);
                }


            }

            //增加grand 的利益
            if (memberLevelDO.getGrandMid() != null) {
                MemberDO grandeMember = memberService.get(memberLevelDO.getGrandMid());
                if(grandeMember != null){
                    generatorProfit(grandeMember,amount,current,backTradeLog.getTradeNo(),Constants.PROFIT_GRAND);
                }

            }
            //增加great 的利益
            if (memberLevelDO.getGreatMid() != null){
                MemberDO greatMember = memberService.get(memberLevelDO.getGreatMid());
                if(greatMember != null){
                    generatorProfit(greatMember,amount,current,backTradeLog.getTradeNo(),Constants.PROFIT_GRAND);
                }
            }

        }
    }

    private void generatorProfit(MemberDO memberDO, String amount,Date current,String backTradeNo,String profitPercent) {
        String parentProfit = NumberUtil.multiply(amount, profitPercent);
        TradeLogDO backTradeLogParent = new TradeLogDO.Builder()
                .tradeNo(OrderCodeUtil.getOrderCode(Long.valueOf(memberDO.getId())))
                .mobile(memberDO.getUsername())
                .mid(memberDO.getId())
                .mFreeTotal(NumberUtil.add(memberDO.getFreeAmount(), parentProfit))
                .type(TradeTypeEnum.MEMBER_PURCHASE_BACK.toString())
                .createTime(current)
                .finishedTime(current)
                .amount(parentProfit)
                .status(PayStatusEnum.FINISHED.toString())
                .factAmount(amount)
                .remark("原交易单号：" + backTradeNo)
                .build();
        tradeLogService.save(backTradeLogParent);

        wrapperMemberProfit(memberDO, parentProfit);
    }


    private void wrapperMemberProfit(MemberDO memberDO, String profit) {

        //同时增加会员的整体金额 可用余额 和团队奖励,以及
        String parentTotalAmount = memberDO.getTotalAmount();
        String parentFreeAmount = memberDO.getFreeAmount();
        String parentDayProfitAmount = memberDO.getDayProfitAmount();
        String parentTeamProfitAmount = memberDO.getTeamProfitAmount();
        String parentProfitAmount = memberDO.getProfitAmount();

        memberDO.setTotalAmount(NumberUtil.add(parentTotalAmount, profit));
        memberDO.setFreeAmount(NumberUtil.add(parentFreeAmount, profit));
        memberDO.setTeamProfitAmount(NumberUtil.add(parentTeamProfitAmount, profit));
        memberDO.setDayProfitAmount(NumberUtil.add(parentDayProfitAmount, profit));
        memberDO.setProfitAmount(NumberUtil.add(parentProfitAmount,profit));
        memberService.update(memberDO);
        try{
            authManager.refeshMemberInfo(memberDO);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
