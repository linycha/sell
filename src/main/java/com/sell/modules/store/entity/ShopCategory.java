package com.sell.modules.store.entity;

import java.util.Date;
/**
 * @author linyc
 * @date 2020/01/16 17:47
 */
public class ShopCategory {
    private String id;

    private String parentId;

    private String name;

    private Integer sort;

    private String delFlag;

    private Date createTime;

    private Date updateTime;

    public ShopCategory(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public ShopCategory(String id, String parentId, String name, Integer sort, String delFlag, Date createTime, Date updateTime) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.sort = sort;
        this.delFlag = delFlag;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ShopCategory() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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