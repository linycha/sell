package com.sell.modules.store.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sell.common.Const;
import com.sell.common.IdGenerate;
import com.sell.modules.store.dao.OrderCommentMapper;
import com.sell.modules.store.entity.OrderComment;
import com.sell.modules.store.entity.Product;
import com.sell.modules.store.service.OrderCommentService;
import org.apache.commons.lang3.StringUtils;
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
        orderComment.setId(IdGenerate.uuid());
        return orderCommentMapper.insertSelective(orderComment);
    }

    @Override
    public PageInfo<OrderComment> list(String shopId, String scoreType, String isAnonymity, String status, String pageNum) {
        Integer page = Const.PAGE_DEFAULT_NUM;
        Integer score = null;
        if(!StringUtils.isBlank(pageNum)){
            page = Integer.parseInt(pageNum);
        }
        PageHelper.startPage(page,Const.PAGE_DEFAULT_SIZE_FIVE);
        if(!StringUtils.isBlank(scoreType)){
           score = Integer.parseInt(scoreType);
        }
        List<OrderComment> commentList = orderCommentMapper.selectOrderCommentList(shopId,score,isAnonymity,status);
        PageInfo<OrderComment> pageResult = new PageInfo<>(commentList);
        return pageResult;
    }

    @Override
    public int update(String orderId,String reply) {
        return orderCommentMapper.updateReplyByOrderId(orderId,reply);
    }
}
