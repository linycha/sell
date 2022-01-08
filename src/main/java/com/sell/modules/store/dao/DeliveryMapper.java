package com.sell.modules.store.dao;

import com.sell.modules.store.entity.Delivery;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author linyuc
 * @date 2019/12/20 11:50
 */
@Mapper
public interface DeliveryMapper {
    int deleteByPrimaryKey(String id);

    int insert(Delivery record);

    int insertSelective(Delivery record);

    Delivery selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Delivery record);

    int updateByPrimaryKey(Delivery record);

    int selectCount();

    Delivery selectBest(Integer number);

    String selectDeliveryIdByUserId(String userId);

    Delivery selectByUserId(String userId);

    int updateTaskNumByUserId(Integer userId);
}