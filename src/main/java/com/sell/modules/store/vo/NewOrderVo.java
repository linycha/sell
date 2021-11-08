package com.sell.modules.store.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** 商家新的订单实体类
 * @author linyuc
 * @date 2020/3/2 14:08
 */
@JsonIgnoreProperties({"carts"})
@Data
public class NewOrderVo implements Serializable {
    private static final long serialVersionUID = 4328147386951812730L;
    private String id;
    private Long orderNo;
    private String remark;
    private String username;
    private String deliveryName;
    private BigDecimal payMoney;
    private String status;
    private String address;
    private String cartStr;
    //扩展用
    private List<Cart> carts = new ArrayList<>();
}
