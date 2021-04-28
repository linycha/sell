package com.sell.modules.sys.controller;

import com.sell.common.Res;
import com.sell.modules.store.dao.ShopMapper;
import com.sell.modules.store.service.ShopService;
import com.sell.modules.sys.entity.User;
import com.sell.modules.sys.security.WebSocket;
import com.sell.modules.sys.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linyuc
 * @date 2019/12/30 9:23
 */
@RestController
@RequestMapping("test")
@Api(tags = "测试相关接口")
public class TestController {
    @Autowired
    private UserService userService;
    @Autowired
    private WebSocket webSocket;


    @GetMapping("test")
    public Res<String> test2() {
        return Res.successMsg("1111111");
    }
    @GetMapping("sendAllWebSocket")
    public Res<String> test1() {
        webSocket.sendAllMessage("清晨起来打开窗，心情美美哒~");
        return Res.successMsg("websocket群体消息通知！");
    }

    @GetMapping("sendOneWebSocket")
    public Res<String> sendOneWebSocket() {
        webSocket.sendOneMessage("DPS007", "只要你乖给你买条gai！");
        return Res.successMsg("websocket单人发送");
    }
}
