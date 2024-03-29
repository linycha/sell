package com.sell.modules.store.controller;

import cn.hutool.core.util.ObjectUtil;
import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.common.utils.UserUtils;
import com.sell.modules.store.entity.Delivery;
import com.sell.modules.store.entity.Order;
import com.sell.modules.store.service.DeliveryService;
import com.sell.modules.store.service.OrderService;
import com.sell.modules.store.service.OrderStatusService;
import com.sell.modules.store.vo.DeliveryOrderVo;
import com.sell.modules.sys.security.WebSocket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "骑手操作相关接口")
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
    @ApiOperation("获取骑手基本的基本个人信息")
    public Res<Delivery> info(){
        Delivery delivery = deliveryService.getInfo(UserUtils.getDeliveryId());
        return Res.success(delivery);
    }
    @PutMapping("start")
    @ApiOperation("切换骑手所处的工作状态")
    public Res<String> startWork(Integer id,boolean value){
        Delivery delivery = new Delivery();
        delivery.setId(id);
        if(value){
            delivery.setStatus("1");
        }else{
            delivery.setStatus("2");
        }
        int b = deliveryService.update(delivery);
        if(b != 1){
            return Res.errorMsg("切换开工状态失败");
        }
        return Res.successMsg("切换开工状态成功");
    }
    @GetMapping("new_list")
    @ApiOperation("获取骑手待接单列表")
    public Res<List<DeliveryOrderVo>> newOrderList(){
        List<DeliveryOrderVo> orderList = orderService.getDeliveryOrderList(null,Const.OrderStatus.SHOP_ACCEPT);

        return Res.success(orderList);
    }
    @GetMapping("accept_list")
    @ApiOperation("获取骑手已接单订单列表")
    public Res<List<DeliveryOrderVo>> takeOrderList(){
        List<DeliveryOrderVo> orderList = orderService.getDeliveryOrderList(UserUtils.getDeliveryId(),Const.OrderStatus.DELIVERY_ACCEPT);

        return Res.success(orderList);
    }
    @GetMapping("take_list")
    @ApiOperation("获取骑手已取货订单列表")
    public Res<List<DeliveryOrderVo>> deliveryOrderList(){
        List<DeliveryOrderVo> orderList = orderService.getDeliveryOrderList(UserUtils.getDeliveryId(),Const.OrderStatus.DELIVERY_TAKE);

        return Res.success(orderList);
    }

    @PutMapping("accept")
    @ApiOperation("骑手确认接受订单")
    public Res<String> accept(Long orderNo,String userId){
        if(orderNo == null){
            return Res.errorMsg("订单号参数错误");
        }
        Delivery delivery = deliveryService.getInfo(UserUtils.getDeliveryId());
        if(ObjectUtil.notEqual("1",delivery.getStatus())) {
            return Res.errorMsg("请先切换开工状态");
        }
        int result = orderService.update(Order.builder().orderNo(orderNo)
                .status(Const.OrderStatus.DELIVERY_ACCEPT).deliveryId(UserUtils.getDeliveryId()).build());
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
    @ApiOperation("骑手确认取货")
    public Res<String> take(Long orderNo,String userId){
        if(orderNo == null){
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
    @ApiOperation("骑手确认送达订单操作")
    public Res<String> fulfill(Long orderNo){
        if(orderNo == null){
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
        Integer userId = UserUtils.getUserId();
        boolean b3 = deliveryService.updateTaskNum(userId);
        if(!b3){
            return Res.errorMsg("更新骑手配送订单量失败");
        }
        String shopId = orderService.getShopId(orderNo);
        webSocket.sendOneMessage(userId.toString(),"您有一条订单已送达，祝您用餐愉快");
        webSocket.sendOneMessage(shopId,"您有一条订单已被骑手送达");
        return Res.successMsg("确认送达成功");
    }

    /**
     * 拒绝订单，分配给新的骑手
     */
    @PutMapping("reject")
    @ApiOperation("骑手拒绝分配的订单")
    public Res<String> reject(String orderNo){
        if(StringUtils.isBlank(orderNo)){
            return Res.errorMsg("订单号参数错误");
        }
        boolean b = deliveryService.updateAssign(orderNo);
        if(!b){
            return Res.errorMsg("拒绝订单失败");
        }
        return Res.successMsg("拒绝订单成功");
    }
}
