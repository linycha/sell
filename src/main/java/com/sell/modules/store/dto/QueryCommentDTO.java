package com.sell.modules.store.dto;

import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.ws.rs.DefaultValue;
import java.io.Serializable;

/**
 * 订单评价查询dto
 * @author linyuc
 * @date 2021/11/10 10:41
 */
@Data
public class QueryCommentDTO implements Serializable {

    private static final long serialVersionUID = 4230409002628279977L;

    String  shopId;

    String scoreType;

    String isAnonymity;

    String status;

    int pageNum;

    int pageSize;
}
