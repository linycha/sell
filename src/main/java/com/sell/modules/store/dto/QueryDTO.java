package com.sell.modules.store.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author linyuc
 * @Description TODO
 * @date 2022/2/28 22:51
 */
@Data
public class QueryDTO implements Serializable {
    private static final long serialVersionUID = -669994595343357950L;

    private String username;

    private String name;

    private String status;

    private int current;

    private int size;
}
