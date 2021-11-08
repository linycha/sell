package com.sell.modules.store.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author linyuc
 */
@Data
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