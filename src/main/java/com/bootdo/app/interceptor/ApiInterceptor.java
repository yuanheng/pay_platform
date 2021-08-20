package com.bootdo.app.interceptor;

import com.bootdo.app.annotation.AuthIgnore;
import com.bootdo.app.config.Constants;
import com.bootdo.app.global.AuthException;
import com.bootdo.app.service.impl.AppRedisManager;
import com.bootdo.system.domain.MemberDO;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Create: 2018/9/14 15:11
 * Modified By:
 * Description:
 */
public class ApiInterceptor implements HandlerInterceptor {

    @Autowired
    AppRedisManager redisTokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuthIgnore annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthIgnore.class);
        }else{
            return true;
        }

        //如果有@AuthIgnore注解，则不验证token
        if(annotation != null){
            return true;
        }

        //获取用户凭证
        String token = request.getHeader(Constants.USER_TOKEN);
        if(StringUtils.isBlank(token)){
            token = request.getParameter(Constants.USER_TOKEN);
        }
        if(StringUtils.isBlank(token)){
            Object obj = request.getAttribute(Constants.USER_TOKEN);
            if(null!=obj){
                token=obj.toString();
            }
        }


        //token凭证为空
        if(StringUtils.isBlank(token)){
            throw new AuthException(Constants.USER_TOKEN + "不能为空",HttpStatus.OK.value());
        }

        if(StringUtils.isNotEmpty(token)){
            MemberDO userInfo  = redisTokenManager.getUserInfoByToken(token);
            if(userInfo == null){
                response.setStatus(HttpStatus.OK.value());
                throw new AuthException("登录过期", HttpStatus.OK.value());
            }else{
                redisTokenManager.refreshUserToken(token);
            }

        }

        return true;
    }


}