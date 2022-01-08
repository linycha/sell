package com.sell.modules.store.service;

import com.sell.modules.store.entity.Shipping;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/2/5 16:15
 */
public interface ShippingService {
    List<Shipping> getShippingList(Integer userId);
    int save(Shipping shipping);
    int update(Shipping shipping);
    Shipping getDefault(Integer userId);
    void updateDefault(Integer userId);
    Shipping getByOrderNo(String orderNo);
}
