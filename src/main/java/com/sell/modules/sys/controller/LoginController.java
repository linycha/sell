package com.sell.modules.sys.controller;

import com.sell.common.Res;
import com.sell.modules.store.service.ShopService;
import com.sell.modules.sys.entity.User;
import com.sell.modules.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linyuc
 * @date 2020/1/15 19:26
 */
@RestController
@RequestMapping
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;

    @GetMapping("login")
    public Res<Map<String,Object>> login(String username, String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> info = new HashMap<>();
        try {
            subject.login(token);
            Object o = SecurityUtils.getSubject().getPrincipal();
            info.put("token",subject.getSession().getId());
            info.put("userId",o.toString());
            return Res.success("登录成功", info);
        }catch(Exception e){
            e.printStackTrace();
            //扩展错误几次之后要多久才能再次登录
            return Res.errorMsg("用户名或密码错误");
        }
    }

    /**
     * 商家登录
     */
    @GetMapping("business_login")
    public Res<Map<String,Object>> shopLogin(String username, String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> info = new HashMap<>();
        try {
            subject.login(token);
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            //校验是否是商家账号
            if(!"business".equals(user.getRoles().get(0).getName())){
                subject.logout();
                return Res.errorMsg("该账号不是商家账号");
            }
            String shopId = shopService.getshopId(user.getId());
            info.put("token",subject.getSession().getId());
            info.put("shopId",shopId);
            return Res.success("登录成功", info);
        }catch(Exception e){
            e.printStackTrace();
            //扩展错误几次之后要多久才能再次登录
            return Res.errorMsg("用户名或密码错误");
        }
    }
    /**
     * 商家登录
     */
    @GetMapping("delivery_login")
    public Res<Map<String,Object>> deliveryLogin(String username, String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> info = new HashMap<>();
        try {
            subject.login(token);
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            //校验是否是骑手账号
            if(!"delivery".equals(user.getRoles().get(0).getName())){
                subject.logout();
                return Res.errorMsg("该账号不是骑手账号");
            }
            info.put("token",subject.getSession().getId());
            info.put("userId",user.getId());
            return Res.success("登录成功", info);
        }catch(Exception e){
            e.printStackTrace();
            //扩展错误几次之后要多久才能再次登录
            return Res.errorMsg("用户名或密码错误");
        }
    }

    @PostMapping("register")
    public Res<String> register(String username, String mobile, String password){
        return userService.register(username,mobile,password);
    }

    @RequestMapping("/to_login")
    public Res<String> toLogin(){
        return Res.errorCodeMsg(-2,"请先登录账号");
    }
    @RequestMapping("/unauthc")
    public Res<String> unAuthor(){
        return Res.errorCodeMsg(-3,"没有权限访问该页面");
    }

    @RequestMapping("/logout")
    public Res<String> logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Res.successMsg("退出成功");
    }
}
