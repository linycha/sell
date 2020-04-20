package com.sell.modules.store.service;

import com.sell.modules.store.entity.Shipping;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/2/5 16:15
 */
public interface ShippingService {
    List<Shipping> getShippingList(String userId);
    int save(Shipping shipping);
    int update(Shipping shipping);
    Shipping getDefault(String userId);
    void updateDefault(String userId);
    Shipping getByOrderNo(String orderNo);
}
