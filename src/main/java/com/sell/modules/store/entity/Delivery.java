package com.sell.modules.store.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Delivery {
    private String id;

    private String userId;

    private String trueName;

    private String mobile;

    private BigDecimal score;

    private Integer punctuality;

    private Integer dayTaskNum;

    private Integer monthTaskNum;

    private Integer totalTaskNum;

    private String status;

    private Date addTime;

    public Delivery(String id, String userId, String trueName, String mobile, BigDecimal score, Integer punctuality, Integer dayTaskNum, Integer monthTaskNum, Integer totalTaskNum, String status, Date addTime) {
        this.id = id;
        this.userId = userId;
        this.trueName = trueName;
        this.mobile = mobile;
        this.score = score;
        this.punctuality = punctuality;
        this.dayTaskNum = dayTaskNum;
        this.monthTaskNum = monthTaskNum;
        this.totalTaskNum = totalTaskNum;
        this.status = status;
        this.addTime = addTime;
    }

    public Delivery(String id, String trueName) {
        this.id = id;
        this.trueName = trueName;
    }

    public Delivery() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Integer getPunctuality() {
        return punctuality;
    }

    public void setPunctuality(Integer punctuality) {
        this.punctuality = punctuality;
    }

    public Integer getDayTaskNum() {
        return dayTaskNum;
    }

    public void setDayTaskNum(Integer dayTaskNum) {
        this.dayTaskNum = dayTaskNum;
    }

    public Integer getMonthTaskNum() {
        return monthTaskNum;
    }

    public void setMonthTaskNum(Integer monthTaskNum) {
        this.monthTaskNum = monthTaskNum;
    }

    public Integer getTotalTaskNum() {
        return totalTaskNum;
    }

    public void setTotalTaskNum(Integer totalTaskNum) {
        this.totalTaskNum = totalTaskNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", trueName='" + trueName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", score=" + score +
                ", punctuality=" + punctuality +
                ", dayTaskNum=" + dayTaskNum +
                ", monthTaskNum=" + monthTaskNum +
                ", totalTaskNum=" + totalTaskNum +
                ", status='" + status + '\'' +
                ", addTime=" + addTime +
                '}';
    }
}