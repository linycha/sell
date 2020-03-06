package com.sell.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by 廖师兄
 * 2017-07-03 01:25
 */
@Configuration
public class WechatMpConfig {
    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpDefaultConfigImpl wxMpDefaultConfig = new WxMpDefaultConfigImpl();
        wxMpDefaultConfig.setToken("aaaacl");
        wxMpDefaultConfig.setAppId("wxdb39d808622723ea");
        wxMpDefaultConfig.setSecret("38cd0511e446f5cd6be72ad6adf85e90");
        //wxMpConfigStorage.setAppId("wxdb39d808622723ea");
        //wxMpConfigStorage.setSecret("38cd0511e446f5cd6be72ad6adf85e90");
        return wxMpDefaultConfig;
    }
}
