package com.sell.modules.sys.entity;

import java.math.BigDecimal;
import java.util.Date;

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

    //接收前端传值字段
    private String latitude;
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Location(String id, Integer accuracy, String locationType, String address, BigDecimal lat, BigDecimal lng, String info, String message, Date createTime) {
        this.id = id;
        this.accuracy = accuracy;
        this.locationType = locationType;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.info = info;
        this.message = message;
        this.createTime = createTime;
    }

    public Location() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType == null ? null : locationType.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}