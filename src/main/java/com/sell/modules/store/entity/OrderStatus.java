package com.sell.modules.store.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

/**
 * @author linyuc
 * @date 2020/03/01 00:13
 */
@Data
@JsonIgnoreProperties({"id","orderNo","remark"})
public class OrderStatus {
    private String id;

    private Long orderNo;

    private String status;

    private String remark;

    @JsonFormat(pattern = "MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;
}