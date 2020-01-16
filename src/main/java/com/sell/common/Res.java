package com.sell.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * 高复用服务响应对象
 * JsonSerialize保证序列化Json的时候，如果是null的对象，可以也会消失,
 * code:状态码，msg信息，data数据
 * @author linyuc
 * @date 2019/12/18 10:11
 */
@JsonSerialize
public class Res<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    private Res(int code){
        this.code =code;
    }
    private Res(int code, T data){
        this.code = code;
        this.data = data;
    }
    private Res(int code,String msg){
        this.code =code;
        this.msg = msg;
    }
    private Res(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    //使之不在json序列化结果当中
    @JsonIgnore
    public boolean isSuccess(){
        return this.code == ResponseCode.SUCCESS.getCode();
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    public T getData() {
        return data;
    }
    public static <T> Res<T> success(){
        return new Res<T>(ResponseCode.SUCCESS.getCode());
    }
    public static <T> Res<T> successMsg(String msg){
        return new Res<T>(ResponseCode.SUCCESS.getCode(),msg);
    }
    public static <T> Res<T> success(T data){
        return new Res<T>(ResponseCode.SUCCESS.getCode(),data);
    }
    public static <T> Res<T> success(String msg,T data){
        return new Res<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }
    public static <T> Res<T> error(){
        return new Res<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }
    public static <T> Res<T> errorMsg(String msg){
        return new Res<T>(ResponseCode.ERROR.getCode(),msg);
    }

    public static <T> Res<T> errorCodeMsg(int code,String msg){
        return new Res<T>(code,msg);
    }
}
