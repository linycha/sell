package com.sell.controller;

import com.sell.dao.UserMapper;
import com.sell.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linyc
 * @date 2019/12/9 17:32
 */
@Controller
@RequestMapping("/first")
public class FirstController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/user")
    @ResponseBody
    public User user(){
        User user = userMapper.selectByPrimaryKey("1");
        return user;
    }
    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("ha","eeeeeeeeeeeeeeee");
        return "first";
    }
}
