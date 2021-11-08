package com.sell.modules.store.service;

import com.sell.modules.store.entity.Delivery;

/**
 * @author linyuc
 * @date 2020/3/3 11:39
 */
public interface DeliveryService {
    boolean updateAssign(String orderNo);
    Delivery getBest();
    String getDeliveryId(String userId);
    Delivery getInfo(String userId);
    boolean update(Delivery delivery);
    boolean updateTaskNum(String userId);
}
