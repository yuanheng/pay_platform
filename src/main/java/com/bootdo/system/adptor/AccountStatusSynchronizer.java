package com.bootdo.system.adptor;

import com.bootdo.app.config.Constants;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.app.zwlenum.PayTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 收款方式状态同步
 *
 * @author Prometheus
 * @date 2021/8/22.
 */
@Component
public class AccountStatusSynchronizer {

    private static final String WECHAT_PAY_KEY = Constants.PAYMENTINFO_LIST
            .replace("{payType}", PayTypeEnum.WECHAT_CODE.getKey());
    private static final String ALIPAY_KEY = Constants.PAYMENTINFO_LIST
            .replace("{payType}", PayTypeEnum.APLIPAY_CODE.getKey());
    private static final String BANKPAY_KEY = Constants.PAYMENTINFO_LIST
            .replace("{payType}", PayTypeEnum.BANK_CODE.getKey());

    private RedisUtils redisUtil;

    @Autowired
    public void setRedisUtil(RedisUtils redisUtil) {
        this.redisUtil = redisUtil;
    }

    public boolean sync(PayTypeEnum payTypeEnum, final Long id, Object o) {
        switch (payTypeEnum) {
            case BANK_CODE:
                redisUtil.addPaymentInfo(String.format("%s_%s", BANKPAY_KEY, id), o);
                return true;
            case APLIPAY_CODE:
                redisUtil.addPaymentInfo(String.format("%s_%s", ALIPAY_KEY, id), o);
                return true;
            case WECHAT_CODE:
                redisUtil.addPaymentInfo(String.format("%s_%s", WECHAT_PAY_KEY, id), o);
                return true;
        }
        return false;
    }

    public boolean remove(PayTypeEnum payTypeEnum, final Long id) {
        switch (payTypeEnum) {
            case BANK_CODE:
                redisUtil.del(String.format("%s_%s", BANKPAY_KEY, id));
                return true;
            case APLIPAY_CODE:
                redisUtil.del(String.format("%s_%s", ALIPAY_KEY, id));
                return true;
            case WECHAT_CODE:
                redisUtil.del(String.format("%s_%s", WECHAT_PAY_KEY, id));
                return true;
        }
        return false;
    }

}
