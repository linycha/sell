package com.sell.modules.store.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author linyuc
 * @date 2020/03/01 00:13
 */
@Data
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1023918481434137016L;

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

}