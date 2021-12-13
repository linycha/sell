package com.sell.modules.store.service.impl;

import com.sell.common.IdGenerate;
import com.sell.modules.store.dao.OrderStatusMapper;
import com.sell.modules.store.entity.OrderStatus;
import com.sell.modules.store.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/3/1 18:30
 */
@Service
public class OrderStatusServiceImpl implements OrderStatusService {
    @Autowired
    private OrderStatusMapper orderStatusMapper;
    @Override
    public boolean saveStatus(Long orderNo,String status) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(IdGenerate.uuid());
        orderStatus.setOrderNo(orderNo);
        orderStatus.setStatus(status);
        int result = orderStatusMapper.insertSelective(orderStatus);
        if(result >= 1){
            return true;
        }
        return false;
    }

    @Override
    public List<OrderStatus> getList(String orderNo) {
        return orderStatusMapper.selectList(Long.valueOf(orderNo));
    }
}
