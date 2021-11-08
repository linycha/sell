package com.sell.modules.store.dao;

import com.sell.modules.store.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/03/01 00:13
 */
@Mapper
public interface OrderItemMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
    List<OrderItem> selectListByOrderNo(String orderNo);
}