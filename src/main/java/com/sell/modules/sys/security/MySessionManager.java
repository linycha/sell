package com.sell.modules.sys.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
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
@Slf4j
public class MySessionManager extends DefaultWebSessionManager {

    private static final String AUTHORIZATION = "token";

    public MySessionManager(){
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response){
        //从请求头获取token
        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        if(token != null){
            log.info("token准备中,传过来的token值为："+token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return token;
        }else{
            log.info("请求头里的token不能为空！");
            return super.getSessionId(request,response);
        }

    }

    /**
     * 去掉没有访问权限时跳转的url上的jSessionId
     * @param session
     * @param context
     */
    @Override
    protected void onStart(Session session, SessionContext context) {
        super.onStart(session, context);
        ServletRequest request = WebUtils.getRequest(context);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
    }
}
