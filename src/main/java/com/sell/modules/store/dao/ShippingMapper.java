package com.sell.modules.store.dao;

import com.sell.modules.store.entity.Shipping;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/02/05 16:14
 */
public interface ShippingMapper {
    int deleteByPrimaryKey(String id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByOrderNo(String orderNo);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    List<Shipping> selectListByUserId(String userId);

    Shipping selectDefaultByUserId(String userId);

    void updateDefault(String userId);

    int insertAll(List<Shipping> list);

}