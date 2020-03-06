package com.sell.modules.store.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author linyuc
 * @date 2020/03/01 00:13
 */
public class OrderItem implements Serializable {
    private String id;

    private Long orderNo;

    private String shopId;

    private String productId;

    private String productName;

    private String productImg;

    private BigDecimal originPrice;

    private BigDecimal sellPrice;

    private Integer number;

    private BigDecimal totalPrice;

    private Date createTime;

    public OrderItem(String id, Long orderNo, String shopId, String productId, String productName, String productImg, BigDecimal originPrice, BigDecimal sellPrice, Integer number, BigDecimal totalPrice, Date createTime) {
        this.id = id;
        this.orderNo = orderNo;
        this.shopId = shopId;
        this.productId = productId;
        this.productName = productName;
        this.productImg = productImg;
        this.originPrice = originPrice;
        this.sellPrice = sellPrice;
        this.number = number;
        this.totalPrice = totalPrice;
        this.createTime = createTime;
    }

    public OrderItem() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg == null ? null : productImg.trim();
    }

    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}