package com.sell.modules.store.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author linyuc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    private Integer id;

    private Integer userId;

    private String trueName;

    private String mobile;

    private BigDecimal score;

    private Integer punctuality;

    private Integer dayTaskNum;

    private Integer monthTaskNum;

    private Integer totalTaskNum;

    private String status;

    private Date addTime;

    private String username;

    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;
}