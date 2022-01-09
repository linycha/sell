package com.sell.modules.store.vo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author linyuc
 * @date 2020/1/20 16:51
 */
@Data
public class ProductVo implements Serializable{
    private static final long serialVersionUID = 2397081056042082197L;

    private Integer id;

    private Integer categoryId;

    private String  categoryName;

    private String name;

    private String logoImg;

    private String remark;

    private BigDecimal originPrice;

    private BigDecimal sellPrice;
    //折扣
    private BigDecimal discount;
    //限购数量
    private Integer limitNum;

    private Integer monthlySales;
    //销售数量（返回给前端）
    private Integer sellNum = 0;
}
