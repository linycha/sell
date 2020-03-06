package com.sell.modules.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author linyuc
 * @date 2020/01/20 09:40
 */
@JsonIgnoreProperties({"userId","createTime","updateTime","delFlag"})
public class Shop {
    private String id;

    private String userId;

    private String name;

    private String mobile;

    private String logoImg;

    private String storeImg;

    private String address;

    private String categoryId;

    private String openingHours;

    private String tags;

    private String notice;

    private BigDecimal sendCost;

    private BigDecimal deliveryCost;

    private BigDecimal boxCost;

    private Integer deliveryTime;

    private BigDecimal score;

    private BigDecimal foodScore;

    private BigDecimal packScore;

    private BigDecimal deliveryScore;

    private Integer totalSales;

    private Integer monthlySales;

    private String status;

    private String delFlag;

    private Date createTime;

    private Date updateTime;

    public Shop(String id, String userId, String name, String mobile, String logoImg, String storeImg, String address, String categoryId, String openingHours, String tags, String notice, BigDecimal sendCost, BigDecimal deliveryCost, BigDecimal boxCost, Integer deliveryTime, BigDecimal score, BigDecimal foodScore, BigDecimal packScore, BigDecimal deliveryScore, Integer totalSales, Integer monthlySales, String status, String delFlag, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.mobile = mobile;
        this.logoImg = logoImg;
        this.storeImg = storeImg;
        this.address = address;
        this.categoryId = categoryId;
        this.openingHours = openingHours;
        this.tags = tags;
        this.notice = notice;
        this.sendCost = sendCost;
        this.deliveryCost = deliveryCost;
        this.boxCost = boxCost;
        this.deliveryTime = deliveryTime;
        this.score = score;
        this.foodScore = foodScore;
        this.packScore = packScore;
        this.deliveryScore = deliveryScore;
        this.totalSales = totalSales;
        this.monthlySales = monthlySales;
        this.status = status;
        this.delFlag = delFlag;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Shop() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg == null ? null : logoImg.trim();
    }

    public String getStoreImg() {
        return storeImg;
    }

    public void setStoreImg(String storeImg) {
        this.storeImg = storeImg == null ? null : storeImg.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours == null ? null : openingHours.trim();
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    public BigDecimal getSendCost() {
        return sendCost;
    }

    public void setSendCost(BigDecimal sendCost) {
        this.sendCost = sendCost;
    }

    public BigDecimal getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(BigDecimal deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public BigDecimal getBoxCost() {
        return boxCost;
    }

    public void setBoxCost(BigDecimal boxCost) {
        this.boxCost = boxCost;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Integer deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getFoodScore() {
        return foodScore;
    }

    public void setFoodScore(BigDecimal foodScore) {
        this.foodScore = foodScore;
    }

    public BigDecimal getPackScore() {
        return packScore;
    }

    public void setPackScore(BigDecimal packScore) {
        this.packScore = packScore;
    }

    public BigDecimal getDeliveryScore() {
        return deliveryScore;
    }

    public void setDeliveryScore(BigDecimal deliveryScore) {
        this.deliveryScore = deliveryScore;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    public Integer getMonthlySales() {
        return monthlySales;
    }

    public void setMonthlySales(Integer monthlySales) {
        this.monthlySales = monthlySales;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}