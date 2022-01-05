package com.sell.modules.store.service.impl;

import com.github.pagehelper.PageInfo;
import com.sell.common.Const;
import com.sell.modules.store.dao.OrderCommentMapper;
import com.sell.modules.store.dto.QueryCommentDTO;
import com.sell.modules.store.entity.OrderComment;
import com.sell.modules.store.service.OrderCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/3/31 23:26
 */
@Service
public class OrderCommentServiceImpl implements OrderCommentService {
    @Autowired
    private OrderCommentMapper orderCommentMapper;
    @Override
    public int save(OrderComment orderComment) {
        return orderCommentMapper.insertSelective(orderComment);
    }

    @Override
    public PageInfo<OrderComment> list(QueryCommentDTO dto) {
        Const.initPage(dto.getPageNum(),dto.getPageSize());
        List<OrderComment> commentList = orderCommentMapper.selectOrderCommentList(dto);

        return new PageInfo<>(commentList);
    }

    @Override
    public int update(Long orderNo,String reply) {
        return orderCommentMapper.updateReplyByOrderId(orderNo,reply);
    }
}
