package com.sell.common;
/**
 * 枚举类
 * @author linyuc
 * @date 2019/12/18 10:11
 */
public enum ResponseCode {
    /**
     * success
     * @Params
     */
    SUCCESS(200,"SUCCESS"),
    ERROR(400,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    Unauthorized(401,"Unauthorized"),
    Forbidden(403,"Forbidden");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc){
        this.code = code;
        this.desc = desc;
    }
    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }
}
