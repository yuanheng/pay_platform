package com.bootdo.testDemo;

import com.bootdo.app.config.Constants;
import com.bootdo.app.util.Encript;
import com.bootdo.app.util.RedisUtils;

import com.bootdo.app.zwlenum.PayTypeEnum;
import com.bootdo.app.zwlenum.StatusEnum;
import com.bootdo.system.domain.BankInfoDO;
import com.bootdo.system.domain.PayAlipayInfoDO;
import com.bootdo.system.domain.PayWechatInfoDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {

  @Autowired
  RedisUtils redisUtils;

  private static Logger logger = LoggerFactory.getLogger(TestDemo.class);

  @Test
  public void testPayInfos() throws Exception{
    String wechatPayKey = Constants.PAYMENTINFO_LIST.replace("{payType}", PayTypeEnum.WECHAT_CODE.getKey());
    String alipayKey =  Constants.PAYMENTINFO_LIST.replace("{payType}", PayTypeEnum.APLIPAY_CODE.getKey());
    String bankPayKey = Constants.PAYMENTINFO_LIST.replace("{payType}", PayTypeEnum.BANK_CODE.getKey());
    redisUtils.del(wechatPayKey);
    redisUtils.del(alipayKey);
    redisUtils.del(bankPayKey);
    //添加微信收款码
    for(int i = 0; i < 1000; i++) {
      PayWechatInfoDO payWechatInfoDO = new PayWechatInfoDO();
      payWechatInfoDO.setName("丁三"+i);
      payWechatInfoDO.setAccount("dingbs"+i);
      payWechatInfoDO.setStatus(StatusEnum.ENABLE.getKey());
      payWechatInfoDO.setImgUrl("http://140.143.244.246:6868/img/wechat.jpg");
      payWechatInfoDO.setMid(137L);
      redisUtils.addPaymentInfo(wechatPayKey,payWechatInfoDO);
    }

    for(int i = 0; i < 1000; i++) {
      PayAlipayInfoDO payWechatInfoDO = new PayAlipayInfoDO();
      payWechatInfoDO.setName("支付宝"+i);
      payWechatInfoDO.setAccount("zhifubao"+i);
      payWechatInfoDO.setStatus(StatusEnum.ENABLE.getKey());
      payWechatInfoDO.setMid(137L);
      payWechatInfoDO.setImgUrl("http://140.143.244.246:6868/img/wechat.jpg");
      redisUtils.addPaymentInfo(alipayKey,payWechatInfoDO);
    }

    for(int i = 0; i < 1000; i++) {
      BankInfoDO payWechatInfoDO = new BankInfoDO();
      payWechatInfoDO.setName("银行"+i);
      payWechatInfoDO.setStatus(StatusEnum.ENABLE.getKey());
      payWechatInfoDO.setMid(137L);
      payWechatInfoDO.setAccount("622588014634687"+i);
      payWechatInfoDO.setAddress("北京市朝去东大街");
      payWechatInfoDO.setBankName("招商银行");
      payWechatInfoDO.setBranchBankName("首体支行");
      redisUtils.addPaymentInfo(bankPayKey,payWechatInfoDO);
    }


  }

  @Test
  public void testInitData() {


    String wechatPayKey = Constants.PAYMENTINFO_LIST.replace("{payType}", PayTypeEnum.WECHAT_CODE.getKey());
    String alipayKey =  Constants.PAYMENTINFO_LIST.replace("{payType}", PayTypeEnum.APLIPAY_CODE.getKey());
    String bankPayKey = Constants.PAYMENTINFO_LIST.replace("{payType}", PayTypeEnum.BANK_CODE.getKey());
    redisUtils.del(wechatPayKey);
    redisUtils.del(alipayKey);

   /* //@TODO 必须初始化.
    redisUtils.set(Constants.ORDER_PAY_URL,"http://140.143.244.246:6868/api/pay");
    redisUtils.set(Constants.ORDER_TIMER_KEY,1 * 60 * 10);*/
  }

  @Test
  public void tesInitPro() {
    String host = "http://43.129.219.47:6868";
    redisUtils.set(Constants.ORDER_PAY_URL, host+"/api/pay");
    redisUtils.set(Constants.ORDER_TIMER_KEY,1 * 60 * 10);
  }
  @Test
  public void md5(){
    System.out.println(Encript.md5("admin"));
  }

}
