package com.sell;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author linyc
 * @date 2019/12/9 18:25
 */
@EnableOpenApi
@SpringBootApplication
@MapperScan(basePackages= {"com.sell.modules.store.dao", "com.sell.modules.sys.dao"})
public class SellApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellApplication.class, args);
    }

}
