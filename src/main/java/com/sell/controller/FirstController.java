package com.sell.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linyuc
 * @date 2019/12/9 17:32
 */
@RestController
@RequestMapping("/first")
public class FirstController {
    @RequestMapping("hello")
    public String hello(){
        return "hello World!";
    }
}
