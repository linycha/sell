package com.sell.modules.store.service.impl;

import com.github.pagehelper.PageInfo;
import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.common.ResponseCode;
import com.sell.modules.store.dao.DeliveryMapper;
import com.sell.modules.store.dao.OrderMapper;
import com.sell.modules.store.dto.QueryDTO;
import com.sell.modules.store.entity.Delivery;
import com.sell.modules.store.entity.Order;
import com.sell.modules.store.service.DeliveryService;
import com.sell.modules.sys.dao.UserMapper;
import com.sell.modules.sys.entity.User;
import com.sell.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
    @Autowired
    private UserService userService;

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
        //暂时使用随机匹配一个
        Random random = new Random();
        int number = random.nextInt(count);
        return deliveryMapper.selectBest(number);
    }

    @Override
    public Delivery getInfo(Integer deliveryId) {
        return deliveryMapper.selectByUserId(deliveryId);
    }

    @Override
    public int update(Delivery delivery) {
        return deliveryMapper.updateByPrimaryKeySelective(delivery);
    }

    @Override
    public Res<Integer> save(Delivery delivery) {
        delivery.setPassword("123456");
        //先添加账号信息
        Res<Integer> res = userService.insertRegister(delivery.getUsername(), delivery.getMobile(), delivery.getPassword(),3);
        if(res.getCode() == ResponseCode.ERROR.getCode()){
            return res;
        }
        delivery.setUserId(res.getData());
        deliveryMapper.insertSelective(delivery);
        return Res.successMsg("添加骑手账号成功");
    }

    @Override
    public boolean updateTaskNum(Integer userId) {
        int result = deliveryMapper.updateTaskNumByUserId(userId);
        if(result == 1){
            return true;
        }
        return false;
    }

    @Override
    public PageInfo<Delivery> getDeliveryList(QueryDTO dto) {
        Const.initPage(dto.getCurrent(),dto.getSize());
        List<Delivery> list = deliveryMapper.selectDeliveryList(dto);
        return new PageInfo<>(list);
    }

    @Override
    public int deleteBatch(String ids) {
        List<Integer> list = deliveryMapper.selectUserIdByDeliveryId(ids);
        String userIds = list.stream().map(String::valueOf).collect(Collectors.joining(","));
        // 删除用户表对应用户
        userService.deleteBatch(userIds);
        return deliveryMapper.deleteBatch(ids);
    }
}
