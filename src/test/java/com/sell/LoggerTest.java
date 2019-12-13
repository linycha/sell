package com.sell;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author linyuc
 * @date 2019/12/9 19:25
 */
@SpringBootTest
public class LoggerTest {

     private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

     @Test
    public void test1(){
         /*logger.debug("debug...");
         logger.info("info...");
         logger.error("error...");*/
     }
}
