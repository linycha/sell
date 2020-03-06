package com.sell.modules.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author linyuc
 * @date 2020/01/20 16:36
 */
@JsonIgnoreProperties({"shopId","createTime","updateTime","delFlag","file"})
public class Product {
    private String id;

    private String shopId;

    private String categoryId;

    private String name;

    private String logoImg;

    private String subImg;

    private String remark;

    private BigDecimal originPrice;

    private BigDecimal sellPrice;
    //折扣
    private BigDecimal discount;

    private Integer likes;
    //限购数量
    private Integer limitNum;

    private Integer totalSales;

    private Integer monthlySales;

    private Integer stock;

    private String status;

    private String delFlag;

    private Date createTime;

    private Date updateTime;
    //用来接收前端传来的图片、String类型的原价和售价
    private MultipartFile file;
    private String origin;
    private String sell;

    public Product(String id, String shopId, String categoryId, String name, String logoImg, String subImg, String remark, BigDecimal originPrice, BigDecimal sellPrice, BigDecimal discount, Integer likes, Integer limitNum, Integer totalSales, Integer monthlySales, Integer stock, String status, String delFlag, Date createTime, Date updateTime, MultipartFile file, String origin, String sell) {
        this.id = id;
        this.shopId = shopId;
        this.categoryId = categoryId;
        this.name = name;
        this.logoImg = logoImg;
        this.subImg = subImg;
        this.remark = remark;
        this.originPrice = originPrice;
        this.sellPrice = sellPrice;
        this.discount = discount;
        this.likes = likes;
        this.limitNum = limitNum;
        this.totalSales = totalSales;
        this.monthlySales = monthlySales;
        this.stock = stock;
        this.status = status;
        this.delFlag = delFlag;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.file = file;
        this.origin = origin;
        this.sell = sell;
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg == null ? null : logoImg.trim();
    }

    public String getSubImg() {
        return subImg;
    }

    public void setSubImg(String subImg) {
        this.subImg = subImg == null ? null : subImg.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }
}