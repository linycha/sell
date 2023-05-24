package com.sell.modules.store.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@JsonIgnoreProperties({"parentId","sort","delFlag"})
public class ShopCategory {
    private Integer id;

    private Integer parentId;

    private String name;

    private Integer sort;

    private Integer delFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date updateTime;
    //返回给前端的
    private String text;
    public ShopCategory(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

}