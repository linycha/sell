package com.sell.modules.store.service;

import com.sell.modules.store.entity.OrderStatus;

/**
 * @author linyuc
 * @date 2020/3/1 18:30
 */
public interface OrderStatusService {
    boolean saveStatus(OrderStatus orderStatus);
}
