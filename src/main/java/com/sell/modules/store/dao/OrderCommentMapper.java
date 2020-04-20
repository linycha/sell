package com.sell.modules.store.dao;

import com.sell.modules.store.entity.OrderComment;

public interface OrderCommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderComment record);

    int insertSelective(OrderComment record);

    OrderComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderComment record);

    int updateByPrimaryKey(OrderComment record);
}