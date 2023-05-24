package com.sell.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;
import lombok.*;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"openId","password","sex","updateTime"})
public class User implements Serializable {
    private static final long serialVersionUID = 7321352169002894594L;
    private Integer id;

    private Integer shopId;

    private Integer deliveryId;

    private String username;

    private String password;

    private String sex;

    private String mobile;

    private String headImg;

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date updateTime;

    private String[] roles;

    private List<Role> roleList = new ArrayList<>();
}