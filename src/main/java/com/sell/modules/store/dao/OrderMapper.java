package com.sell.modules.store.dao;

import com.sell.modules.store.dto.QueryOrderDTO;
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
    List<NewOrderVo> selectNewOrderList(QueryOrderDTO dto);

    List<UserOrderVo> selectUserOrderList(@Param("userId")Integer userId, @Param("orderNo")String orderNo);

    /**
     * 5个小时内
     */
    List<DeliveryOrderVo> selectDeliveryOrderList(@Param("deliveryId")String deliveryId,
                                                  @Param("status")String status);
    String selectUserMobile(Long orderNo);
    String selectDeliveryMobile(Long orderNo);
    String selectUserId(Long orderNo);
    String selectShopId(Long orderNo);

    Order selectOrderByOrderNo(String orderNo);
}