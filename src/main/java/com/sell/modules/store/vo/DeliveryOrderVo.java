package com.sell.modules.store.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author linyuc
 * @date 2020/3/5 10:33
 */
@Data
public class DeliveryOrderVo implements Serializable {
    private static final long serialVersionUID = 6144455599924303947L;

    private Integer id;
    private Long orderNo;
    private String userId;
    private String shopName;
    private String shopAddress;
    private String userAddress;
    private String username;
    private String mobile;
    private Integer deliveryTime;
    private BigDecimal deliveryCost;
}
