package com.bootdo.app.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.app.annotation.AuthIgnore;
import com.bootdo.app.config.Constants;
import com.bootdo.app.global.AuthException;
import com.bootdo.app.model.PayNotifyResult;
import com.bootdo.app.model.Result;
import com.bootdo.app.qapple.util.RsaUtil;
import com.bootdo.app.qapple.vo.BaseResponse;
import com.bootdo.app.qapple.vo.MerchantMsgVo;
import com.bootdo.app.service.OtcPayService;
import com.bootdo.app.service.OwnerService;
import com.bootdo.app.service.StandarOtc;
import com.bootdo.app.service.UsdtPayService;
import com.bootdo.app.service.impl.AuthManager;
import com.bootdo.app.util.AmountUtil;
import com.bootdo.app.util.DateUtil;
import com.bootdo.app.util.IdentityUtils;
import com.bootdo.app.util.NumberUtil;
import com.bootdo.app.util.OrderCodeUtil;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.app.util.SmsUtil;
import com.bootdo.app.util.TradeUtil;
import com.bootdo.app.zwlenum.AgreementTypeEnum;
import com.bootdo.app.zwlenum.PayStatusEnum;
import com.bootdo.app.zwlenum.SmsTypeKeyEnum;
import com.bootdo.app.zwlenum.TradeTypeEnum;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.system.domain.AgreementDO;
import com.bootdo.system.domain.MemberDO;
import com.bootdo.system.domain.MemberLevelDO;
import com.bootdo.system.domain.TradeLogDO;
import com.bootdo.system.service.AgreementService;
import com.bootdo.system.service.MemberLevelService;
import com.bootdo.system.service.MemberService;
import com.bootdo.system.service.TradeLogService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RequestMapping("/api")
@RestController
public class ApiController {



    //商家账号名
    @Value("${MERCHANT_NAME}")
    private String MERCHANT_NAME;
    //商家用户merchant1私钥
    @Value("${MERCHANT_PRIVATE_KEY}")
    private String MERCHANT_PRIVATE_KEY;
    //用青苹果平台公钥
    @Value("${QAPPLE_PUBLIC_KEY}")
    private String QAPPLE_PUBLIC_KEY;
    //青苹果支付服务器地址
    @Value("${QAPPLE_SERVER_URL}")
    private  String QAPPLE_SERVER_URL;
    @Value("${pay_nofity_url}")
    private String notifyUrl;
    @Autowired
    private UsdtPayService usdtPayService;
    @Autowired
    private OtcPayService otcPayService;

    @Autowired
    private StandarOtc standarOtc;

    @Autowired
    private AuthManager authManager;
    @Autowired
    private SmsUtil smsUtil;
    @Autowired
    private MemberService memberService;
    @Autowired
    private TradeLogService tradeLogService;
    @Autowired
    private AgreementService agreementService;
    @Autowired
    public MemberLevelService memberLevelService;
    @Autowired
    private OwnerService ownerService;

    @Autowired
    private RedisUtils redisUtils;



    @Value("${ustd.mchId}")
    private String mchId;
    @Value("${ustd.mdkey}")
    private String mdKey;

    private static Logger logger = LoggerFactory.getLogger(ApiController.class);


    @RequestMapping(value = "/login")
    public Result<Map> userLogin(String username, String password, HttpServletRequest httpServletRequest) throws Exception {
        Map<String,Object> data = new HashMap<>();
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return Result.error("用户名或密码错误");

        }


