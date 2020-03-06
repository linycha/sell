package com.sell.modules.store.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** 新的订单实体类
 * @author linyuc
 * @date 2020/3/2 14:08
 */
@JsonIgnoreProperties({"carts"})
public class NewOrderVo implements Serializable {
    private String id;
    private Long orderNo;
    private String remark;
    private String userMobile;
    private BigDecimal payMoney;
    private String address;
    private String cartStr;
    //扩展用
    private List<Cart> carts = new ArrayList<>();

    public NewOrderVo(String id, Long orderNo, String remark, String userMobile, BigDecimal payMoney) {
        this.id = id;
        this.orderNo = orderNo;
        this.remark = remark;
        this.userMobile = userMobile;
        this.payMoney = payMoney;
    }

    public NewOrderVo() {
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

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public String getCartStr() {
        return cartStr;
    }

    public void setCartStr(String cartStr) {
        this.cartStr = cartStr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }


    @Override
    public String toString() {
        return "NewOrderVo{" +
                "id='" + id + '\'' +
                ", orderNo=" + orderNo +
                ", userMobile='" + userMobile + '\'' +
                ", payMoney=" + payMoney +
                ", remark='" + remark + '\'' +
                ", address='" + address + '\'' +
                ", cartStr='" + cartStr + '\'' +
                '}';
    }
}
