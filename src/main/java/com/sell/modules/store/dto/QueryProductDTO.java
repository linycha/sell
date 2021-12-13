package com.sell.modules.store.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author linyuc
 * @date 2021/12/6 21:16
 */
@Data
public class QueryProductDTO implements Serializable {
    private static final long serialVersionUID = 8826638126597745310L;

    private String categoryId;

    private String name;

    private String status;

    private String shopId;

    private int pageNum;

    private int pageSize;
}
