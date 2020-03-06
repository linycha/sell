package com.sell.modules.store.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author linyuc
 * @date 2020/3/3 15:20
 */
public class UserOrderVo implements Serializable {
    private String id;
    private Long orderNo;
    private String shopId;
    private String shopLogo;
    private String shopName;
    private String status;
    private BigDecimal payMoney;
    private Date completeTime;
    private String cartStr;
    //扩展用
    private String completeTimeStr;
    private List<Cart> carts = new ArrayList<>();

    public UserOrderVo() {
    }

    public UserOrderVo(String id, Long orderNo, String shopId, String shopLogo, String shopName, String status, BigDecimal payMoney, Date completeTime, String cartStr, String completeTimeStr, List<Cart> carts) {
        this.id = id;
        this.orderNo = orderNo;
        this.shopId = shopId;
        this.shopLogo = shopLogo;
        this.shopName = shopName;
        this.status = status;
        this.payMoney = payMoney;
        this.completeTime = completeTime;
        this.cartStr = cartStr;
        this.completeTimeStr = completeTimeStr;
        this.carts = carts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        this.shopId = shopId;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public String getCartStr() {
        return cartStr;
    }

    public void setCartStr(String cartStr) {
        this.cartStr = cartStr;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public String getCompleteTimeStr() {
        return completeTimeStr;
    }

    public void setCompleteTimeStr(String completeTimeStr) {
        this.completeTimeStr = completeTimeStr;
    }

    @Override
    public String toString() {
        return "UserOrderVo{" +
                "id='" + id + '\'' +
                ", orderNo=" + orderNo +
                ", shopId='" + shopId + '\'' +
                ", shopLogo='" + shopLogo + '\'' +
                ", shopName='" + shopName + '\'' +
                ", status='" + status + '\'' +
                ", payMoney=" + payMoney +
                ", completeTime='" + completeTime + '\'' +
                ", cartStr='" + cartStr + '\'' +
                ", carts=" + carts +
                '}';
    }
}
