package com.sell.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验工具类
 * @author linyuc
 * @date 2020/4/20 13:04
 */
public class CheckUtil {
    public static boolean isNumber(String num){
        String regex = "^\\d{5}[0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher=pattern.matcher(num);
        return matcher.matches();
    }
    public static boolean isMobile(String mobile) {
        if(StringUtils.isBlank(mobile)){
            return false;
        }
        String regex = "1[5378]\\d{9}|15[1267089]\\d{8}";
        //编译正则表达式
        Pattern pattern= Pattern.compile(regex);
        Matcher matcher=pattern.matcher(mobile);
        return matcher.matches();

    }
}
