package com.sell.modules.store.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author linyuc
 * @date 2021/12/9 16:32
 */
@Data
public class QueryOrderDTO implements Serializable {
    private static final long serialVersionUID = -1984202400099317589L;

    private Integer orderNo;

    private String shopId;

    private String status;

    private int pageNum;

    private int pageSize;
}
