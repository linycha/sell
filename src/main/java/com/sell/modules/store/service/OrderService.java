package com.sell.modules.store.service;

import com.github.pagehelper.PageInfo;
import com.sell.modules.store.entity.Order;
import com.sell.modules.store.vo.DeliveryOrderVo;
import com.sell.modules.store.vo.NewOrderVo;
import com.sell.modules.store.vo.UserOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/3/1 0:15
 */
public interface OrderService {
    boolean save(Order order);
    PageInfo<NewOrderVo> getNewOrderList(String shopId, String orderNo, String pageNum);
    int updateStatusByOrderNo(String orderNo,String status);
    int update(Order order);
    PageInfo<UserOrderVo> getUserOrderList(String userId, String orderNo,String pageNum );
    List<DeliveryOrderVo> getDeliveryOrderList(String deliveryId,String status);

}
