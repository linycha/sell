package com.sell.modules.store.service;

import com.github.pagehelper.PageInfo;
import com.sell.modules.store.dto.QueryCommentDTO;
import com.sell.modules.store.entity.OrderComment;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/3/31 23:27
 */
public interface OrderCommentService {
    int save(OrderComment orderComment);

    PageInfo<OrderComment> list(QueryCommentDTO dto);

    int update(String orderId,String reply);
}
