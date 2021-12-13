package com.sell.modules.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;
/**
 * @author linyc
 * @date 2020/01/16 17:47
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties({"parentId","sort","delFlag","createTime","updateTime"})
public class ShopCategory {
    private String id;

    private String parentId;

    private String name;

    private Integer sort;

    private String delFlag;

    private Date createTime;

    private Date updateTime;
    //返回给前端的
    private String text;
    public ShopCategory(String id, String text) {
        this.id = id;
        this.text = text;
    }

}