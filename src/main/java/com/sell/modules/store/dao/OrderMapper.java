package com.sell.modules.store.dao;

import com.sell.modules.store.entity.Order;
import com.sell.modules.store.vo.DeliveryOrderVo;
import com.sell.modules.store.vo.NewOrderVo;
import com.sell.modules.store.vo.UserOrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author linyuc
 * @date 2019/12/20 11:50
 */
@Mapper
public interface OrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    List<NewOrderVo> selectNewOrderList(@Param("shopId")String shopId, @Param("orderNo")String orderNo,
                                        @Param("status")String status);

    List<UserOrderVo> selectUserOrderList(@Param("userId")String userId, @Param("orderNo")String orderNo);

    /**
     * 5个小时内
     */
    List<DeliveryOrderVo> selectDeliveryOrderList(@Param("deliveryId")String deliveryId,
                                                  @Param("status")String status);
    String selectUserMobile(String orderNo);
    String selectDeliveryMobile(String orderNo);
    String selectUserId(String orderNo);
    String selectShopId(String orderNo);

    Order selectOrderByOrderNo(String orderNo);
}