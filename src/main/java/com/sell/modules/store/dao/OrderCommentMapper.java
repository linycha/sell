package com.sell.modules.store.dao;

import com.sell.modules.store.dto.QueryCommentDTO;
import com.sell.modules.store.entity.OrderComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderCommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderComment record);

    int insertSelective(OrderComment record);

    OrderComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderComment record);

    int updateByPrimaryKey(OrderComment record);

    List<OrderComment> selectOrderCommentList(QueryCommentDTO dto);

    int updateReplyByOrderId(@Param("orderId") String orderId,@Param("reply") String reply);

}