package com.sell.modules.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 店铺首页统计信息dto
 * @author linyuc
 * @date 2021/12/6 17:26
 */
@Data
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class ShopCountDTO implements Serializable {

    private static final long serialVersionUID = -793036735405216847L;

    /**
     * 订单总数
     */
    private int orderTotal;
    /**
     * 客户总数
     */
    private int customerTotal;
    /**
     * 销售总额
     */
    private BigDecimal salesTotal;
    /**
     * 商品总数
     */
    private int productTotal;
    /**
     * 年月
     */
    private String month;
}
