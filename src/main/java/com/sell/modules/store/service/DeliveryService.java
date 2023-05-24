package com.sell.modules.store.service;

import com.github.pagehelper.PageInfo;
import com.sell.common.Res;
import com.sell.modules.store.dto.QueryDTO;
import com.sell.modules.store.entity.Delivery;
import com.sell.modules.sys.entity.User;

/**
 * @author linyuc
 * @date 2020/3/3 11:39
 */
public interface DeliveryService {

    boolean updateAssign(String orderNo);

    Delivery getBest();

    Delivery getInfo(Integer deliveryId);

    int update(Delivery delivery);

    Res<Integer> save(Delivery delivery);

    boolean updateTaskNum(Integer userId);
    /**
     * 查询普通用户列表
     * @param dto
     * @return
     */
    PageInfo<Delivery> getDeliveryList(QueryDTO dto);
    /**
     * 批量假删
     * @param ids
     */
    int deleteBatch(String ids);
}
