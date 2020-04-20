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
import com.sell.modules.sys.security.WebSocket;
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
    @Autowired
    private WebSocket webSocket;
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
            return Res.errorMsg("切换开工状态失败");
        }
        return Res.successMsg("切换开工状态成功");
    }
    @GetMapping("new_list")
    public Res<List<DeliveryOrderVo>> newOrderList(){
        //String userId = UserUtils.getUserId();
        String userId = "32";
//        String deliveryId = deliveryService.getDeliveryId(userId);
//        System.out.println("deliveryId="+deliveryId);
//        if(StringUtils.isBlank(deliveryId)){
//            return Res.errorMsg("请先登录相应的账号");
//        }
        List<DeliveryOrderVo> orderList = orderService.getDeliveryOrderList(userId,Const.OrderStatus.SHOP_ACCEPT);

        return Res.success(orderList);
    }
    @GetMapping("accept_list")
    public Res<List<DeliveryOrderVo>> takeOrderList(){
        String userId = UserUtils.getUserId();
        List<DeliveryOrderVo> orderList = orderService.getDeliveryOrderList(userId,Const.OrderStatus.DELIVERY_ACCEPT);

        return Res.success(orderList);
    }
    @GetMapping("take_list")
    public Res<List<DeliveryOrderVo>> deliveryOrderList(){
        String userId = UserUtils.getUserId();
        List<DeliveryOrderVo> orderList = orderService.getDeliveryOrderList(userId,Const.OrderStatus.DELIVERY_TAKE);

        return Res.success(orderList);
    }

    @PutMapping("accept")
    public Res<String> accept(String orderNo,String userId){
        if(StringUtils.isBlank(orderNo)){
            return Res.errorMsg("订单号参数错误");
        }
        int result = orderService.updateStatusByOrderNo(orderNo, Const.OrderStatus.DELIVERY_ACCEPT);
        if(result == 0){
            return Res.errorMsg("确认接单失败");
        }
        boolean b2 = orderStatusService.saveStatus(orderNo,Const.OrderStatus.DELIVERY_ACCEPT);
        if(!b2){
            return Res.errorMsg("确认接单失败");
        }
        webSocket.sendOneMessage(userId,"您有一条订单已被骑手接单，骑手正在赶往商家");
        return Res.successMsg("确认接单成功");
    }

    /**
     * 骑手取货操作
     */
    @PutMapping("take")
    public Res<String> take(String orderNo,String userId){
        if(StringUtils.isBlank(orderNo)){
            return Res.errorMsg("订单号参数错误");
        }
        int result = orderService.updateStatusByOrderNo(orderNo, Const.OrderStatus.DELIVERY_TAKE);
        if(result == 0){
            return Res.errorMsg("确认取货失败");
        }
        boolean b2 = orderStatusService.saveStatus(orderNo,Const.OrderStatus.DELIVERY_TAKE);
        if(!b2){
            return Res.errorMsg("确认取货失败");
        }
        webSocket.sendOneMessage(userId,"您有一条订单骑手正在配送中");
        return Res.successMsg("确认取货成功");
    }
    /**
     * 骑手确认送达订单操作
     */
    @PutMapping("accomplish")
    public Res<String> fulfill(String orderNo,String userId){
        if(StringUtils.isBlank(orderNo)){
            return Res.errorMsg("订单号参数错误");
        }
        int result = orderService.updateStatusByOrderNo(orderNo, Const.OrderStatus.ACCOMPLISH);
        if(result == 0){
            return Res.errorMsg("确认送达失败");
        }
        boolean b2 = orderStatusService.saveStatus(orderNo,Const.OrderStatus.ACCOMPLISH);
        if(!b2){
            return Res.errorMsg("确认送达失败");
        }
        String deliveryId = UserUtils.getUserId();
        boolean b3 = deliveryService.updateTaskNum(deliveryId);
        if(!b3){
            return Res.errorMsg("更新骑手配送订单量失败");
        }
        String shopId = orderService.getShopId(orderNo);
        webSocket.sendOneMessage(userId,"您有一条订单已送达，祝您用餐愉快");
        webSocket.sendOneMessage(shopId,"您有一条订单已被骑手送达");
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
