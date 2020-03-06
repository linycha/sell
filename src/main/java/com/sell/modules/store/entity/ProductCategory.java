package com.sell.modules.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * @author linyc
 * @date 2020/01/16 17:47
 */
@JsonIgnoreProperties({"parentId","shopId","status","sort","delFlag","createTime","updateTime"})
public class ProductCategory {
    private String id;

    private String parentId;

    private String shopId;

    private String name;

    private String status;

    private Integer sort;

    private String delFlag;

    private Date createTime;

    private Date updateTime;

    public ProductCategory(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductCategory(String id, String parentId, String shopId, String name, String status, Integer sort, String delFlag, Date createTime, Date updateTime) {
        this.id = id;
        this.parentId = parentId;
        this.shopId = shopId;
        this.name = name;
        this.status = status;
        this.sort = sort;
        this.delFlag = delFlag;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ProductCategory() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
}