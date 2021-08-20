package com.bootdo.testDemo;

import com.bootdo.app.config.Constants;
import com.bootdo.app.model.PayNotifyResult;
import com.bootdo.app.service.impl.AuthManager;
import com.bootdo.app.task.RepaymentScheduler;
import com.bootdo.app.util.DateUtil;
import com.bootdo.app.util.NumberUtil;
import com.bootdo.app.util.OrderCodeUtil;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.app.util.ShareCodeUtil;
import com.bootdo.app.zwlenum.AgreementTypeEnum;
import com.bootdo.app.zwlenum.PayStatusEnum;
import com.bootdo.app.zwlenum.TradeTypeEnum;
import com.bootdo.common.service.JobService;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.system.domain.AgreementDO;
import com.bootdo.system.domain.MemberDO;
import com.bootdo.system.domain.MemberLevelDO;
import com.bootdo.system.domain.TradeLogDO;
import com.bootdo.system.service.AgreementService;
import com.bootdo.system.service.MemberLevelService;
import com.bootdo.system.service.MemberService;
import com.bootdo.system.service.TradeLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.MissingFormatArgumentException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController()
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {


  @Autowired
  RedisTemplate redisTemplate;
  @Autowired
  MemberService memberService;
  @Autowired
  TradeLogService tradeLogService;
  @Autowired
  JobService taskScheduleJobService;
  @Autowired
  AuthManager authManager;
  @Autowired
  AgreementService agreementService;
  @Autowired
  RedisUtils redisUtils;
  @Autowired
  RepaymentScheduler repaymentScheduler;
  @Autowired
  private MemberLevelService memberLevelService;


  private static Logger logger = LoggerFactory.getLogger(TestDemo.class);

  @Test
  public void test() throws Exception{

    //redisUtils.set(Constants.ANDRIOD_URL,"https://ios.lxooydq.cn/app.php/NTg5OA==");
    //redisUtils.set(Constants.MY_MOBILE, "18651735549");
    //redisUtils.set(Constants.APP_PAY_TYPE,"OTC_PAY");
   //redisUtils.set(Constants.APP_PAY_TYPE,"STANDAR_PAY");
  //redisUtils.set(Constants.APP_PAY_TYPE,"OWNER_PAY");

/*
    List<PayInfo> payInfos = new ArrayList<>();

    PayInfo payInfo4 = new PayInfo("齐军","交通银行","西安分行","6222620810027873385");

    PayInfo payInfo2 = new PayInfo("秦新宇","招商银行","西安分行","6214832948575415");






    payInfos.add(payInfo4);

        payInfos.add(payInfo2);
    redisUtils.remove("pay_infos_set");
    redisUtils.sSet("pay_infos_set",payInfos);

    Set<Object> pay_infos = redisUtils.sGet("pay_infos_set");
    Iterator<Object> it = pay_infos.iterator();

    while (it.hasNext()) {
      List<Object> str = (ArrayList)it.next();
      System.out.println(str);
    }

    System.out.println(pay_infos);*/


    //redisUtils.set(Constants.WEB_SITE,"http://www.yikamile.com/");
    //redisUti ls.set(Constants.MY_URL,"http://www.yikamile.com/fastRegister.html");

   // redisUtils.set(Constants.ANDRIOD_VERSION,"1.4");
    // redisUtils.set(Constants.IOS_URL,"");
    //redisUtils.set(Constants.NOTICe_MSG,"");
  //  redisUtils.set(Constants.ANDRIOD_URL,"https://ios.lxooydq.cn/app.php/NTg5OA==");

   //redisUtils.set(Constants.KEFU_URL, "http://www.yikamile.com/img/kf1.jpg");
   // MemberDO memberDO = memberService.get(66187);
   // authManager.refeshMemberInfo(memberDO);
/**
    redisUtils.set(Constants.NYC_PRICE, "6.57");
    redisUtils.set(Constants.POOL_USDT_AMOUNT,5000000);
    redisUtils.set(Constants.COOL_WALLET_ADDRESS,"FUIWERIWEURIWEU423784723874JHSHSJDHFJSDFJKDSHJF");

    redisUtils.set(Constants.COOL_WALLET_ADDRESS_IMG,"https://mp.weixin.qq.com/mp/qrcode?scene=10000004&size=102&__biz=MzIzMzgxOTQ5NA==&mid=2247513761&idx=3&sn=5cd7d6139e3fc5187594f71a08e4a4f8&send_time=");
    redisUtils.decr(Constants.POOL_USDT_AMOUNT,100L);
*/
List<String> coins = new ArrayList<>();
coins.add("FILUSDT");
coins.add("BTCUSDT");
coins.add("ETHUSDT");
coins.add("DOGEUSDT");
coins.add("CRVUSDT");
coins.add("XMRUSDT");
redisUtils.set(Constants.COIN_LIST,coins);
for (String coin : coins) {
  redisUtils.set(Constants.CURRENT_SYMBOL_PRICE_PRE + coin.toUpperCase(), "100.00");
}

  }

  public static String md5(String str) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(str.getBytes());
      byte[] byteDigest = md.digest();
      int i;
      //字符数组转换成字符串
      StringBuffer buf = new StringBuffer("");
      for (int offset = 0; offset < byteDigest.length; offset++) {
        i = byteDigest[offset];
        if (i < 0)
          i += 256;
        if (i < 16)
          buf.append("0");
        buf.append(Integer.toHexString(i));
      }
      // 32位加密
      return buf.toString().toUpperCase();
      // 16位的加密
      //return buf.toString().substring(8, 24).toUpperCase();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Test
  public void initUser() {
    String code = ShareCodeUtil.toSerialCode(Long.parseLong("13162789927"));
    //初始化根用户
    memberService.register("13162789927", "123456", null);
    List<String> mobiles = new ArrayList<>();
    for (Long i = 1L; i < 6; i++) {
      String mobile = "13162" + getRandom620(6);
      if(i < 3){
        mobiles.add(mobile);
      }

      memberService.register(mobile, "123456", code);
      System.out.println("手机号：" + mobile);
    }

    List<String> children = new ArrayList<>();

    //在为这会员生成下级
    for (String tempMobile : mobiles) {

      for (int i = 1; i < 3; i++) {
        String tempCode = ShareCodeUtil.toSerialCode(Long.parseLong(tempMobile));
        String mobile = "1516" + i + getRandom620(6);
        if(i < 3){
          children.add(mobile);
        }

        memberService.register(mobile, "123456", tempCode);
        System.out.println("手机号：" + mobile);
      }

    }
    System.out.println("==========");

    //在为这会员生成下级
    for (String tempMobile : children) {
      for (int i = 1; i < 3; i++) {
        String tempCode = ShareCodeUtil.toSerialCode(Long.parseLong(tempMobile));
        String mobile = "18565" + i + getRandom620(6);
        memberService.register(mobile, "123456", tempCode);
        System.out.println("手机号：" + mobile);
      }

    }

  }


  @Test
  public void addMoney() {

    Map<String, Object> params = new HashMap<>();

    List<MemberDO> memberDOs = memberService.list(params);
    for (MemberDO memberDO : memberDOs) {
      //1.生成交易记录
      TradeLogDO tradeLogDO = tradeLogService.recharge(memberDO, "100000");
      PayNotifyResult payNotifyResult = new PayNotifyResult();
      payNotifyResult.setAmount(tradeLogDO.getAmount());
      payNotifyResult.setFactAmount(tradeLogDO.getAmount());
      payNotifyResult.setTradeNo(tradeLogDO.getTradeNo());
      payNotifyResult.setTradeOutNo("测试上分");
      MemberDO result = tradeLogService.paySucess(payNotifyResult);
      System.out.println("*************" + result);
    }


  }


  @Test
  public void setWallet() {

    redisUtils.set(Constants.COOL_WALLET_ADDRESS,"0x34505e282c5eed60602c29b455dcee3c7cc34603");
    redisUtils.set(Constants.COOL_WALLET_ADDRESS_IMG,"/img/wallet_address.jpg");
  }

  @Test
  public void qianyue() {
    Map<String, Object> params = new HashMap<>();

    List<MemberDO> memberDOs = memberService.list(params);

    String type = "month";
    AgreementTypeEnum agreementTypeEnum = AgreementTypeEnum.getDaysByKey(type);

    String amoungFen = "10000000";

    for (MemberDO memberDO : memberDOs) {
      Integer days = agreementTypeEnum.days;

      final Date currentDate = DateUtil.toDate("2020-03-08 20:30:24","yyyy-MM-dd hh:mm:ss");
      if (days != null) {
        final Date endDate = new Date(currentDate.getTime() + days * 24 * 60 * 60 * 1000L);
        synchronized (memberDO.getUsername().intern()) {
          //查询是否有合约 TODO
          AgreementDO agreementDO = agreementService.getMemberAgreetment(memberDO.getId());
          if (agreementDO == null) {
            String percent = agreementTypeEnum.percent;

            String freezeAmount = NumberUtil.multiply(amoungFen, percent);
            String freeAmount = NumberUtil.subtract(memberDO.getFreeAmount(), freezeAmount);
            String buyAmount = NumberUtil.subtract(amoungFen, freezeAmount);

            //生成智能合约
            agreementDO = new AgreementDO();
            agreementDO.setType(type);
            agreementDO.setStartTime(currentDate);
            agreementDO.setAmount(buyAmount);
            agreementDO.setPercent(percent);
            agreementDO.setMid(memberDO.getId());
            agreementDO.setMobile(memberDO.getUsername());
            agreementDO.setStatus(PayStatusEnum.ENABLE.toString());
            agreementDO.setFreeAmount(freezeAmount);
            agreementDO.setEndTime(endDate);
            agreementService.save(agreementDO);

            //生成保障金冻结金额
            TradeLogDO tradeLogDOFreeze = new TradeLogDO.Builder()
                    .status(PayStatusEnum.ONLIONING.toString())
                    .type(TradeTypeEnum.CASH_DEPOSIT.toString())
                    .mFreeTotal(freeAmount)
                    .factAmount(freezeAmount)
                    .amount(freezeAmount)
                    .mid(memberDO.getId())
                    .mobile(memberDO.getUsername())
                    .agreeId(agreementDO.getId())
                    .createTime(currentDate)
                    .tradeNo(OrderCodeUtil.getOrderCode(Long.valueOf(memberDO.getId())))
                    .build();

            tradeLogService.save(tradeLogDOFreeze);

            memberDO.setFreeAmount(freeAmount);
            //会员的冻结额度
            String mFreezeAmount = NumberUtil.add(memberDO.getLockAmount(), freezeAmount);
            memberDO.setLockAmount(mFreezeAmount);
            memberService.update(memberDO);
            //更新缓存
            authManager.refeshMemberInfo(memberDO);
          }
        }
      }
    }

  }

  @Test
  public void tradeLog(){
    try{
      Date initDate = DateUtils.newDate("2020-03-09 09:30:00");

      for (int i = 1; i < 58; i++) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(initDate);
        cd.add(Calendar.DATE, i);//增加一天
        Date startDate1 = cd.getTime();
        purchase(startDate1);
        backPurchase(new Date(startDate1.getTime() + 4 * 60 * 60 * 1000));

        purchase(new Date(startDate1.getTime() + 5 * 60 * 60 * 1000));
        backPurchase(new Date(startDate1.getTime() + 9 * 60 * 60 * 1000));

      }
    }catch (Exception e){
      e.printStackTrace();
    }

  }



  public void purchase(Date startDate){
    final Date currentDate = startDate;

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

  public void backPurchase(Date endDate){
    //获取当前尚未返现的购买记录
    Map<String,Object> params = new HashMap<>();
    params.put("status",PayStatusEnum.ONLIONING.toString());
    params.put("type",TradeTypeEnum.PURCHASE.toString());
    List<TradeLogDO> tradeLogDOS = tradeLogService.list(params);
    if(tradeLogDOS != null && tradeLogDOS.size() > 0){
      for(TradeLogDO tradeLogDO : tradeLogDOS){
        try{
          Integer mid = tradeLogDO.getMid();
          Integer tid = tradeLogDO.getId();

          final Date current = endDate;

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
        }catch (Exception e){
          logger.error("返现失败：tid="+tradeLogDO.getId());
          e.printStackTrace();
        }

      }
    }
  }
  public void buy() {

    Map<String, Object> params = new HashMap<>();

    List<MemberDO> memberDOs = memberService.list(params);
    for (MemberDO memberDO : memberDOs) {
      //1.生成交易记录
      TradeLogDO tradeLogDO = tradeLogService.recharge(memberDO, "20000");
      PayNotifyResult payNotifyResult = new PayNotifyResult();
      payNotifyResult.setAmount(tradeLogDO.getAmount());
      payNotifyResult.setFactAmount(tradeLogDO.getAmount());
      payNotifyResult.setTradeNo(tradeLogDO.getTradeNo());
      payNotifyResult.setTradeOutNo("测试上分");
      MemberDO result = tradeLogService.paySucess(payNotifyResult);
      System.out.println("*************" + result);
    }


  }


  @Test
  public void openbank() {

    Map<String, Object> params = new HashMap<>();

    List<MemberDO> memberDOs = memberService.list(params);
    for (MemberDO memberDO : memberDOs) {
      memberDO.setCardNo("411304190871635564");
      memberDO.setBankNo("123748238748374");
      memberDO.setBankAddress("南京市");
      memberDO.setBankName("招商银行");
      memberDO.setBankBranchName("南京支行");
      memberDO.setReallyName("王刚");
      memberService.update(memberDO);
    }


  }


  public void addUsers() {
    String code = "pjalbky";

    memberService.register("13070105067", "123456", code);
  }

  public void addTradeLog() {
    TradeLogDO backTradeLog = new TradeLogDO.Builder()
            .tradeNo(OrderCodeUtil.getOrderCode(Long.valueOf(55797L)))
            .mobile("13070103069")
            .mid(55797)
            .remark("原交易单号：")
            .mFreeTotal(1000 + "")
            .type(TradeTypeEnum.PURCHASE_BACK.toString())
            .createTime(new Date())
            .finishedTime(new Date())
            .amount("100001")
            .status(PayStatusEnum.FINISHED.toString())
            .factAmount("2000")
            .build();

    tradeLogService.save(backTradeLog);
  }


  public void getMemberInOutcome() {
    MemberDO memberDO = new MemberDO();
    memberDO.setId(60777);
    String income = memberService.getTotalIncome(memberDO.getId());
    String outcome = memberService.getTotalOutcome(memberDO.getId());
    System.out.println("income:" + income + ",outcome:" + outcome);
  }


  @Test
  public void updateTradeLog() {

    Map<String, Object> params1 = new HashMap<>();
    List<MemberDO> memberDOs = memberService.list(params1);
    try {

      for (int i = 0; i < memberDOs.size(); i++) {
        MemberDO memberDO = memberDOs.get(i);
        Date startDate1 = DateUtils.newDate("2020-01-26 13:30:00");
        Date startDate2 = DateUtils.newDate("2020-01-26 18:30:00");
        Integer mid = memberDO.getId();
        Map<String, Object> params = new HashMap<>();
        params.put("mid", mid);
        params.put("type", TradeTypeEnum.PURCHASE_BACK.toString());
        List<TradeLogDO> tradeLogDOS = tradeLogService.list(params);
        for (int j = 0; j < tradeLogDOS.size(); j++) {
          TradeLogDO tradeLogDO = tradeLogDOS.get(j);

          int mode = j % 2;

          if (mode == 0) {
            tradeLogDO.setCreateTime(startDate1);
            tradeLogDO.setFinishedTime(startDate1);
          } else {

            tradeLogDO.setCreateTime(startDate2);
            tradeLogDO.setFinishedTime(startDate2);

            startDate2 = new Date(startDate2.getTime() + 24 * 60 * 60 * 1000);
            startDate1 = new Date(startDate1.getTime() + 24 * 60 * 60 * 1000);
          }


          System.out.println(mid + "   " + DateUtils.format(tradeLogDO.getCreateTime(), "yyyy-MM-dd HH:mm:ss") + "  " + DateUtils.format(tradeLogDO.getFinishedTime(), "yyyy-MM-dd HH:mm:ss"));
          tradeLogService.update(tradeLogDO);
        }

      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public String getRandom620(Integer length) {
    String result = "";
    Random rand = new Random();
    int n = 20;
    if (null != length && length > 0) {
      n = length;
    }
    int randInt = 0;
    for (int i = 0; i < n; i++) {
      randInt = rand.nextInt(10);
      result += randInt;
    }
    return result;
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

  @Test
  public void checkAgreement(){
    logger.info("轮询检测过期合约");
    Date current = new Date();


    List<AgreementDO> agreementDOS = agreementService.getExpireAgreements(current);


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

  public void witdraw(){


  }


  @Test
  public void testRedis(){
    Map<String, String> map = new HashMap<>();
    map.put("fileId", "dingbs123");
    redisTemplate.opsForList().leftPush("trade_log", map);

    Object object = redisTemplate.opsForList().rightPop("trade_log", 1, TimeUnit.SECONDS);
    Map<String,String> result = (Map<String,String>) object;
    System.out.println("object=========" + result.get("fileId"));
  }


  @Test
  public void testRedisLock() {

  }
}
