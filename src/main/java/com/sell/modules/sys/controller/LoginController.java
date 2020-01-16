package com.sell.modules.sys.controller;

import com.sell.common.Res;
import com.sell.modules.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/login")
    public Res<Map<String,Object>> login(String username, String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> info = new HashMap<>();
        try {
            subject.login(token);
            info.put("token",subject.getSession().getId());
            return Res.success("登录成功", info);
        }catch(Exception e){
            e.printStackTrace();
            //扩展错误几次之后要多久才能再次登录
            return Res.errorMsg("用户名或密码错误");
        }
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Res<String> register(String mobile,String password){

        return userService.register(mobile,password);
    }

    /**
     * forget password
     * @return
     */

    @RequestMapping("/to_login")
    public Res<String> toLogin(){
        return Res.errorCodeMsg(-2,"请先登录账号,或token已过期");
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
