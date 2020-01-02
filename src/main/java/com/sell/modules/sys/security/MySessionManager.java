package com.sell.modules.sys.security;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义sessionManager，服务端分配一个token，用户权限校验，
 * 登录成功存储一个token,每次访问接口就校验这个token
 * @author linyuc
 * @date 2020/1/1 21:18
 */
public class MySessionManager extends DefaultWebSessionManager {

    private static final String AUTHORIZATION = "token";

    public MySessionManager(){
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response){
        //从请求头获取token
        String sessionId = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        if(sessionId != null){
            System.out.println("token准备中");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionId;
        }else{
            System.out.println("sessionId is null");
            return null;
            //return super.getSessionId(request,response);
        }

    }
}
