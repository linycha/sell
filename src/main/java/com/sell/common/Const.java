package com.sell.common;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 一些常量
 * @author linyuc
 * @date 2019/11/20 11:34
 */
public class Const {
    public static final String CURRENT_USER = "currentUser";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String NOT_DELETE = "0";
    public static final String DELETED = "1";

    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc","price_asc");
    }
    public interface Role{
        int ROLE_CUSTOMER = 0;//普通用户
        int ROLE_ADMIN = 1;//超级管理员
    }

    public static boolean isNumber(String num){
        String regex = "^\\d{5}[0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher=pattern.matcher(num);
        return matcher.matches();
    }
    public enum ProductStatusEnum{
        ON_SALE(1,"在售");
        private String value;
        private int code;
        ProductStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }
}
