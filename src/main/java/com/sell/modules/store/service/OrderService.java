package com.sell.modules.store.service;

import com.github.pagehelper.PageInfo;
import com.sell.modules.store.dto.QueryOrderDTO;
import com.sell.modules.store.entity.Order;
import com.sell.modules.store.vo.DeliveryOrderVo;
import com.sell.modules.store.vo.NewOrderVo;
import com.sell.modules.store.vo.UserOrderVo;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/3/1 0:15
 */
public interface OrderService {
    boolean save(Order order);
    PageInfo<NewOrderVo> getOrderList(QueryOrderDTO dto);
    int updateStatusByOrderNo(Long orderNo,String status);
    int update(Order order);
    PageInfo<UserOrderVo> getUserOrderList(Integer userId, Integer deliveryId, String orderNo,String pageNum );
    List<DeliveryOrderVo> getDeliveryOrderList(Integer deliveryId,String status);

    String getUserMobile(Long orderNo);
    String getDeliveryMobile(Long orderNo);
    String getUserId(Long orderNo);
    String getShopId(Long orderNo);
    Order getOrderDetail(String orderNo);
}
