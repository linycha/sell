package com.sell.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author linyc
 * @date 2019/12/12 12:43
 */
@Data
@JsonIgnoreProperties({"openId","password","sex","createTime","updateTime"})
public class User implements Serializable {
    private String id;

    private String openId;

    private String username;

    private String password;

    private String sex;

    private String mobile;

    private String headImg;

    private String status;

    private Date createTime;

    private Date updateTime;

    private List<Role> roles = new ArrayList<>();
}