        MemberDO memberDO = memberService.login(username,password);
        if(memberDO != null){
            String token = authManager.signIn(memberDO);
            data.put("token",token);
            return Result.ok(data);
        }else{
            return Result.error("用户名或密码错误");
        }

    }



    /**
     * 个人中心
     * @return
     */
    @RequestMapping(value = "/gifMoney")
    public Result gifMoney(){
        MemberDO memberDO = authManager.getUserInfo();

        synchronized (memberDO.getUsername().intern()){
            //判断当前用户是否领取过红包
            Map<String,Object> params = new HashMap<>();
            params.put("mid",memberDO.getId());
            params.put("type",TradeTypeEnum.GIF_MONEY.toString());
            List<TradeLogDO> tradeLogDOS = tradeLogService.list(params);
            if(tradeLogDOS != null && tradeLogDOS.size() > 0){
                return Result.error("您已经领取过，每个会员仅限领取一次！");
            }
            tradeLogService.gitMoney(memberDO,10000+"");
            memberDO = memberService.get(memberDO.getId());
        }
        authManager.refeshMemberInfo(memberDO);

        return Result.ok(memberDO);
    }


    @AuthIgnore
    @RequestMapping(value = "/sendSmsCode", method = RequestMethod.GET)
    public Result sendSmsCode(String mobile,String type) throws Exception{

        if(SmsTypeKeyEnum.WITH_DRAW.toString().equals(type)){
            //判断当前时间是否为9:00-21:00

            int currentHourOfDay = DateUtil.getHourOfDay();
            if(currentHourOfDay < 8 || currentHourOfDay > 23){
                return Result.error("充值时间为每天8:00-23:00");
            }

            MemberDO memberDO = authManager.getUserInfo();
            if(memberDO != null){
                mobile = memberDO.getUsername();
            }else{
                throw new AuthException("登录过期", HttpStatus.OK.value());
            }
        }

        Map<String,Object> sendResult = smsUtil.sendMobileSms(mobile,type,1);
        return Result.ok(sendResult);
    }



    @AuthIgnore
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(String mobile,String password,String code,String sourceInviteCode){

        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)){
            return Result.error("参数不正确");
        }
        if(StringUtils.isEmpty(sourceInviteCode)){
            return Result.error("邀请码不能为空");
        }
        MemberDO temp = memberService.getMemberByCode(sourceInviteCode);
        if(temp == null){
            return Result.error("邀请码不正确");
        }

        //校验手机号是否注册过
        if(smsUtil.validMobileIsRegister(mobile)){
            return Result.error("手机号已被注册");
        }

        //校验验证手机验证码
        if(!smsUtil.validSmsCode(code,mobile, SmsTypeKeyEnum.REGISTER.toString())){
            return Result.error("验证码不正确");
        }

        try{
            memberService.register(mobile,password,sourceInviteCode);
        }catch (Exception e){
            e.printStackTrace();
            Result.error("注册出错");
        }

        return Result.ok("注册成功");
    }


    @AuthIgnore
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    public Result updatePwd(String mobile,String password,String code){

        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)){
            return Result.error("参数不正确");
        }

        //校验手机号是否注册过
        if(!smsUtil.validMobileIsRegister(mobile)){
            return Result.error("手机号尚未被注册！");
        }
        //校验验证手机验证码
        if(!smsUtil.validSmsCode(code,mobile, SmsTypeKeyEnum.UPDATE_PASSWORD.toString())){
            return Result.error("验证码不正确");
        }

        try{
            memberService.updatePwd(mobile,password);
        }catch (Exception e){
            e.printStackTrace();
            Result.error("修改出错");
        }
        return Result.ok("注册成功");
    }



    /**
     * 生成支付订单，发起第三方支付
     */
    @RequestMapping(value = "/goPay", method = RequestMethod.POST)
    public Result goPay(String amount){
        MemberDO memberDO = authManager.getUserInfo();
        String username = memberDO.getUsername();
        String vipName = memberDO.getUsername().substring(4,username.length());
        //判断是否是否在可用时间
        int currentHourOfDay = DateUtil.getHourOfDay();
        if(currentHourOfDay < 9 || currentHourOfDay > 22){
            return Result.error("充值时间为每天9:00-23:00");
        }

        if(!isNumber(amount)){
            return Result.error("参数不正确，请输入数字!");
        }

        Double tempAmount = Double.parseDouble(amount);
        if(tempAmount < 100 || tempAmount > 10000){
            return Result.error("参数不正确，单次充值金额100-10000元!");
        }
        int temp = tempAmount.intValue();
        //判断当前是否用充值未支付
        if(redisUtils.exists(Constants.PAY_MID_PRE + "_"+memberDO.getId())){
            JSONObject  jsonObject = (JSONObject)redisUtils.get(Constants.PAY_MID_PRE + "_"+memberDO.getId());
            jsonObject.put("oldPay",true);
            return Result.ok(jsonObject);
        }

        //1.生成交易记录
        TradeLogDO tradeLogDO = tradeLogService.recharge(memberDO,temp+"");
        String tradeNo = tradeLogDO.getTradeNo();
        //在缓存中存储 ，防止重复支付回调。
        redisUtils.incr(tradeNo,1);
        redisUtils.setExpireTime(tradeNo,Constants.TRADELOG_NO_TIME);
        JSONObject transferInfo = null;

        try{
            String payType = (String) redisUtils.get(Constants.APP_PAY_TYPE);

            if(payType.equals("OWNER_PAY")){
                transferInfo = ownerService.createUsdtOrder(memberDO.getReallyName(),temp+"",tradeNo);
            }

            if(payType.equals("OTC_PAY")){
                transferInfo = otcPayService.createUsdtOrder(memberDO.getReallyName(),temp+"",tradeNo);
                if(transferInfo != null){
                    redisUtils.set(Constants.PAY_MID_PRE + "_"+memberDO.getId(),transferInfo,10 * 60L);
                }
            }
            if(payType.equals("STANDAR_PAY")){
                //transferInfo = usdtPayService.createUsdtOrder(memberDO.getReallyName(),temp+"",tradeNo);
                transferInfo = standarOtc.createUsdtOrder( memberDO.getReallyName(), temp+"", tradeNo+"");
                if(transferInfo != null){
                    redisUtils.set(Constants.PAY_MID_PRE + "_"+memberDO.getId(),transferInfo,10 * 60L);
                }
           }




        }catch (Exception e){
            e.printStackTrace();
        }

        return Result.ok(transferInfo);

    }

    private String getTransferInfo(Document transferInfos) {
        //获取支付变量
        Elements e = transferInfos.getElementsByTag("script").eq(8);
        String rmb="";
        //*循环遍历script下面的JS变量*//*
        for (Element element : e) {
            String[] data = element.data().split("var");
            for(String variable : data){
                if(variable.contains("=")){
                    String[]  kvp = variable.split("=");
                    if(variable.contains("buyVo")){
                        String payInfos = kvp[1].trim().substring(0, kvp[1].trim().length()-19);
                        payInfos = payInfos.trim();
                        payInfos = payInfos.substring(0,payInfos.length() - 1);
                        return payInfos;
                    }
                }
            }
        }
        return null;
    }


    /**
     * 获取资金交易记录
     */
    @RequestMapping(value = "/getRecord", method = RequestMethod.GET)
    public Result getRecord(){
        MemberDO memberDO = authManager.getUserInfo();
        Map<String,Object> params = new HashMap<>();
        params.put("mid",memberDO.getId());
        params.put("createTime", new Date(System.currentTimeMillis() - 2 * 30 * 24 * 60 * 60 *1000L));
        params.put("app","app");
        List<TradeLogDO> tradeLogDOS = tradeLogService.list(params);

        return Result.ok(tradeLogDOS);
    }



    /**
     * 个人中心
     * @return
     */
    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public Result my(){
        MemberDO memberDO = authManager.getUserInfo();
        //TODO
        AgreementDO agreementDO = agreementService.getMemberAgreetment(memberDO.getId());
        if(agreementDO == null) {
            memberDO.setAgreemtFlag("0");
        }else{
            memberDO.setAgreemtFlag("1");
        }

        String myUrl = (String) redisUtils.get(Constants.MY_URL);
        //设置用户的推荐链接
        memberDO.setMyUrl(myUrl+"?sourceCode="+memberDO.getOwnerInviteCode());
        try{
            memberDO.setVersion((String)redisUtils.get(Constants.APP_VERSION));
            memberDO.setAndroid_verison((String) redisUtils.get(Constants.ANDRIOD_VERSION));
            memberDO.setIosUrl((String) redisUtils.get(Constants.IOS_URL));
            memberDO.setAndriodUrl((String) redisUtils.get(Constants.ANDRIOD_URL));
            memberDO.setNotice((String)redisUtils.get(Constants.NOTICe_MSG));
            memberDO.setKefu_url((String) redisUtils.get(Constants.KEFU_URL));
        }catch (Exception e){
            e.printStackTrace();
        }
        memberDO.setWebSite((String) redisUtils.get(Constants.WEB_SITE));

        return Result.ok(memberDO);
    }



    /**
     * 我的团队
     * @return
     */
    @RequestMapping(value = "/myteam", method = RequestMethod.GET)
    public Result myteam(){
        MemberDO memberDO = authManager.getUserInfo();
        String currentCode = memberDO.getOwnerInviteCode();
        Map<String, Object> data = new HashMap<>();

        MemberLevelDO memberLevelDO = memberLevelService.getByMid(memberDO.getId());
        int num1 = memberLevelDO.getLevel1();
        int num2 = memberLevelDO.getLevel2();
        int num3 = memberLevelDO.getLevel3();


        if(memberLevelDO.getLevel1() == null){
            num1 = 0;
        }
        if(memberLevelDO.getLevel2() == null){
            num2 = 0;
        }
        if(memberLevelDO.getLevel3() == null){
            num3 = 0;
        }


        data.put("num",(num1 + num2 + num3)+"");

        data.put("level1Num",num1);
        data.put("level2Num",num2 + num3);

        //获取团队收益
        data.put("profit",memberDO.getTeamProfitAmount());

        //获取手下会员的详情和投资记录

        List<MemberDO> childrenMember = new ArrayList<>();
        List<MemberLevelDO> children = memberLevelService.getChildrenMember(memberDO.getId());
        Map<Integer,String> midSourceCodes = new HashMap<>();
        List<TradeLogDO>  tradeLogDOS = new ArrayList<>();
        if(children != null && children.size() > 0){
            for(MemberLevelDO levelDO : children){
                Integer mid = levelDO.getMid();
                Integer parentId = levelDO.getParentMid();
                MemberDO child = memberService.get(mid);
                midSourceCodes.put(mid,child.getSourceInviteCode());

                MemberDO tempChild = new MemberDO();
                //判断是直接或者间接
                String type="(间推)";
                if(parentId - memberDO.getId() == 0){
                    type = "(直推)";
                }
                tempChild.setUsername(child.getUsername().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                tempChild.setUsername( tempChild.getUsername() + type);
                tempChild.setCreateTime(child.getCreateTime());
                childrenMember.add(tempChild);
                Map<String,Object> params = new HashMap<>();
                params.put("mid",child.getId());
                params.put("createTime", new Date(System.currentTimeMillis() - 2 * 24 * 60 * 60 *1000L));
                params.put("app","app");
                params.put("type",TradeTypeEnum.PURCHASE.toString());
                params.put("status",PayStatusEnum.FINISHED.toString());
                List<TradeLogDO> tempTradeLogs = tradeLogService.list(params);
                if(tempTradeLogs != null && tempTradeLogs.size() > 0){
                    for(TradeLogDO tradeLogDO : tempTradeLogs){
                        tradeLogDO.setMobile(tempChild.getUsername());
                        tradeLogDO.setUsername(tempChild.getUsername());
                    }
                    tradeLogDOS.addAll(tempTradeLogs);
                }

            }
        }
        tradeLogDOS.sort(Comparator.comparing(TradeLogDO::getFinishedTime).reversed());

        childrenMember.sort(Comparator.comparing(MemberDO::getCreateTime).reversed());

        data.put("children",childrenMember);



        if(tradeLogDOS.size() > 0){
            for(TradeLogDO tradeLogDO : tradeLogDOS){
                tradeLogDO.setTradeNo(tradeLogDO.getTradeNo().replaceAll("(\\d{5})\\d{7}(\\d{5})", "$1*****$2"));
                tradeLogDO.setMobile(tradeLogDO.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                tradeLogDO.setUsername(tradeLogDO.getMobile());
                Integer tempMid = tradeLogDO.getMid();
                String tempCode = midSourceCodes.get(tempMid);
                if(currentCode.equals(tempCode)){
                    tradeLogDO.setAmount(NumberUtil.multiply(tradeLogDO.getFactAmount(),Constants.PROFIT_GRAND));
                }else {
                    tradeLogDO.setAmount(NumberUtil.multiply(tradeLogDO.getFactAmount(),Constants.PROFIT_GRAND));
                }

                tradeLogDO.setCreateTime(tradeLogDO.getFinishedTime());
            }
        }
        data.put("tradeLogDOS",tradeLogDOS);

        if(Constants.masterUsers.contains(memberDO.getUsername())){
            data.put("num",Constants.teams.get(memberDO.getUsername()));
            //获取团队收益
            data.put("profit",Constants.teamsProfit.get(memberDO.getUsername()));


        }

        return Result.ok(data);
    }

    /**
     * 我的合约
     */
    @RequestMapping(value = "/myAgreement", method = RequestMethod.GET)
    public Result myAgreement(){
        MemberDO memberDO = authManager.getUserInfo();
        Map<String, Object> data = new HashMap<>();
        data.put("mid",memberDO.getId());
        List<AgreementDO> agreementDOS = agreementService.list(data);
        return Result.ok(agreementDOS);
    }


    /**
     * 提现
     */
    @RequestMapping(value = "/withDraw", method = RequestMethod.POST)
    public Result withDraw(String code,String amount){
        MemberDO memberDO = authManager.getUserInfo();
        if(StringUtils.isEmpty(memberDO.getCardNo())){
            return Result.error("请先实名认证，绑定银行卡后进行提现！");
        }
        if(!isNumber(amount)){
            return Result.error("提现金额不正确，请输入正确的金额！");
        }
        int currentHourOfDay = DateUtil.getHourOfDay();
        if(currentHourOfDay < 10 || currentHourOfDay > 20){
            return Result.error("提现时间为每天10:00-21:00");
        }

        //校验验证码是否正确
        if(!smsUtil.validSmsCode(code,memberDO.getUsername(),SmsTypeKeyEnum.WITH_DRAW.toString())){
            return Result.error("手机验证码不正确！");
        }


        if(NumberUtil.compare(amount,"100") < 0 || NumberUtil.compare(amount,"10000") >0){
            return Result.error("参数不正确，单次提现金额100-10000元！");
        }

        //可用余额是否大于提现金额
        synchronized (memberDO.getUsername().intern()){

            if(NumberUtil.compare(AmountUtil.changeY2F(amount),memberDO.getFreeAmount()) > 0){
                return Result.error("余额不足！");
            }
            MemberLevelDO memberLevelDO = memberLevelService.getByMid(memberDO.getId());
            if(memberLevelDO.getStatus().equals(PayStatusEnum.DISABLE.toString())){
                //判断会员是否能够提现
                Map<String,Object> gifPamras = new HashMap<>();
                gifPamras.put("mid",memberDO.getId());
                gifPamras.put("type",TradeTypeEnum.GIF_MONEY.toString());
                List<TradeLogDO> gifMoneyLogs = tradeLogService.list(gifPamras);
                if(gifMoneyLogs != null && gifMoneyLogs.size() > 0){
                    String tempAmount =  NumberUtil.add(amount,"100");
                    if(NumberUtil.compare(AmountUtil.changeY2F(tempAmount),memberDO.getFreeAmount()) > 0){
                        return Result.error("红包额度未发生代还不能进行提现！");
                    }
                }
            }

            tradeLogService.withDraw(memberDO,amount);
        }


        try{
            //随机生成的动态码
            String dynamicCode = "" + (int)((Math.random() * 9 + 1) * 100000);

            String smsContent = "【亿卡米乐】您本次操作的验证码为"+dynamicCode+"，如非本人操作请忽略。";
            String mobile = (String) redisUtils.get(Constants.MY_MOBILE);

            String timestamp = DateUtils.format(new Date(),"yyyyMMddHHmmss");
            String sign = md5("yikamile123456"+timestamp).toLowerCase();
            String tempUrl="http://39.99.214.106:8088/v2sms.aspx?action=send&userid=1088&timestamp="+timestamp+"&sign="+sign+"&mobile="+mobile+"&content="+URLEncoder.encode(smsContent,"UTF-8")+"&sendTime=&extno=";

            HttpGet httpGet = new HttpGet(tempUrl);
            CloseableHttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String codeResult = EntityUtils.toString(entity);
            logger.info("重要提醒结果："+codeResult);

        }catch (Exception e){
            e.printStackTrace();
        }

        //更新缓存
        authManager.refeshMemberInfo();
        return Result.ok("操作成功");


    }



    /**
     * 购买
     */
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public Result buy(String type,String amount){

        final MemberDO memberDO = authManager.getUserInfo();
        MemberLevelDO memberLevelDO = memberLevelService.getByMid(memberDO.getId());
        if(StringUtils.isEmpty(memberDO.getCardNo())){
            return Result.error("请先实名认证，绑定银行卡后进行提现！");
        }

        //判断
        try{
            if(!isNumber(amount)){
                return Result.error("请输入正确的签约金额！");
            }
            AgreementTypeEnum agreementTypeEnum = AgreementTypeEnum.getDaysByKey(type);

            Integer maxAmount = agreementTypeEnum.maxAmount;

            //判断是否新手首次签约

            //if(memberLevelDO.getStatus().equals(PayStatusEnum.ENABLE.toString()) && agreementTypeEnum.equals(AgreementTypeEnum.DAY)){
              //  return Result.error("1天智能合约仅限新手首次签约！");
           // }

            if(NumberUtil.compare(amount,Constants.PER_BUY_MIN) < 0){
                return Result.error("单次最低"+Constants.PER_BUY_MIN+"元！");
            }
            if(NumberUtil.compare(amount,maxAmount+"") > 0){
                return Result.error("限额"+maxAmount+"元！");
            }
            String amoungFen = AmountUtil.changeY2F(amount);

            Long tempAmount = Long.parseLong(amoungFen);

            if(tempAmount.intValue() > Integer.parseInt(memberDO.getFreeAmount())){
                return Result.error("参数有误！");
            }



            Integer days = agreementTypeEnum.days;

            final Date currentDate = new Date();
            if(days != null){
                final Date endDate = new Date(currentDate.getTime() + days * 24 * 60 * 60 * 1000L);
                synchronized (memberDO.getUsername().intern()){
                    //查询是否有合约 TODO
                    AgreementDO agreementDO = agreementService.getMemberAgreetment(memberDO.getId());
                    if(agreementDO == null){
                        String percent = agreementTypeEnum.percent;

                        String freezeAmount = NumberUtil.multiply(amoungFen, percent);
                        String freeAmount = NumberUtil.subtract(memberDO.getFreeAmount(),freezeAmount);
                        String buyAmount = NumberUtil.subtract(amoungFen,freezeAmount);

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
                        String mFreezeAmount = NumberUtil.add(memberDO.getLockAmount(),freezeAmount);
                        memberDO.setLockAmount(mFreezeAmount);
                        memberService.update(memberDO);
                        //更新缓存
                        authManager.refeshMemberInfo(memberDO);

                    }else{
                        return Result.error("您签订的智能合约正在生效中，请勿重复签约！");
                    }
                }

            }else{
                return Result.error("参数有误");
            }

            return Result.ok("操作成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("处理错误，请重试");
        }



    }

    /**
     *
     */
    @RequestMapping(value = "/logout")
    public Result logout(){
        MemberDO memberDO = authManager.getUserInfo();
        authManager.loginOff(memberDO);
        return Result.ok("操作成功");

    }




    /**
     * 实名认证
     */
    @RequestMapping(value = "/openbank", method = RequestMethod.POST)
    public Result openBank(MemberDO memberDO){
        MemberDO currentMember = authManager.getUserInfo();
        memberDO.setId(currentMember.getId());
        //判断身份证是否合法
        if(!IdentityUtils.isLegalPattern(memberDO.getCardNo())){
            return Result.error("身份证不正确！");
        }
        //判断当前身份证是已经被使用
        Map<String,Object> params  = new HashMap<>();
        params.put("cardNo",memberDO.getCardNo());
        List<MemberDO> memberDOS = memberService.list(params);
        if(memberDOS != null && memberDOS.size() > 0){
            return Result.error("身份证已被实名制，请更换身份证，如有疑问请联系客服！");
        }
        memberService.update(memberDO);
        authManager.refeshMemberInfo();
        return Result.ok("操作成功");

    }


    /**
     * 支付回调
     * @return
     */
    @AuthIgnore
    @RequestMapping(value = "/notify_apple")
    public BaseResponse ustdNotifyPay(@RequestBody MerchantMsgVo merchantMsgVo){

        logger.info("receive merchant message: " + JSON.toJSONString(merchantMsgVo));
        BaseResponse error = new BaseResponse(613, "操作异常");
        //用青苹果平台公钥验签
        boolean checkSign = RsaUtil.doCheck( merchantMsgVo.makeSignJoinStr(), merchantMsgVo.getSign(), QAPPLE_PUBLIC_KEY);
        if (!checkSign){
            logger.error("验签失败！"+merchantMsgVo.getOutTradeNo());
            return error;
        }

        TradeLogDO tradeLogDO = tradeLogService.getTradeLogByNo(merchantMsgVo.getOutTradeNo());
        if(redisUtils.exists(Constants.PAY_MID_PRE + "_"+tradeLogDO.getMid())){
            redisUtils.del(Constants.PAY_MID_PRE + "_"+tradeLogDO.getMid());
        }

        if("SUCCESS".equals(merchantMsgVo.getStatus().getValue())){
            String tradeNo = merchantMsgVo.getOutTradeNo();
            //校验重复性
            if(redisUtils.incr(tradeNo,1) == 2){
                redisUtils.remove(tradeNo);
                PayNotifyResult payNotifyResult = new PayNotifyResult();
                payNotifyResult.setAmount(tradeLogDO.getAmount());
                payNotifyResult.setFactAmount(AmountUtil.changeY2F(merchantMsgVo.getReceiveAmountGac().longValue()));
                payNotifyResult.setTradeNo(tradeNo);
                payNotifyResult.setTradeOutNo(merchantMsgVo.getTradeNo());
                MemberDO memberDO = tradeLogService.paySucess(payNotifyResult);
                authManager.refeshMemberInfo(memberDO);
                redisUtils.remove(tradeNo);
                BaseResponse baseResponse = new BaseResponse(200, "操作成功");
                return baseResponse;
            }else{
                return error;
            }

        }else{
            return error;
        }
    }





    /**
     * 支付回调
     * @return
     */
    @AuthIgnore
    @RequestMapping(value = "/notify")
    @ResponseBody public String ustdNotifyPay(HttpServletRequest request) {

        String memberid=request.getParameter("memberid");
        String orderid=request.getParameter("orderid");
        String amount=request.getParameter("amount");
        String datetime=request.getParameter("datetime");
        String returncode=request.getParameter("returncode");
        String transaction_id = request.getParameter("transaction_id");
        String sign=request.getParameter("sign");
        String keyValue= mdKey;
        String SignTemp="amount="+amount+"&datetime="+datetime+"&memberid="+memberid+"&orderid="+orderid+"&returncode="+returncode+"&transaction_id="+transaction_id+"&key="+keyValue+"";

        String md5sign=md5Ustd(SignTemp);//MD5加密

        if(!md5sign.equals(sign)){
            return "验签失败！";
        }
        TradeLogDO tradeLogDO = tradeLogService.getTradeLogByNo(orderid);
        if(redisUtils.exists(Constants.PAY_MID_PRE + "_"+tradeLogDO.getMid())){
            redisUtils.del(Constants.PAY_MID_PRE + "_"+tradeLogDO.getMid());
        }

        if("00".equals(returncode)){
            String tradeNo = orderid;
            //校验重复性
            if(redisUtils.incr(tradeNo,1) == 2){
                redisUtils.remove(tradeNo);
                PayNotifyResult payNotifyResult = new PayNotifyResult();
                payNotifyResult.setAmount(tradeLogDO.getAmount());
                payNotifyResult.setFactAmount(AmountUtil.changeY2F(amount));
                payNotifyResult.setTradeNo(tradeNo);
                payNotifyResult.setTradeOutNo(tradeNo);
                MemberDO memberDO = tradeLogService.paySucess(payNotifyResult);
                authManager.refeshMemberInfo(memberDO);
                redisUtils.remove(tradeNo);
                return "ok";
            }else{
                return "支付失败";
            }
        }else{
            return "支付失败";
        }
    }



    /**
     * 支付回调
     * @return
     */
    @AuthIgnore
    @RequestMapping(value = "/notifybackup")
    public Result notifybackup(HttpServletRequest request){
        //获取充值编号
        String tradeNo = request.getParameter("tradeNo");
        //外部订单编号
        String tradeOutNo = request.getParameter("tradeOutNo");
        String factAmount = request.getParameter("factAmount");

        String amount = request.getParameter("amount");
        //是否需要将amount转化分


        //校验重复性
        if(redisUtils.incr(tradeNo,1) == 2){
            redisUtils.remove(tradeNo);
            PayNotifyResult payNotifyResult = new PayNotifyResult();
            payNotifyResult.setAmount(amount);
            payNotifyResult.setFactAmount(factAmount);
            payNotifyResult.setTradeNo(tradeNo);
            payNotifyResult.setTradeOutNo(tradeOutNo);
            MemberDO memberDO = tradeLogService.paySucess(payNotifyResult);
            authManager.refeshMemberInfo(memberDO);
            redisUtils.remove(tradeNo);

        }else{
            Result.error("参数错误");
        }

        return Result.ok("充值成功");
    }




    private JSONObject generatorPayInfo(String payInfo){

        /**
         * {"tradeStatus":11,
         * "tradeId":"qsnr4qtv3xa",
         * "createTime":"2019-11-14 17:02:42",
         * "amountGac":998700,
         * "fundPrice":1.0,
         * "amountRmb":998700,
         * "acptName":"zhi12345",
         * "qacptName":"zhi12345",
         * "fundCharge":15000,
         * "cardInfo":{"cardType":"BANK_CARD","cardUq":"6217 9336 7500 3402","bankName":"\u6D66\u53D1\u94F6\u884C","bankSubName":"\u6CC9\u5DDE\u5E02\u4E30\u6CFD\u533A\u5206\u884C","img":"https:\/\/otc.gac.top\/v3\/api\/image\/","bankAccName":"\u8521\u4E91\u8FDB","bankShort":"","cardId":"d5053fef2a86b9f889e460a9341bb4de","alipayPid":""},
         * "payReferNo":"","payOvertime":"2019-11-14 17:12:42","acptBzzGac":0,"fastFlag":0,"appendInfo":"","paramCardType":"BANK_CARD","historySuccCount":0,"matchFailReason":"","afterTradeUq":"","dischargeTime":"","cancelTime":"","cancelType":0,"realTradeId":null,"merchantAccount":"jyfd2aloao4","fastSubName":"3384582","userInputRmb":0,"reboot":null}
         */
        if(payInfo == null || payInfo.length() == 0){
            return null;
        }

        JSONObject payResult  = JSONObject.parseObject(payInfo);
        JSONObject result = new JSONObject();
        if(payResult != null){
            JSONObject cardInfo = payResult.getJSONObject("cardInfo");
            if(cardInfo != null){
                cardInfo.put("cardId",cardInfo.getString("cardUq"));
                result.put("cardInfo",cardInfo);
                result.put("amountRmb",AmountUtil.changeF2Y((payResult.getInteger("amountRmb")/100)+""));
                result.put("oldPay","false");
            }

        }


        return result;
    }




    /**
     * 支付回调
     * @return
     */
    @AuthIgnore
    @RequestMapping(value = "/withdrawNotify")
    @ResponseBody public String withdrawNotify(HttpServletRequest request) {

        String memberid=request.getParameter("memberid");
        String orderid=request.getParameter("orderid");
        String amount=request.getParameter("amount");
        String datetime=request.getParameter("datetime");
        String returncode=request.getParameter("returncode");
        String transactionId=request.getParameter("transaction_id");

        String sign=request.getParameter("sign");
        String keyValue=mdKey;
        String SignTemp="amount="+amount+"&datetime="+datetime+"&memberid="+memberid+"&orderid="+orderid+"&returncode="+returncode+"&transaction_id="+transactionId+"&key="+keyValue+"";

        String md5sign=md5Ustd(SignTemp);//MD5加密

        if(!md5sign.equals(sign)){
            return "验签失败！";
        }
        TradeLogDO tradeLogDO = tradeLogService.getTradeLogByNo(orderid);


        if("00".equals(returncode)){
            String tradeNo = orderid;
            logger.info("提款回调成功+：：：：：：：：orderid"+orderid + "************"+transactionId);
            return "ok";
        }else{
            return "支付失败";
        }
    }





    public static void main(String[] args) throws Exception{

        String params = "&amount=70.72135785&appid=7UHoEzKSxRFySnRy&orderid=20200520164609983412&rmb=500.00&status=1&sign=50D1DA30A8A093A93D7E952065081551D7AFC971";
        Map<String,String> temp = urlSplit(params);
        Map<String, String> params123 = new TreeMap<>();
        String orderid = temp.get("orderid");
        String amount = temp.get("amount");
        String appid = "7UHoEzKSxRFySnRy";
        String rmb = temp.get("rmb");
        String status = temp.get("status");
        String sign = temp.get("sign");

        params123.put("orderid",orderid);
        params123.put("amount",amount);
        params123.put("rmb",rmb);
        params123.put("status",status);
        params123.put("appid",appid);

        StringBuffer sb = new StringBuffer();
        for (String k : params123.keySet()) {
            String value = params123.get(k);
            if (null != value && value.length() > 0) {
                sb.append(k);
                sb.append(value);
            }
        }
        sb.append("23d0ad626dcd6a1e83ba230f82c02740");

        String tempsign = "";
        try {
            tempsign = sha1(sb.toString());

           System.out.println(tempsign);
        } catch (Exception e) {
            System.err.println("签名异常: " + e);
        }
        /*

        String codeResult = null;
        try{
           String result =  Jsoup.connect("http://39.105.26.145:7862/sms?action=send")
                    .ignoreContentType(true)
                    .method(Method.POST)
                    .data(params)
                    .execute()
                    .body();
            logger.info("send sm code "+result);
        }catch (Exception e){
            e.printStackTrace();
        }

         */
    }


    public static String encrypt(String dataStr) {
        try {

            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static boolean isNumber(String str){
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }


    public static String md5Ustd(String str){
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

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/upaynotify")
    @ResponseBody
    public String notify(String orderNo, BigDecimal account, String  accountType, String currencyType, String signType, String sign, String transactionId, String status) {
        // 验证签名
        if (TradeUtil.validate(orderNo, account, accountType, currencyType, signType, sign, transactionId, status) && status!= null && status.equals("success")) {
            // 实现业务
            TradeLogDO tradeLogDO = tradeLogService.getTradeLogByNo(orderNo);
            if (redisUtils.exists(Constants.PAY_MID_PRE + "_" + tradeLogDO.getMid())) {
                redisUtils.del(Constants.PAY_MID_PRE + "_" + tradeLogDO.getMid());
            }
            String tradeNo = orderNo;
            //校验重复性
            if (redisUtils.incr(tradeNo, 1) == 2) {
                redisUtils.remove(tradeNo);
                PayNotifyResult payNotifyResult = new PayNotifyResult();
                payNotifyResult.setAmount(tradeLogDO.getAmount());
                payNotifyResult.setFactAmount(AmountUtil.changeY2F(account.toString()));
                payNotifyResult.setTradeNo(tradeNo);
                payNotifyResult.setTradeOutNo(tradeNo);
                MemberDO memberDO = tradeLogService.paySucess(payNotifyResult);
                authManager.refeshMemberInfo(memberDO);
                redisUtils.remove(tradeNo);
            }

        }
        return "success";
    }



    @AuthIgnore
    @RequestMapping("/otcNotify")
    @ResponseBody
    public String otcNotify(String memberid, String orderid, String  amount, String transaction_id, String datetime, String returncode, String attach, String sign) {


        String SignTemp="amount="+amount+"&datetime="+datetime+"&memberid="+memberid+"&orderid="+orderid+"&returncode="+returncode+"&transaction_id="+transaction_id+"&key=md3wepjngrhsj54fmvyhkglwacat64v7";
        logger.info("calllback : "+SignTemp + " sing:"+sign);
        String md5sign=md5(SignTemp);//MD5加密

        if (sign.equals(md5sign)){
            if(returncode.equals("00")){
                // 实现业务
                TradeLogDO tradeLogDO = tradeLogService.getTradeLogByNo(orderid);
                if (redisUtils.exists(Constants.PAY_MID_PRE + "_" + tradeLogDO.getMid())) {
                    redisUtils.del(Constants.PAY_MID_PRE + "_" + tradeLogDO.getMid());
                }
                String tradeNo = orderid;
                //校验重复性
                if (redisUtils.incr(tradeNo, 1) == 2) {
                    PayNotifyResult payNotifyResult = new PayNotifyResult();
                    payNotifyResult.setAmount(tradeLogDO.getAmount());
                    payNotifyResult.setFactAmount(AmountUtil.changeY2F(amount.toString()));
                    payNotifyResult.setTradeNo(tradeNo);
                    payNotifyResult.setTradeOutNo(tradeNo);
                    MemberDO memberDO = tradeLogService.paySucess(payNotifyResult);
                    authManager.refeshMemberInfo(memberDO);
                    redisUtils.remove(tradeNo);
                }
                return "OK";
            }else{
                return "支付失败";
            }
        }

       return "验签失败";
    }



    //回调
    //todo  接收请求http数据
    /*
        数据类型
        {
            "amount": 100.0000,
            "orderid": "TP056165",
            "appid": "15AS4D644534",
            "status": 1,
            "sign": "6546413A8S4D3AF3A4F38A4"
        }
     */
    @AuthIgnore
    @RequestMapping(value="/standarOtcNotify",produces = "application/json")
    @ResponseBody
    public String standarOtcNotify(@RequestBody String callbackPayInfo){
        String key = "23d0ad626dcd6a1e83ba230f82c02740";
        logger.info("standarOtcNotify====" +callbackPayInfo);
        Map<String,String> temp = urlSplit(callbackPayInfo);

        Map<String, String> params123 = new TreeMap<>();
        String orderid = temp.get("orderid");
        String amount = temp.get("amount");
        String appid = "7UHoEzKSxRFySnRy";
        String rmb = temp.get("rmb");
        String status = temp.get("status");
        String oriSign = temp.get("sign");

        params123.put("orderid",orderid);
        params123.put("amount",amount);
        params123.put("rmb",rmb);
        params123.put("status",status);
        params123.put("appid",appid);

        StringBuffer sb = new StringBuffer();
        for (String k : params123.keySet()) {
            String value = params123.get(k);
            if (null != value && value.length() > 0) {
                sb.append(k);
                sb.append(value);
            }
        }
        sb.append(key);
        String sign = "";
        try {
            sign = sha1(sb.toString());
            System.out.println("签名: " + sign);
        } catch (Exception e) {
            System.err.println("签名异常: " + e);
            return "fail";
        }
        //-------------------------------------------------
        if(sign.equals(oriSign)){
            // 实现业务
            TradeLogDO tradeLogDO = tradeLogService.getTradeLogByNo(orderid);
            if (redisUtils.exists(Constants.PAY_MID_PRE + "_" + tradeLogDO.getMid())) {
                redisUtils.del(Constants.PAY_MID_PRE + "_" + tradeLogDO.getMid());
            }
            String tradeNo = orderid;
            //校验重复性
            if (redisUtils.incr(tradeNo, 1) == 2) {
                redisUtils.remove(tradeNo);
                PayNotifyResult payNotifyResult = new PayNotifyResult();
                payNotifyResult.setAmount(tradeLogDO.getAmount());
                payNotifyResult.setFactAmount(AmountUtil.changeY2F(rmb));
                payNotifyResult.setTradeNo(tradeNo);
                payNotifyResult.setTradeOutNo(tradeNo);
                MemberDO memberDO = tradeLogService.paySucess(payNotifyResult);
                authManager.refeshMemberInfo(memberDO);
            }

            return "success";
        }else{
            //验签失败
            System.out.println("验签失败");
            return "fail";
        }
    }


    public static String sha1(String inStr) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString().toUpperCase();
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

    public static Map<String, String> urlSplit( String strUrlParam){
        Map<String, String> mapRequest = new HashMap<String, String>();
        String[] arrSplit=null;
        if(strUrlParam==null){
            return mapRequest;
        }
        arrSplit=strUrlParam.split("[&]");
        for(String strSplit:arrSplit){
            String[] arrSplitEqual=null;
            arrSplitEqual= strSplit.split("[=]");
//解析出键值
            if(arrSplitEqual.length>1){
//正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            }else{
                if(arrSplitEqual[0]!=""){
//只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }


}
