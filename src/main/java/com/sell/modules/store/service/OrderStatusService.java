package com.sell.modules.store.service;

import com.sell.modules.store.entity.OrderStatus;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/3/1 18:30
 */
public interface OrderStatusService {
    boolean saveStatus(Long orderNo,String status);
    List<OrderStatus> getList(String orderNo);
}
