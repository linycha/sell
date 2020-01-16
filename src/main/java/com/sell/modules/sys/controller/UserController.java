package com.sell.modules.sys.controller;

import com.sell.common.Const;
import com.sell.common.ResponseCode;
import com.sell.common.Res;
import com.sell.common.utils.UserUtils;
import com.sell.modules.sys.entity.User;
import com.sell.modules.sys.service.UserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author linyuc
 * @date 2019/12/18 15:10
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取用户个人信息
     */
    @GetMapping("info")
    public Res info(){
        return userService.selectById(UserUtils.getUserId());
    }

    @PostMapping("update_head")
    public Res<String> updateHead(String url){
        return null;
    }
    @PostMapping("update_mobile")
    public Res<String> updateMobile(String mobile){
        User user = new User();
        user.setId(UserUtils.getUserId());
        user.setMobile(mobile);
        return userService.updateMobile(user);
    }
    @PostMapping("update_password")
    public Res<String> updatePassword(String oldPwd,String newPwd){
        User user = new User();
        user.setId(UserUtils.getUserId());
        user.setPassword(oldPwd);
        return userService.updatePassword(newPwd,user);
    }

    /**
     * 未登录状态下忘记密码的重置密码
     * */
    @RequestMapping(value = "/rest_password",method = RequestMethod.POST)
    @ResponseBody
    public Res<String> restPassword(String username,String passwordNew,String forgetToken){
        return userService.restPassword(username,passwordNew,forgetToken);
    }
}
