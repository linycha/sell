package com.sell.modules.sys.controller;

import com.sell.common.ServerResponse;
import com.sell.modules.sys.entity.User;
import com.sell.modules.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linyuc
 * @date 2019/12/30 9:23
 */
@RestController
public class TestController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public ServerResponse login(){
        return ServerResponse.errorCodeMsg(-2,"请使用对应的账号登录");
    }
    @RequestMapping("/unauthc")
    public ServerResponse unAuthor(){
        return ServerResponse.errorCodeMsg(-3,"没有权限访问该页面 ");
    }
    @RequestMapping("/list")
    public ServerResponse author(){
        return ServerResponse.successMsg("列表");
    }
    @RequestMapping("test")
    public ServerResponse test(){
        return ServerResponse.successMsg("test");
    }
    @RequestMapping("/index")
    public Object index(){
        String username = "cloud";
        User user = userService.selectByUsername(username);
        if(user == null){
            return "null";
        }
        return user;
    }

    @RequestMapping("/loginUser")
    public ServerResponse loginUser(String username, String password, HttpSession session){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        Map<String,Object> info = new HashMap<>();
        System.out.println("login stat");
        try {
            subject.login(token);
            info.put("sessionId",subject.getSession().getId());
            return ServerResponse.success("登录成功", info);
        }catch(Exception e){
            e.printStackTrace();
            //错误几次之后要多久才能再次登录
            return ServerResponse.errorMsg("用户名或密码错误");
        }
    }
    @RequestMapping("/admin")
    public ServerResponse admin(){
        return ServerResponse.successMsg("访问admin成功");
    }

    @RequestMapping("/logout")
    public ServerResponse logout(HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return ServerResponse.successMsg("退出成功");
    }
    @RequestMapping("edit")
    public String edit(){
        return "edit success";
    }

}
