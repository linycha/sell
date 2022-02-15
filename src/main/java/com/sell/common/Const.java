package com.sell.common;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
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
    public static final String FTP_PATH_USER = "user";
    public static final String FTP_PATH_SHOP = "shop";
    public static final String FTP_PATH_PRODUCT = "product";
    public static final String FTP_PATH_COMMENT = "comment";
    public static final String FTP_PATH_TEST = "test";
    public static final String FTP_PATH_DAILY = "daily";
    public static final Integer PAGE_DEFAULT_NUM = 1;
    public static final Integer PAGE_DEFAULT_SIZE = 10;
    public static final Integer PAGE_DEFAULT_SIZE2 = 5;
    public static final Integer PAGE_DEFAULT_SIZE_TEN = 10;
    public static final Integer PAGE_DEFAULT_SIZE_FIVE = 5;
    public static final Integer CATEGORY_PARENT_ID = 0;
    public static final String UPLOADING_IMG_URL = "";
    //评价等级  0:好评，1：一般，2：差评
    public static final Integer COMMENT_GOOD = 0;
    public static final Integer COMMENT_GENERAL = 1;
    public static final Integer COMMENT_BAD = 2;

    public static final String UPLOAD_CHECK = "980424";

    public interface ShopList{
        List<String> ORDER_BY = Lists.newArrayList("monthly_sales desc","monthly_sales desc","score desc","delivery_time");
    }

    /**
     * 订单状态：0:已提交，1:已支付 , 2:商家已接单 , 3: 骑手已接单, 4: 骑手已到店,
     *  5骑手已取货，6：订单已送达，70已退款（用户发起）,71已退款（商家发起），8已评价
     */

    public interface  OrderStatus{
        String PAID = "1";
        String SHOP_ACCEPT = "2";
        String DELIVERY_ACCEPT = "3";
        String DELIVERY_ARRIVE = "4";
        String DELIVERY_TAKE = "5";
        String ACCOMPLISH = "6";
        String REFUND_USER = "70";
        String REFUND_SHOP = "71";
        String HAVE_EVALUATION = "8";

    }
    /**
     * 用户账号状态：0：禁用，1：正常
     */
    public static final String USER_STATUS_ENABLE = "1";
    public static final String USER_STATUS_DISABLE = "0";

    /**
     * 角色信息
     * @return
     */
    public static final String USER_ROLE_ADMIN = "admin";
    public static final String USER_ROLE_CUSTOMER = "customer";
    public static final String USER_ROLE_BUSINESS = "business";
    public static final String USER_ROLE_DELIVERY = "delivery";

    public static long generateOrderNo(){
        long currentTime = System.currentTimeMillis();
        return currentTime + currentTime%10;
    }

    /**
     * 初始化分页大小
     */
    public static void initPage(int pageNum,int pageSize){
        if(pageNum == 0 || pageSize == 0){
            pageNum = Const.PAGE_DEFAULT_NUM;
            pageSize = Const.PAGE_DEFAULT_SIZE;
        }
        PageHelper.startPage(pageNum,pageSize);
    }
}
