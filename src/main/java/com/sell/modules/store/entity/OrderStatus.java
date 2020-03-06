package com.sell.modules.store.entity;

import java.util.Date;

/**
 * @author linyuc
 * @date 2020/03/01 00:13
 */
public class OrderStatus {
    private String id;

    private Long orderNo;

    private String status;

    private String remark;

    private Date createTime;

    public OrderStatus(String id, Long orderNo, String status, String remark, Date createTime) {
        this.id = id;
        this.orderNo = orderNo;
        this.status = status;
        this.remark = remark;
        this.createTime = createTime;
    }

    public OrderStatus() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}