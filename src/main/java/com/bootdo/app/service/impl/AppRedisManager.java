package com.bootdo.app.service.impl;

import com.bootdo.app.config.Constants;
import com.bootdo.app.service.TokenManager;
import com.bootdo.app.util.RedisUtils;
import com.bootdo.system.domain.AgreementDO;
import com.bootdo.system.domain.MemberDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class AppRedisManager implements TokenManager {

    @Autowired
    private RedisUtils redisUtils;


    /**
     * 创建token
     * @param userInfo
     * @return
     */
    @Override
    public String getToken(MemberDO userInfo){
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        String token_format=token.replaceAll("-","");
        userInfo.setPassword(null);

        String midTokenKey = Constants.MID_TOKEN_KEY+"_"+userInfo.getId();
        //判断当前用户是否登录
        if(redisUtils.exists(midTokenKey)){
            String oldToken = (String) redisUtils.get(midTokenKey);
            redisUtils.del(oldToken);
        }
        redisUtils.set(token_format,userInfo,Constants.ACTIVE_TIME);
        redisUtils.set(midTokenKey,token_format,Constants.ACTIVE_TIME);

        return token;
    }

    /**
     * 刷新用户
     * @param token
     */
    @Override
    public void refreshUserToken(String token){

        if(redisUtils.exists(token)){
            MemberDO memberDO = getUserInfoByToken(token);
            redisUtils.setExpireTime(Constants.MID_TOKEN_KEY+"_"+memberDO.getId(),Constants.ACTIVE_TIME);
            redisUtils.setExpireTime(token, Constants.ACTIVE_TIME);
        }
    }

    /**
     * 用户退出登陆
     * @param memberDO
     */
    @Override
    public void loginOff(MemberDO memberDO){

        String memberToken = (String) redisUtils.get(Constants.MID_TOKEN_KEY+"_"+memberDO.getId());

        if(memberToken!= null &&redisUtils.exists(memberToken)){
            redisUtils.del(memberToken);
            redisUtils.del(Constants.MID_TOKEN_KEY+"_"+memberDO.getId());
        }


    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @Override
    public MemberDO getUserInfoByToken(String token){

        if(redisUtils.exists(token)){
            return (MemberDO) redisUtils.get(token);
        }
        return null;
    }

    @Override
    public void refreshMemberInfo(String token,MemberDO memberDO) {
        redisUtils.set(Constants.MID_TOKEN_KEY+"_"+memberDO.getId(),token,Constants.ACTIVE_TIME);
        redisUtils.set(token,memberDO,Constants.ACTIVE_TIME);
    }

    @Override
    public void refreshMemberByMid(MemberDO memberDO) {
        String token = (String) redisUtils.get(Constants.MID_TOKEN_KEY+"_"+memberDO.getId());
        if(token != null){
            redisUtils.set(token,memberDO,Constants.ACTIVE_TIME);
        }


    }

    @Override
    public AgreementDO getMemberAgreement(String agreementKey) {
        if(redisUtils.exists(agreementKey)){
            return (AgreementDO) redisUtils.get(agreementKey);
        }
        return null;
    }

    @Override
    public void setAgreement(String agreementKey, AgreementDO agreementDO,Long activeTime) {
        redisUtils.set(agreementKey,agreementDO,activeTime);
    }

    @Override
    public void removeAgreement(String agreementKey) {
        if(redisUtils.exists(agreementKey)){
            redisUtils.del(agreementKey);
        }
    }


}
