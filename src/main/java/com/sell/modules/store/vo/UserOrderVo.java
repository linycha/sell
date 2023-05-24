package com.sell.modules.store.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author linyuc
 * @date 2020/3/3 15:20
 */
@Data
public class UserOrderVo implements Serializable {
    private static final long serialVersionUID = -7552514319738161983L;

    private String id;
    private Long orderNo;
    private String shopId;
    private String shopLogo;
    private String shopName;
    private String status;
    private BigDecimal payMoney;
    private Date completeTime;
    private String cartStr;
    private String username;
    //扩展用
    private String completeTimeStr;
    private List<Cart> carts = new ArrayList<>();
}
