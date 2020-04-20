package com.sell.modules.store.service;

import com.sell.modules.store.entity.OrderItem;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/3/1 17:22
 */
public interface OrderItemService {
    int insert(OrderItem orderItem);
    List<OrderItem> getList(String orderNo);
}
