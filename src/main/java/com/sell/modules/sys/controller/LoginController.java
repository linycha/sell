package com.sell.modules.sys.controller;

import com.sell.common.Res;
import com.sell.common.utils.UserUtils;
import com.sell.modules.store.service.ShopService;
import com.sell.modules.sys.entity.Role;
import com.sell.modules.sys.entity.User;
import com.sell.modules.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
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
        }catch(UnknownAccountException | IncorrectCredentialsException e){
            e.printStackTrace();
            return Res.errorMsg("用户名或密码错误");
        } catch (LockedAccountException e){
            e.printStackTrace();
            return Res.errorMsg("该账号已被禁用，请联系管理员");
        }catch (AuthenticationException e){
            e.printStackTrace();
            return Res.errorMsg("登录异常，请联系管理员");
        }
    }

    /**
     * 商家登录
     */
    @GetMapping("business_login")
    public Res<Map<String,Object>> shopLogin(String username, String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> info = new HashMap<>(10);
        try {
            subject.login(token);
            Role role = userService.getRoleName(UserUtils.getUserId());
            //校验是否是商家账号
            if(!"business".equals(role.getName())){
                subject.logout();
                return Res.errorMsg("该账号不是商家账号");
            }
            String shopId = shopService.getshopId(UserUtils.getUserId());
            info.put("token",subject.getSession().getId());
            info.put("shopId",shopId);
            return Res.success("登录成功", info);
        }catch(UnknownAccountException | IncorrectCredentialsException e){
            e.printStackTrace();
            return Res.errorMsg("用户名或密码错误");
        } catch (LockedAccountException e){
            e.printStackTrace();
            return Res.errorMsg("该账号已被禁用，请联系管理员");
        }catch (AuthenticationException e){
            e.printStackTrace();
            return Res.errorMsg("登录异常，请联系管理员");
        }
    }
    /**
     * 骑手登录
     */
    @GetMapping("delivery_login")
    public Res<Map<String,Object>> deliveryLogin(String username, String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> info = new HashMap<>(10);
        try {
            subject.login(token);
            String userId = UserUtils.getUserId();
            Role role = userService.getRoleName(userId);
            //校验是否是骑手账号
            if(!"delivery".equals(role.getName())){
                subject.logout();
                return Res.errorMsg("该账号不是骑手账号");
            }
            info.put("token",subject.getSession().getId());
            info.put("userId",userId);
            return Res.success("登录成功", info);
        }catch(UnknownAccountException | IncorrectCredentialsException e){
            e.printStackTrace();
            return Res.errorMsg("用户名或密码错误");
        } catch (LockedAccountException e){
            e.printStackTrace();
            return Res.errorMsg("该账号已被禁用，请联系管理员");
        }catch (AuthenticationException e){
            e.printStackTrace();
            return Res.errorMsg("登录异常，请联系管理员");
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
