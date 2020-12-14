package com.sell.modules.store.vo;

import java.math.BigDecimal;

/**
 * @author linyuc
 * @date 2020/1/20 10:13
 */
public class ShopVo {
    private String id;

    private String name;

    private String logoImg;

    private String address;

    private String tags;

    private BigDecimal sendCost;

    private BigDecimal deliveryCost;

    private Integer deliveryTime;

    private BigDecimal score;

    private Integer monthlySales;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

    public Integer getMonthlySales() {
        return monthlySales;
    }

    public void setMonthlySales(Integer monthlySales) {
        this.monthlySales = monthlySales;
    }

    @Override
    public String toString() {
        return "ShopVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", logoImg='" + logoImg + '\'' +
                ", address='" + address + '\'' +
                ", tags='" + tags + '\'' +
                ", sendCost=" + sendCost +
                ", deliveryCost=" + deliveryCost +
                ", deliveryTime=" + deliveryTime +
                ", score=" + score +
                ", monthlySales=" + monthlySales +
                '}';
    }
}
