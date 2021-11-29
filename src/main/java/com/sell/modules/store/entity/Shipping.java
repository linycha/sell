package com.sell.modules.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author linyuc
 * @date 2020/02/05 16:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"userId","createTime","updateTime","delFlag"})
public class Shipping {
    private String id;

    private String userId;

    private String name;

    private String tel;

    private String province;

    private String city;

    private String county;

    private String address;

    private String isDefault;

    private String delFlag;

    private Date createTime;

    private Date updateTime;
}