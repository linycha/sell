package com.sell;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.sell.common.Const;
import com.sell.common.utils.CheckUtil;
import com.sell.common.utils.FTPUtil;
import com.sell.common.utils.RedisUtil;
import com.sell.modules.store.dao.*;
import com.sell.modules.store.entity.Delivery;
import com.sell.modules.store.entity.Shipping;
import com.sell.modules.store.entity.Shop;
import com.sell.modules.store.entity.ShopCategory;
import com.sell.modules.store.service.DeliveryService;
import com.sell.modules.store.service.OrderService;
import com.sell.modules.store.service.RedisService;
import com.sell.modules.store.service.ShopCategoryService;
import com.sell.modules.store.vo.Cart;
import com.sell.modules.store.vo.NewOrderVo;
import com.sell.modules.store.vo.ShopVo;
import com.sell.modules.store.vo.UserOrderVo;
import com.sell.modules.sys.dao.UserMapper;
import com.sell.modules.sys.entity.User;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import springfox.documentation.spring.web.json.Json;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author linyuc
 * @date 2019/12/12 12:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test {
    @Autowired
    private UserMapper userMapper;
    @Autowired RedisUtil.redisString redisUtil;

    @org.junit.Test
    public void test(){
        redisUtil.set("aaacl","qqqqq");
        System.out.println(redisUtil.get("aaacl").toString());
/*        User user = userMapper.selectByUsernameOrUserId("xiaolin",null);
        String str = JSON.toJSONString(user);
        //System.out.println(user);
        System.out.println(str);
        System.out.println("---------------");
        User a  = JSON.parseObject(str,User.class);
        System.out.println(a.toString());*/
    }
}
