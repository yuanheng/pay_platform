package com.bootdo.app.service.impl;

import com.bootdo.app.config.Constants;
import com.bootdo.app.global.AuthException;
import com.bootdo.app.service.TokenManager;
import com.bootdo.system.domain.AgreementDO;
import com.bootdo.system.domain.MemberDO;
import com.bootdo.system.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthManager {

    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private MemberService memberService;


    /**
     * 获取请求体
     * @return
     */
    public HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 登录
     * @param memberDO
     * @return
     */
    public String signIn(MemberDO memberDO){
        return tokenManager.getToken(memberDO);
    }

    /**
     * 获取该访问用户信息
     * @return
     */
    public MemberDO getUserInfo(){
        HttpServletRequest request=getRequest();
        String token=request.getHeader(Constants.USER_TOKEN);
        MemberDO userInfo=tokenManager.getUserInfoByToken(token);
        if(userInfo==null){
            try {
                throw new AuthException("该用户已过期", HttpStatus.UNAUTHORIZED.value());
            } catch (AuthException e) {
                e.printStackTrace();
            }
        }
        return userInfo;
    }


    /**
     * 注销该访问用户
     */
    public void loginOff(MemberDO memberDO){
        tokenManager.loginOff(memberDO);
    }

    public void refeshMemberInfo(){
        HttpServletRequest request=getRequest();
        String token=request.getHeader(Constants.USER_TOKEN);
        MemberDO userInfo=tokenManager.getUserInfoByToken(token);
        MemberDO temp = memberService.get(userInfo.getId());
        tokenManager.refreshMemberInfo(token,temp);
    }

    public void refeshMemberInfo(MemberDO memberDO){
        tokenManager.refreshMemberByMid(memberDO);
    }

}
