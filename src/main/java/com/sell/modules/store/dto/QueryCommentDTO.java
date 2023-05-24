package com.sell.modules.store.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * 订单评价查询dto
 * @author linyuc
 * @date 2021/11/10 10:41
 */
@Data
public class QueryCommentDTO implements Serializable {

    private static final long serialVersionUID = 4230409002628279977L;

    private Integer shopId;
    private Integer userId;

    private String scoreType;

    private String isAnonymity;

    private String status;

    private int pageNum;

    private int pageSize;
}
