package com.sell.modules.store.controller;

import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.common.utils.UserUtils;
import com.sell.modules.store.entity.Delivery;
import com.sell.modules.store.entity.Order;
import com.sell.modules.store.entity.OrderStatus;
import com.sell.modules.store.service.DeliveryService;
import com.sell.modules.store.service.OrderService;
import com.sell.modules.store.service.OrderStatusService;
import com.sell.modules.store.vo.DeliveryOrderVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/3/3 11:37
 */
@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderStatusService orderStatusService;
    @GetMapping("info")
    public Res<Delivery> info(){
        //String userId = UserUtils.getUserId();
        String userId = "32";
        Delivery delivery = deliveryService.getInfo(userId);
        return Res.success(delivery);
    }
    @PutMapping("start")
    public Res<String> startWork(String id,boolean value){
        System.out.println(value);
        System.out.println(value);
        System.out.println(value);
        Delivery delivery = new Delivery();
        delivery.setId(id);
        if(value){
            delivery.setStatus("1");
        }else{
            delivery.setStatus("2");
        }
        boolean b = deliveryService.update(delivery);
        if(!b){
            return Res.errorMsg("开始接单失败");
        }
        return Res.successMsg("开始接单成功");
    }
    @GetMapping("new_list")
    public Res<List<DeliveryOrderVo>> newOrderList(){
        //String userId = UserUtils.getUserId();
        String userId = "32";
        String deliveryId = deliveryService.getDeliveryId(userId);
        System.out.println("deliveryId="+deliveryId);
        if(StringUtils.isBlank(deliveryId)){
            return Res.errorMsg("请先登录相应的账号");
        }
        List<DeliveryOrderVo> orderList = orderService.getDeliveryOrderList(deliveryId,Const.OrderStatus.SHOP_ACCEPT);

        return Res.success(orderList);
    }
    @GetMapping("accept_list")
    public Res<List<DeliveryOrderVo>> takeOrderList(){
        //String userId = UserUtils.getUserId();
        String userId = "32";
        String deliveryId = deliveryService.getDeliveryId(userId);
        if(StringUtils.isBlank(deliveryId)){
            return Res.errorMsg("请先登录相应的账号");
        }
        List<DeliveryOrderVo> orderList = orderService.getDeliveryOrderList(deliveryId,Const.OrderStatus.DELIVERY_ACCEPT);

        return Res.success(orderList);
    }
    @GetMapping("take_list")
    public Res<List<DeliveryOrderVo>> deliveryOrderList(){
        //String userId = UserUtils.getUserId();
        String userId = "32";
        String deliveryId = deliveryService.getDeliveryId(userId);
        if(StringUtils.isBlank(deliveryId)){
            return Res.errorMsg("请先登录相应的账号");
        }
        List<DeliveryOrderVo> orderList = orderService.getDeliveryOrderList(deliveryId,Const.OrderStatus.DELIVERY_TAKE);

        return Res.success(orderList);
    }

    @PutMapping("accept")
    public Res<String> accept(String orderNo){
        if(StringUtils.isBlank(orderNo)){
            return Res.errorMsg("订单号参数错误");
        }
        int result = orderService.updateStatusByOrderNo(orderNo, Const.OrderStatus.DELIVERY_ACCEPT);
        if(result == 0){
            return Res.errorMsg("确认接单失败");
        }
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderNo(Long.valueOf(orderNo));
        orderStatus.setStatus(Const.OrderStatus.DELIVERY_ACCEPT);
        boolean b2 = orderStatusService.saveStatus(orderStatus);
        if(!b2){
            return Res.errorMsg("确认接单失败");
        }
        return Res.successMsg("确认接单成功");
    }

    /**
     * 骑手取货操作
     */
    @PutMapping("take")
    public Res<String> take(String orderNo){
        if(StringUtils.isBlank(orderNo)){
            return Res.errorMsg("订单号参数错误");
        }
        int result = orderService.updateStatusByOrderNo(orderNo, Const.OrderStatus.DELIVERY_TAKE);
        if(result == 0){
            return Res.errorMsg("确认取货失败");
        }
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderNo(Long.valueOf(orderNo));
        orderStatus.setStatus(Const.OrderStatus.DELIVERY_TAKE);
        boolean b2 = orderStatusService.saveStatus(orderStatus);
        if(!b2){
            return Res.errorMsg("确认取货失败");
        }
        return Res.successMsg("确认取货成功");
    }
    /**
     * 骑手确认送达订单操作
     */
    @PutMapping("accomplish")
    public Res<String> fulfill(String orderNo){
        if(StringUtils.isBlank(orderNo)){
            return Res.errorMsg("订单号参数错误");
        }
        int result = orderService.updateStatusByOrderNo(orderNo, Const.OrderStatus.ACCOMPLISH);
        if(result == 0){
            return Res.errorMsg("确认送达失败");
        }
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderNo(Long.valueOf(orderNo));
        orderStatus.setStatus(Const.OrderStatus.ACCOMPLISH);
        boolean b2 = orderStatusService.saveStatus(orderStatus);
        if(!b2){
            return Res.errorMsg("确认送达失败");
        }
        //String userId = UserUtils.getUserId();
        String userId = "32";
        Delivery delivery = new Delivery();
        delivery.setUserId(userId);
        boolean b3 = deliveryService.updateTaskNum(userId);
        if(!b3){
            return Res.errorMsg("更新骑手配送订单量失败");
        }
        return Res.successMsg("确认送达成功");
    }

    /**
     * 拒绝订单，分配给新的骑手
     */
    @PutMapping("reject")
    public Res<String> reject(String orderNo){
        if(StringUtils.isBlank(orderNo)){
            return Res.errorMsg("订单号参数错误");
        }
        boolean b = deliveryService.assign(orderNo);
        if(!b){
            return Res.errorMsg("拒绝订单失败");
        }
        return Res.successMsg("拒绝订单成功");
    }
}
