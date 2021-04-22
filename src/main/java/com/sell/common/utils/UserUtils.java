package com.sell.common.utils;

import com.sell.modules.sys.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;

/**
 * @author linyuc
 * @date 2020/1/15 20:25
 */
public class UserUtils {
/*    public static User getUser() {
        Object o = SecurityUtils.getSubject().getPrincipal();
        User user = new User();
        BeanUtils.copyProperties(o,user);
        return user;
    }*/

    public static String getUserId() {
        Object o = SecurityUtils.getSubject().getPrincipal();
        return o.toString();
    }

}
