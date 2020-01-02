package com.sell.modules.store.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author linyc
 * @date 2019/12/12 12:43
 */
public class Product {
    private String id;

    private String shopId;

    private Integer categoryId;

    private String name;

    private String mainImg;

    private String subImages;

    private String desc;

    private BigDecimal price;

    private Integer monthlySales;

    private Integer stock;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Product(String id, String shopId, Integer categoryId, String name, String mainImg, String subImages, String desc, BigDecimal price, Integer monthlySales, Integer stock, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.shopId = shopId;
        this.categoryId = categoryId;
        this.name = name;
        this.mainImg = mainImg;
        this.subImages = subImages;
        this.desc = desc;
        this.price = price;
        this.monthlySales = monthlySales;
        this.stock = stock;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Product() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg == null ? null : mainImg.trim();
    }

    public String getSubImages() {
        return subImages;
    }

    public void setSubImages(String subImages) {
        this.subImages = subImages == null ? null : subImages.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getMonthlySales() {
        return monthlySales;
    }

    public void setMonthlySales(Integer monthlySales) {
        this.monthlySales = monthlySales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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