package com.sell.modules.store.service.impl;

import com.google.j2objc.annotations.AutoreleasePool;
import com.sell.common.IdGenerate;
import com.sell.modules.store.dao.OrderItemMapper;
import com.sell.modules.store.entity.OrderItem;
import com.sell.modules.store.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/3/1 17:22
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Override
    public int insert(OrderItem orderItem) {
        orderItem.setId(IdGenerate.uuid());
        return orderItemMapper.insertSelective(orderItem);
    }

    @Override
    public List<OrderItem> getList(String orderNo) {
        return orderItemMapper.selectListByOrderNo(orderNo);
    }
}
