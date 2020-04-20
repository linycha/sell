package com.sell.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
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
        String regex = "1[378]\\d{9}|15[1267089]\\d{8}";
        //编译正则表达式
        Pattern pattern= Pattern.compile(regex);
        Matcher matcher=pattern.matcher(mobile);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }

    }
}
