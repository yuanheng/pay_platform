package com.bootdo.app.zwlenum;

/**
 * PayTypeEnum
 *
 * @author Sylow
 * @version v1.0, 2016年7月6日
 * @since v6.1
 */
public enum PayTypeEnum {
    //充值
    WECHAT_CODE("wechat", "微信个码"),
    APLIPAY_CODE("alipay", "支付宝个码"),
    BANK_CODE("bank", "银行卡");

    PayTypeEnum(String key, String desc) {
        this.key = key;
        this.typeDesc = desc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public static PayTypeEnum getByKey(String key) {
        if (WECHAT_CODE.key.equals(key)) {
            return WECHAT_CODE;
        } else if (APLIPAY_CODE.key.equals(key)) {
            return APLIPAY_CODE;
        } else if (BANK_CODE.key.equals(key)) {
            return BANK_CODE;
        } else {
            return null;
        }
    }

    public static String getTypeDescByType(String type) {
        return PayTypeEnum.valueOf(type).typeDesc;
    }

    @Override
    public String toString() {
        return this.key;
    }

    private String key;
    private String typeDesc;

}
