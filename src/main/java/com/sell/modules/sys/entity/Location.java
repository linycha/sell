package com.sell.modules.sys.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Location {
    private String id;

    private Integer accuracy;

    private String locationType;

    private String address;

    private BigDecimal lat;

    private BigDecimal lng;

    private String info;

    private String message;

    private Date createTime;
    private long long1;

    private Long long2;

    //接收前端传值字段
    private String latitude;
    private String longitude;

}