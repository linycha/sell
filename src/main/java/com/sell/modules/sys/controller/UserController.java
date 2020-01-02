package com.sell.modules.sys.controller;

import com.sell.common.Const;
import com.sell.common.ResponseCode;
import com.sell.common.ServerResponse;
import com.sell.modules.sys.entity.User;
import com.sell.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author linyuc
 * @date 2019/12/18 15:10
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response = userService.login(username, password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.successMsg("退出成功");
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ServerResponse<String> register(User user){
        return userService.register(user);
    }
    @RequestMapping(value = "/question",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username){
        return userService.selectQuestion(username);
    }

    @RequestMapping(value = "/check_answer",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkAnswer(String username,String question, String answer){
        return userService.checkAnswer(username,question,answer);
    }
    /**
     * 未登录状态下忘记密码的重置密码
     * */
    @RequestMapping(value = "/rest_password",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> restPassword(String username,String passwordNew,String forgetToken){
        return userService.restPassword(username,passwordNew,forgetToken);
    }
    /**
     * 登录状态下的重置密码
     * */
    @RequestMapping(value = "/reset_password",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session,String passworOld,String passwordNew){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.errorMsg("用户未登录");
        }
        return userService.resetPassword(passworOld,passwordNew,user);
    }

    @RequestMapping(value = "/update_info",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateInfo(HttpSession session, User user){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.errorMsg("用户未登录");
        }
        //从登录用户中获取id和username
        //user.setId(currentUser.getId());
        //user.setUsername(currentUser.getUsername());
        ServerResponse<User> response =  userService.updateInfo(user);
        //老师的user信息都存到session里了，实际信息存model，username和id存session
        if(response.isSuccess()){
            //model存
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }
    @RequestMapping(value = "get_info")
    public ServerResponse<User> getInformation(HttpSession session){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.errorCodeMsg(ResponseCode.NEED_LOGIN.getCode(),"未登录需要强制登录");
        }

        return userService.getInformation(currentUser.getId());
    }
}
