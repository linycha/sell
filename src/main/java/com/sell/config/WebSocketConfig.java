package com.sell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * @author linyuc
 * @date 2020/3/10 16:31
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
//    /**
//     * 注册端点，发布或订阅消息的时候需要连接此端点
//     * setAllowedOrigins非必须，*表示允许其他域进行连接
//     * withSockJs 表示开启sockejs支持
//     */
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/websocket").setAllowedOrigins("*").withSockJS();
//    }
//
//    /**
//     * 配置消息代理（中介）
//     * enableSimpleBroker 服务端推送给客户端的路径前缀
//     * setApplicationDestinationPrefixes 客户端发送数据给服务端的一个前缀
//     */
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.enableSimpleBroker("/topic","/chat");
//        registry.setApplicationDestinationPrefixes("/app");
//    }
}
