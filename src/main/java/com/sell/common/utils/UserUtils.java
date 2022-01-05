package com.sell.common.utils;

import com.alibaba.fastjson.JSON;
import com.sell.modules.sys.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
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

    /**
     * 获取当前登录用户首个角色,如 “admin”
     */
    public static String getRole(){
        return getUser().getRoles()[0];
    }

    /**
     * 利用shiro的api加密密码
     * @param password
     * @return
     */
    public static String hashTwo(String password){
        Object md5Password = new SimpleHash("md5",password, null, 2);
        return md5Password.toString();
    }

}
