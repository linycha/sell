package com.sell.modules.store.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author linyuc
 * @date 2020/1/20 10:13
 */
@Data
public class ShopVo {
    private String id;

    private String name;

    private String logoImg;

    private String address;

    private String tags;

    private BigDecimal sendCost;

    private BigDecimal deliveryCost;

    private Integer deliveryTime;

    private BigDecimal score;

    private Integer monthlySales;
}
