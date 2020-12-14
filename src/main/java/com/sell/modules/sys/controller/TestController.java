package com.sell.modules.sys.controller;

import com.sell.common.Res;
import com.sell.modules.store.dao.ShopMapper;
import com.sell.modules.store.service.ShopService;
import com.sell.modules.sys.entity.User;
import com.sell.modules.sys.security.WebSocket;
import com.sell.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linyuc
 * @date 2019/12/30 9:23
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private UserService userService;
    @Autowired
    private WebSocket webSocket;
    @Autowired
    private ShopService shopService ;
    @Autowired
    private ShopMapper shopMapper ;
    @RequestMapping("drool")
    public void param (){
    }


    @RequestMapping("test")
    public Res<String> test2() {
        return Res.successMsg("1111111");
    }
    @RequestMapping("sendAllWebSocket")
    public Res<String> test1() {
        webSocket.sendAllMessage("清晨起来打开窗，心情美美哒~");
        return Res.successMsg("websocket群体消息通知！");
    }

    @RequestMapping("sendOneWebSocket")
    public Res<String> sendOneWebSocket() {
        webSocket.sendOneMessage("DPS007", "只要你乖给你买条gai！");
        return Res.successMsg("websocket单人发送");
    }
    @RequestMapping("list")
    public Res<String> author(){
        return Res.successMsg("列表");
    }
    @RequestMapping("test1")
    public Res<String> test(){
        return Res.successMsg("test");
    }
    @RequestMapping("index")
    public Object index(){
        String username = "eeeee";
        User user = userService.selectByUsername(username,null);
        if(user == null){
            return "null";
        }
        return user;
    }

    @RequestMapping("/admin")
    public Res admin(){
        return Res.successMsg("访问admin成功");
    }

}
