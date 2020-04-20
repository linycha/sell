package com.sell.modules.store.dao;

import com.sell.modules.store.entity.OrderStatus;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/03/01 00:13
 */
public interface OrderStatusMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderStatus record);

    int insertSelective(OrderStatus record);

    OrderStatus selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderStatus record);

    int updateByPrimaryKey(OrderStatus record);
    List<OrderStatus> selectList(Long orderNo);
}