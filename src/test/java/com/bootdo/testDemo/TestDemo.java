package com.bootdo.testDemo;

import com.bootdo.app.config.Constants;
import com.bootdo.app.util.Encript;
import com.bootdo.app.util.RedisUtils;

import com.bootdo.app.zwlenum.PayTypeEnum;
import com.bootdo.app.zwlenum.StatusEnum;
import com.bootdo.system.adptor.TbOrderStatusCensor;
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
    public void testPayInfos() throws Exception {
        String wechatPayKey = Constants.PAYMENTINFO_LIST.replace("{payType}", PayTypeEnum.WECHAT_CODE.getKey());
        String alipayKey = Constants.PAYMENTINFO_LIST.replace("{payType}", PayTypeEnum.APLIPAY_CODE.getKey());
        String bankPayKey = Constants.PAYMENTINFO_LIST.replace("{payType}", PayTypeEnum.BANK_CODE.getKey());
        redisUtils.del(wechatPayKey);
        redisUtils.del(alipayKey);
        redisUtils.del(bankPayKey);
        //添加微信收款码
        for (int i = 0; i < 1000; i++) {
            PayWechatInfoDO payWechatInfoDO = new PayWechatInfoDO();
            payWechatInfoDO.setName("丁三" + i);
            payWechatInfoDO.setAccount("dingbs" + i);
            payWechatInfoDO.setStatus(StatusEnum.ENABLE.getKey());
            payWechatInfoDO.setImgUrl("http://140.143.244.246:6868/img/wechat.jpg");
            payWechatInfoDO.setMid(137L);
            redisUtils.addPaymentInfo(wechatPayKey, payWechatInfoDO);
        }

        for (int i = 0; i < 1000; i++) {
            PayAlipayInfoDO payWechatInfoDO = new PayAlipayInfoDO();
            payWechatInfoDO.setName("支付宝" + i);
            payWechatInfoDO.setAccount("zhifubao" + i);
            payWechatInfoDO.setStatus(StatusEnum.ENABLE.getKey());
            payWechatInfoDO.setMid(137L);
            payWechatInfoDO.setImgUrl("http://140.143.244.246:6868/img/wechat.jpg");
            redisUtils.addPaymentInfo(alipayKey, payWechatInfoDO);
        }

        for (int i = 0; i < 1000; i++) {
            BankInfoDO payWechatInfoDO = new BankInfoDO();
            payWechatInfoDO.setName("银行" + i);
            payWechatInfoDO.setStatus(StatusEnum.ENABLE.getKey());
            payWechatInfoDO.setMid(137L);
            payWechatInfoDO.setAccount("622588014634687" + i);
            payWechatInfoDO.setAddress("北京市朝去东大街");
            payWechatInfoDO.setBankName("招商银行");
            payWechatInfoDO.setBranchBankName("首体支行");
            redisUtils.addPaymentInfo(bankPayKey, payWechatInfoDO);
        }


    }

    @Test
    public void testInitData() {


        String wechatPayKey = Constants.PAYMENTINFO_LIST.replace("{payType}", PayTypeEnum.WECHAT_CODE.getKey());
        String alipayKey = Constants.PAYMENTINFO_LIST.replace("{payType}", PayTypeEnum.APLIPAY_CODE.getKey());
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
        redisUtils.set(Constants.ORDER_PAY_URL, host + "/api/pay");
        redisUtils.set(Constants.ORDER_TIMER_KEY, 1 * 60 * 10);
    }

    @Test
    public void md5() {
        System.out.println(Encript.md5("admin"));
    }

    @Test
    public void testCensorPayStatus() {
        final String url = "https://mclient.alipay.com/h5/peerpay.htm?biz_no=2021092404200338381084397980_0f3c48f78389ffc61e286af6dc353654&app_name=tb&sc=qr_code&v=20211001&sign=8839e4&__webview_options__=pd%3dNO";

        // 正常cookie
//        final String cookie = "JSESSIONID=RZ41H4AwdIoVdnVcpWEODW2rT5oq67mobileclientgwRZ41; zone=RZ41A; ALIPAYJSESSIONID=RZ416ZnsdFOSX7GtcXUfYXadtzHkBU18mobilegwRZ41; rtk=Iqe5CE9xgJehNohtvWkmdn93AsztWwszKVcwKkbEAqtjPsaVH0w; JSESSIONID=6250A5DA1A5DCD670A067DAF4C50A3FA; spanner=fDRzTonpeEqySTQ75QPIvNxC4yFPz3rjXt2T4qEYgj0=; awid=RZ41H4AwdIoVdnVcpWEODW2rT5oq67mobileclientgwRZ41; ctoken=kUhf1R9XoEkPYt61";

        // 异常cookie，测试用
        final String cookie = "JSESSIONID=RZ41H4AwdIoVdnVcpWxxEODW2rT5oq67mobileclientgwRZ41; zone=RZ41A; ALIPAYJSESSIONID=RZ416ZnsdFOSX7GtcXUfYXxxxadtzHkBU18mobilegwRZ41; rtk=Iqe5CE9xgJehNohtvWkmdn93AsztWwszKVcwKxxkbEAqtjPsaVH0w; JSESSIONID=6250A5DA1xxA5DCD670A067DAF4C50A3FA; spanner=fDRzTonpeEqySTQ75QPIvNxC4yFPz3rjXt2T4qEYgj0=; awid=RZ41H4AwdIoVdnVcpWEODW2rTx5oq67mobileclientgwRZ41; ctoken=kUhf1R9XxoEkPYt61";

        System.out.println(new TbOrderStatusCensor(url, cookie).renderStatus());
    }

}
