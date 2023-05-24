package com.sell.modules.sys.controller;

import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.common.ResponseCode;
import com.sell.common.utils.UserUtils;
import com.sell.modules.sys.service.UserService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linyuc
 * @date 2020/1/15 19:26
 */
@RestController
@RequestMapping
@Api(tags = "登录相关接口")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("login")
    public Res<Map<String,Object>> login(String username, String password, String role){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> info = new HashMap<>();
        try {
            subject.login(token);
            if(!UserUtils.getRole().equals(role)){
                subject.logout();
                return Res.errorMsg("该账号角色与所登录角色不符！");
            }
/*            //校验是否是商家账号
            if(Const.USER_ROLE_ADMIN.equals(UserUtils.getRole()) || Const.USER_ROLE_BUSINESS.equals(UserUtils.getRole())){
                subject.logout();
                return Res.errorMsg("该账号不是普通用户/骑手账号");
            }*/
            info.put("token",subject.getSession().getId());
            info.put("userId",UserUtils.getUserId());
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
     * 商家登录接口
     */
    @GetMapping("business_login")
    public Res<Map<String,Object>> shopLogin(String username, String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> info = new HashMap<>(10);
        try {
            subject.login(token);
            //校验是否是商家账号
            if(Const.USER_ROLE_DELIVERY.equals(UserUtils.getRole()) || Const.USER_ROLE_CUSTOMER.equals(UserUtils.getRole())){
                subject.logout();
                return Res.errorMsg("该账号不是商家/管理员账号");
            }
            info.put("token",subject.getSession().getId());
            info.put("shopId",UserUtils.getShopId());
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
     * 骑手登录接口
     */
    @GetMapping("delivery_login")
    public Res<Map<String,Object>> deliveryLogin(String username, String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> info = new HashMap<>(10);
        try {
            subject.login(token);
            //校验是否是骑手账号
            if(!Const.USER_ROLE_DELIVERY.equals(UserUtils.getRole())){
                subject.logout();
                return Res.errorMsg("该账号不是骑手账号");
            }
            info.put("token",subject.getSession().getId());
            info.put("userId",UserUtils.getUser().getId());
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
    public Res<Integer> register(String username, String mobile, String password){
        return userService.insertRegister(username,mobile,password,1);
    }

    @GetMapping("/to_login")
    public Res<String> toLogin(){
        return Res.errorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),"请先登录账号");
    }
    @GetMapping("/unauthc")
    public Res<String> unAuthor(){
        return Res.errorCodeMsg(403,"当前用户没有操作权限");
    }

    @GetMapping("/logout")
    public Res<String> logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Res.successMsg("退出成功");
    }
}
