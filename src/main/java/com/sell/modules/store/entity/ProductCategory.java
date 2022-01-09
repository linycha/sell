package com.sell.modules.store.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

/**
 * @author linyc
 * @date 2020/01/16 17:47
 */
@Data
@JsonIgnoreProperties({"parentId","shopId","sort","delFlag","updateTime"})
public class ProductCategory {
    private Integer id;

    private String parentId;

    private Integer shopId;

    private String name;

    private String status;

    private Integer sort;

    private String delFlag;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    private Date updateTime;
}