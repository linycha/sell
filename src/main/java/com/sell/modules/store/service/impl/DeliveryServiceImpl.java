package com.sell.modules.store.service.impl;

import com.sell.common.Const;
import com.sell.modules.store.dao.DeliveryMapper;
import com.sell.modules.store.dao.OrderMapper;
import com.sell.modules.store.entity.Delivery;
import com.sell.modules.store.entity.Order;
import com.sell.modules.store.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author linyuc
 * @date 2020/3/3 11:39
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    private DeliveryMapper deliveryMapper;
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 指派订单给骑手,匹配出相应的骑手，修改order表
     * @param orderNo
     * @return
     */
    @Override
    public boolean updateAssign(String orderNo){
        Delivery delivery = this.getBest();
        Order order = new Order();
        order.setOrderNo(Long.valueOf(orderNo));
        order.setDeliveryId(delivery.getId());
        order.setDeliveryName(delivery.getTrueName());
        order.setStatus(Const.OrderStatus.SHOP_ACCEPT);
        int result = orderMapper.updateByPrimaryKeySelective(order);
        if(result >=1){
            return true;
        }
        return false;
    }
    /**
     * 匹配最佳的骑手
     * @return Delivery
     */
    @Override
    public Delivery getBest() {
        int count = deliveryMapper.selectCount();

        Random random = new Random();
        int number = random.nextInt(count);
        return deliveryMapper.selectBest(number);
    }

    @Override
    public String getDeliveryId(String userId) {
        return deliveryMapper.selectDeliveryIdByUserId(userId);
    }

    @Override
    public Delivery getInfo(String userId) {
        return deliveryMapper.selectByUserId(userId);
    }

    @Override
    public boolean update(Delivery delivery) {
        int result = deliveryMapper.updateByPrimaryKeySelective(delivery);
        if(result == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTaskNum(Integer userId) {
        int result = deliveryMapper.updateTaskNumByUserId(userId);
        if(result == 1){
            return true;
        }
        return false;
    }
}
