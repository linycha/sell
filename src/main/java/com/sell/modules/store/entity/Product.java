package com.sell.modules.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author linyuc
 * @date 2020/01/20 16:36
 */
@Data
@JsonIgnoreProperties({"shopId","createTime","updateTime","delFlag","file"})
public class Product {
    private Integer id;

    private Integer shopId;

    private Integer categoryId;

    private String name;

    private String logoImg;

    private String subImg;

    private String remark;

    private BigDecimal originPrice;

    private BigDecimal sellPrice;
    //折扣
    private BigDecimal discount;

    private Integer likes;
    //限购数量
    private Integer limitNum;

    private Integer totalSales;

    private Integer monthlySales;

    private Integer stock;

    private String status;

    private String delFlag;

    private Date createTime;

    private Date updateTime;
    //用来接收前端传来的图片、String类型的原价和售价
    private MultipartFile file;
    private String origin;
    private String sell;
}