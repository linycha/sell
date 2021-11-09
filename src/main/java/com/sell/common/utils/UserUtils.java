package com.sell.common.utils;

import com.alibaba.fastjson.JSON;
import com.sell.modules.sys.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;

/**
 * @author linyuc
 * @date 2020/1/15 20:25
 */
public class UserUtils {
    /**
     * 获取当前登录用户信息
     * @return user
     */
    public static User getUser() {
        Object o = SecurityUtils.getSubject().getPrincipal();
        //解析user的json字符串
        return JSON.parseObject(o.toString(),User.class);
    }

    /**
     * 获取当前登录用户Id
     * @return userId
     */
    public static String getUserId() {
        return getUser().getId();
    }

    /**
     * 获取当前登录用户的商家Id
     * @return shopId
     */
    public static String getShopId() {
        return getUser().getShopId();
    }

}
