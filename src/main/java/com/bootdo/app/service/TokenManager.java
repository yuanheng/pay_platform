package com.bootdo.app.service;

import com.bootdo.system.domain.AgreementDO;
import com.bootdo.system.domain.MemberDO;

public interface TokenManager {
    /**
     * 创建token
     * @param memberDO
     * @return
     */
    String getToken(MemberDO memberDO);

    /**
     * 刷新用户
     * @param token
     */
    void refreshUserToken(String token);

    /**
     * 用户退出登陆
     * @param memberDO
     */
    void loginOff(MemberDO memberDO);

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    MemberDO getUserInfoByToken(String token);


    /**
     * 刷新用户信息
     */
    void refreshMemberInfo(String token,MemberDO memberDO);

    /**
     * 保存用户id和token的关系
     */
    void refreshMemberByMid(MemberDO memberDO);


    /**
     * 获取用户的智能合约
     */
    AgreementDO getMemberAgreement(String agreementKey);

    void setAgreement(String agreementKey, AgreementDO agreementDO,Long activeTime);

    void removeAgreement(String agreementKey);
}
