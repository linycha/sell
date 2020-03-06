package com.sell.modules.store.dao;

import com.sell.modules.store.entity.OrderItem;

/**
 * @author linyuc
 * @date 2020/03/01 00:13
 */
public interface OrderItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}