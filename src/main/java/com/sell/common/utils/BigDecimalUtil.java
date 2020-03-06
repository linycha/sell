package com.sell.common.utils;

import java.math.BigDecimal;

/**
 * BigDecimal工具类
 * @author linyuc
 * @date 2020/1/20 15:46
 */
public class BigDecimalUtil {
    private BigDecimalUtil(){

    }
    public static BigDecimal add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v1));
        return b1.add(b2);
    }
    public static BigDecimal subtract(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v1));
        return b1.subtract(b2);
    }
    public static BigDecimal multiply(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v1));
        return b1.multiply(b2);
    }
    public static BigDecimal div(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v1));
        //四舍五入，保留两位小数
        return b1.divide(b2,2,BigDecimal.ROUND_HALF_UP);
    }
}
