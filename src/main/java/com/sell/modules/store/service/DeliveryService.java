package com.sell.modules.store.service;

import com.sell.modules.store.entity.Delivery;

/**
 * @author linyuc
 * @date 2020/3/3 11:39
 */
public interface DeliveryService {
    boolean updateAssign(String orderNo);
    Delivery getBest();
    Delivery getInfo(Integer deliveryId);
    boolean update(Delivery delivery);
    boolean updateTaskNum(Integer userId);
}
