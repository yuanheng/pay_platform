package com.bootdo.system.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2021-08-20 21:24:35
 */
@NoArgsConstructor
@AllArgsConstructor
public class MerchantDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Long id;
    //商户号
    private String merchantNo;
    //用户id
    private Long mid;
    //秘钥
    private String secretKey;
    //回调接口
    private String callbackUrl;
    //支付成功url
    private String returnUrl;
    //创新时间
    private Date createTime;

    public MerchantDO(String merchantNo, String secretKey, String callbackUrl) {
        this.merchantNo = merchantNo;
        this.secretKey = secretKey;
        this.callbackUrl = callbackUrl;
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    /**
     * 设置：商户号
     */
    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    /**
     * 获取：商户号
     */
    public String getMerchantNo() {
        return merchantNo;
    }

    /**
     * 设置：秘钥
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * 获取：秘钥
     */
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * 设置：回调接口
     */
    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    /**
     * 获取：回调接口
     */
    public String getCallbackUrl() {
        return callbackUrl;
    }

    /**
     * 设置：支付成功url
     */
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    /**
     * 获取：支付成功url
     */
    public String getReturnUrl() {
        return returnUrl;
    }

    /**
     * 设置：创新时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创新时间
     */
    public Date getCreateTime() {
        return createTime;
    }
}
