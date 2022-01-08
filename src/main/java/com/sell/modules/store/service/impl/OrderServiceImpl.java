package com.sell.modules.store.service.impl;

import com.github.pagehelper.PageInfo;
import com.sell.common.Const;
import com.sell.common.utils.DateTimeUtil;
import com.sell.modules.store.dao.OrderMapper;
import com.sell.modules.store.dto.QueryOrderDTO;
import com.sell.modules.store.entity.Order;
import com.sell.modules.store.service.OrderService;
import com.sell.modules.store.vo.Cart;
import com.sell.modules.store.vo.DeliveryOrderVo;
import com.sell.modules.store.vo.NewOrderVo;
import com.sell.modules.store.vo.UserOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/3/1 0:15
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public boolean save(Order order){
        order.setPayType("1");
        int result = orderMapper.insertSelective(order);
        if(result >= 1){
            return true;
        }
        return false;
    }

    /**
     * 获取商家自己的订单列表

     */
    @Override
    public PageInfo<NewOrderVo> getOrderList(QueryOrderDTO dto) {
        Const.initPage(dto.getPageNum(), dto.getPageSize());
        List<NewOrderVo> orderList = orderMapper.selectNewOrderList(dto);
        return new PageInfo<>(orderList);
    }

    @Override
    public int updateStatusByOrderNo(Long orderNo,String status) {
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setStatus(status);
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int update(Order order) {
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 查询用户自己的订单列表
     */
    @Override
    public PageInfo<UserOrderVo> getUserOrderList(Integer userId, String orderNo, String pageNum) {
        //先不分页
        /*int page = Const.PAGE_DEFAULT_NUM;
        if(!StringUtils.isBlank(pageNum)){
            page = Integer.parseInt(pageNum);
        }
        PageHelper.startPage(page,Const.PAGE_DEFAULT_SIZE2);*/
        List<UserOrderVo> orderList = orderMapper.selectUserOrderList(userId,orderNo);
        //处理得到的商品信息和数量拼接成String字符串
        for(UserOrderVo order : orderList){
            System.out.println(order.toString());
            String str = "";
            for(Cart cart : order.getCarts()){
                str += cart.getName()+ "×" + cart.getNum()+",";
            }
            order.setCartStr(str);
            order.setCompleteTimeStr(DateTimeUtil.dateToStr(order.getCompleteTime()));
            order.setCarts(null);
        }
        return new PageInfo<>(orderList);
    }

    /**
     * 获取骑手自己的订单,5个小时内
     */
    @Override
    public List<DeliveryOrderVo> getDeliveryOrderList(String deliveryId, String status) {
        return orderMapper.selectDeliveryOrderList(deliveryId,status);
    }

    @Override
    public String getUserMobile(Long orderNo) {
        return orderMapper.selectUserMobile(orderNo);
    }

    @Override
    public String getDeliveryMobile(Long orderNo) {
        return orderMapper.selectDeliveryMobile(orderNo);
    }

    @Override
    public String getUserId(Long orderNo) {
        return orderMapper.selectUserId(orderNo);
    }

    @Override
    public String getShopId(Long orderNo) {
        return orderMapper.selectShopId(orderNo);
    }

    @Override
    public Order getOrderDetail(String orderNo) {
        return orderMapper.selectOrderByOrderNo(orderNo);
    }

}
