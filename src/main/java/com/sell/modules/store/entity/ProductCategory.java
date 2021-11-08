package com.sell.modules.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

/**
 * @author linyc
 * @date 2020/01/16 17:47
 */
@Data
@JsonIgnoreProperties({"parentId","shopId","status","sort","delFlag","createTime","updateTime"})
public class ProductCategory {
    private String id;

    private String parentId;

    private String shopId;

    private String name;

    private String status;

    private Integer sort;

    private String delFlag;

    private Date createTime;

    private Date updateTime;
}