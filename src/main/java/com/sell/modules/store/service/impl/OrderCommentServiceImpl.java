package com.sell.modules.store.service.impl;

import com.sell.common.IdGenerate;
import com.sell.modules.store.dao.OrderCommentMapper;
import com.sell.modules.store.entity.OrderComment;
import com.sell.modules.store.service.OrderCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        orderComment.setId(IdGenerate.uuid());
        return orderCommentMapper.insertSelective(orderComment);
    }
}
