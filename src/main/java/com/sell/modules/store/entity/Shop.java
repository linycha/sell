package com.sell.modules.store.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author linyuc
 * @date 2020/01/20 09:40
 */
@Data
@JsonIgnoreProperties({"userId","createTime","updateTime","delFlag"})
public class Shop {
    private Integer id;

    private Integer userId;

    private String name;

    private String mobile;

    private String logoImg;

    private String storeImg;

    private String address;

    private String categoryId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "GMT+8")
    @DateTimeFormat(pattern = "HH:mm")
    private Date openingTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "GMT+8")
    @DateTimeFormat(pattern = "HH:mm")
    private Date closingTime;
    private String tags;

    private String notice;

    private BigDecimal sendCost;

    private BigDecimal deliveryCost;

    private BigDecimal boxCost;

    private Integer deliveryTime;

    private BigDecimal score;

    private BigDecimal foodScore;

    private BigDecimal packScore;

    private BigDecimal deliveryScore;

    private Integer totalSales;

    private Integer monthlySales;

    private String status;

    private String delFlag;

    private Date createTime;

    private Date updateTime;
    private String categoryName;

}