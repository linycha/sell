package com.sell.modules.store.dao;

import com.sell.modules.store.entity.Order;
import com.sell.modules.store.vo.DeliveryOrderVo;
import com.sell.modules.store.vo.NewOrderVo;
import com.sell.modules.store.vo.UserOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    List<NewOrderVo> selectNewOrderList(@Param("shopId")String shopId, @Param("orderNo")String orderNo);

    List<UserOrderVo> selectUserOrderList(@Param("userId")String userId, @Param("orderNo")String orderNo);

    /**
     * 5个小时内
     */
    List<DeliveryOrderVo> selectDeliveryOrderList(@Param("deliveryId")String deliveryId,
                                                  @Param("status")String status);
}