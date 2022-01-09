package com.sell.modules.store.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sell.modules.sys.entity.Permission;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author linyuc
 * @date 2020/03/01 00:35
 */
@Data
public class Order implements Serializable {
    private static final long serialVersionUID = 3515425295792740287L;
    private Integer id;

    private Long orderNo;

    private Integer userId;

    private Integer shippingId;

    private Integer shopId;

    private String shopName;

    private String shopLogo;

    private String shopMobile;

    private Integer deliveryId;

    private String deliveryName;

    private String deliveryMobile;

    private BigDecimal boxCost;

    private BigDecimal sendCost;

    private BigDecimal totalMoney;

    private BigDecimal payMoney;

    private String payType;

    private String remark;

    private Date completeTime;

    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date updateTime;

    //以下都是扩展用
    private String productName;
    private String number;
    //用来接收前端传来商品信息json字符串
    private String cartStr;
    //用来传给前端的商品列表
    private List<OrderItem> orderItemList;
    private String shippingAddress;
    private String shippingName;
    //用来接收前端传来的
    private String bCost;
    private String sCost;
    private String money;
}