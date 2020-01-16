package com.sell.common.utils;

import com.sell.modules.sys.entity.User;
import org.apache.shiro.SecurityUtils;

/**
 * @author linyuc
 * @date 2020/1/15 20:25
 */
public class UserUtils {
    public static User getUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    public static String getUserId() {
        return getUser().getId();
    }
}
