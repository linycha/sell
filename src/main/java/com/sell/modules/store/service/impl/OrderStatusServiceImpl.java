package com.sell.modules.store.service.impl;

import com.sell.common.IdGenerate;
import com.sell.modules.store.dao.OrderStatusMapper;
import com.sell.modules.store.entity.OrderStatus;
import com.sell.modules.store.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linyuc
 * @date 2020/3/1 18:30
 */
@Service
public class OrderStatusServiceImpl implements OrderStatusService {
    @Autowired
    private OrderStatusMapper orderStatusMapper;
    @Override
    public boolean saveStatus(OrderStatus orderStatus) {
        orderStatus.setId(IdGenerate.uuid());
        int result = orderStatusMapper.insertSelective(orderStatus);
        if(result >= 1){
            return true;
        }
        return false;
    }
}
