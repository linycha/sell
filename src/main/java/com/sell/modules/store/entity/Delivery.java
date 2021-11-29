package com.sell.modules.store.entity;

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
    private String id;

    private String userId;

    private String trueName;

    private String mobile;

    private BigDecimal score;

    private Integer punctuality;

    private Integer dayTaskNum;

    private Integer monthTaskNum;

    private Integer totalTaskNum;

    private String status;

    private Date addTime;
}