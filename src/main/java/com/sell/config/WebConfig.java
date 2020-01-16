package com.sell.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 跨域处理
 * @author linyuc
 * @date 2020/1/9 17:49
 */

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    protected final static Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(36000);
        logger.info("*********************************跨域过滤器**************************");
    }

}

