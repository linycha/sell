package com.sell.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 允许swagger路径访问
 * @author linyuc
 * @date 2020/1/9 17:49
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 文件上传路径
     */
    @Value("${myFile.path}")
    public String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.
                addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
        // 文件路径映射
        registry.addResourceHandler("/file/**").addResourceLocations("file:"+path);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger-ui/")
                .setViewName("forward:/swagger-ui/index.html");
    }
//    protected final static Logger logger = LoggerFactory.getLogger(WebConfig.class);
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowCredentials(true)
//                .allowedMethods("*")
//                .allowedHeaders("*")
//                .maxAge(36000).allowedHeaders("*");
//        logger.info("*********************************跨域过滤器**************************");
//    }
}

