package com.sell.modules.store.entity;

import com.sell.modules.sys.entity.Permission;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author linyuc
 * @date 2020/03/01 00:35
 */
public class Order implements Serializable {
    private String id;

    private Long orderNo;

    private String userId;

    private String shippingId;

    private String shopId;

    private String shopName;

    private String shopLogo;

    private String shopMobile;

    private String deliverId;

    private String deliverName;

    private String deliverMobile;

    private BigDecimal boxCost;

    private BigDecimal sendCost;

    private BigDecimal totalMoney;

    private BigDecimal payMoney;

    private String payType;

    private String remark;

    private Date completeTime;

    private String status;

    private Date createTime;

    private Date updateTime;

    //扩展用
    private String productName;
    private String number;
    //商品信息json字符串
    private String cartStr;
    //用来接收前端传来的
    private String bCost;
    private String sCost;
    private String money;

    public Order(String id, Long orderNo, String userId, String shippingId, String shopId, String shopName, String shopLogo, String shopMobile, String deliverId, String deliverName, String deliverMobile, BigDecimal boxCost, BigDecimal sendCost, BigDecimal totalMoney, BigDecimal payMoney, String payType, String remark, String status,Date completeTime, Date createTime, Date updateTime) {
        this.id = id;
        this.orderNo = orderNo;
        this.userId = userId;
        this.shippingId = shippingId;
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopLogo = shopLogo;
        this.shopMobile = shopMobile;
        this.deliverId = deliverId;
        this.deliverName = deliverName;
        this.deliverMobile = deliverMobile;
        this.boxCost = boxCost;
        this.sendCost = sendCost;
        this.totalMoney = totalMoney;
        this.payMoney = payMoney;
        this.payType = payType;
        this.remark = remark;
        this.completeTime = completeTime;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Order() {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getShippingId() {
        return shippingId;
    }

    public void setShippingId(String shippingId) {
        this.shippingId = shippingId == null ? null : shippingId.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo == null ? null : shopLogo.trim();
    }

    public String getShopMobile() {
        return shopMobile;
    }

    public void setShopMobile(String shopMobile) {
        this.shopMobile = shopMobile == null ? null : shopMobile.trim();
    }

    public String getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(String deliverId) {
        this.deliverId = deliverId == null ? null : deliverId.trim();
    }

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName == null ? null : deliverName.trim();
    }

    public String getDeliverMobile() {
        return deliverMobile;
    }

    public void setDeliverMobile(String deliverMobile) {
        this.deliverMobile = deliverMobile == null ? null : deliverMobile.trim();
    }

    public BigDecimal getBoxCost() {
        return boxCost;
    }

    public void setBoxCost(BigDecimal boxCost) {
        this.boxCost = boxCost;
    }

    public BigDecimal getSendCost() {
        return sendCost;
    }

    public void setSendCost(BigDecimal sendCost) {
        this.sendCost = sendCost;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCartStr() {
        return cartStr;
    }
    public void setCartStr(String cartStr) {
        this.cartStr = cartStr;
    }

    public String getbCost() {
        return bCost;
    }

    public void setbCost(String bCost) {
        this.bCost = bCost;
    }

    public String getsCost() {
        return sCost;
    }

    public void setsCost(String sCost) {
        this.sCost = sCost;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderNo=" + orderNo +
                ", userId='" + userId + '\'' +
                ", shippingId='" + shippingId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopLogo='" + shopLogo + '\'' +
                ", shopMobile='" + shopMobile + '\'' +
                ", deliverId='" + deliverId + '\'' +
                ", deliverName='" + deliverName + '\'' +
                ", deliverMobile='" + deliverMobile + '\'' +
                ", boxCost=" + boxCost +
                ", sendCost=" + sendCost +
                ", totalMoney=" + totalMoney +
                ", payMoney=" + payMoney +
                ", payType='" + payType + '\'' +
                ", remark='" + remark + '\'' +
                ", completeTime=" + completeTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", cartStr='" + cartStr + '\'' +
                ", bCost='" + bCost + '\'' +
                ", sCost='" + sCost + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}