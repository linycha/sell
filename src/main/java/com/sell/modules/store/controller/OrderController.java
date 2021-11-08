package com.sell.modules.store.controller;

import com.github.pagehelper.PageInfo;
import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.common.utils.UserUtils;
import com.sell.modules.store.entity.Order;
import com.sell.modules.store.entity.OrderItem;
import com.sell.modules.store.entity.OrderStatus;
import com.sell.modules.store.entity.Shipping;
import com.sell.modules.store.service.*;
import com.sell.modules.store.vo.UserOrderVo;
import com.sell.modules.sys.security.WebSocket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author linyuc
 * @date 2020/3/1 0:09
 */
@RestController
@RequestMapping("/order")
@Api(tags = "订单模块相关接口")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderStatusService orderStatusService;
    @Autowired
    private ShippingService shippingService;
    @Autowired
    private WebSocket webSocket;
    @PostMapping("create")
    @ApiOperation("用户提交创建订单")
    public Res<String> create(Order order) throws Exception{
        System.out.println(order);
        //生成订单号
        Long orderNo = Const.generateOrderNo();
        List<OrderItem> orderItemList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(order.getCartStr());
        //获取前端传过来的商品信息
        for(int i = 0; i< jsonArray.length();i++){
            JSONObject o= jsonArray.getJSONObject(i);
            OrderItem orderItem = new OrderItem();
            orderItem.setProductName(o.getString("name"));
            orderItem.setProductImg(o.getString("logoImg"));
            orderItem.setNumber(o.getInt("num"));
            orderItem.setSellPrice(new BigDecimal(o.getString("sellPrice")));
            orderItem.setShopId(order.getShopId());
            orderItem.setOrderNo(orderNo);
            orderItemList.add(orderItem);
        }
        //校验库存
        for(OrderItem item : orderItemList){
            boolean b = productService.checkStock(item.getProductName(),item.getNumber());
            if(!b){
                return Res.errorMsg(item.getProductName()+"商品库存不足");
            }
            int result = orderItemService.insert(item);
            //扣库存
            if(result == 0){
                return Res.errorMsg("创建订单失败");
            }
        }
        //创建订单
        order.setUserId(UserUtils.getUser().getId());
        order.setOrderNo(orderNo);
        order.setBoxCost(new BigDecimal(order.getBCost()));
        order.setSendCost(new BigDecimal(order.getSCost()));
        order.setPayMoney(new BigDecimal(order.getMoney()));
        order.setStatus(Const.OrderStatus.PAID);
        boolean b1 = orderService.save(order);
        if(!b1){
            return Res.errorMsg("创建订单失败");
        }
        //记录订单状态
        boolean b2 = orderStatusService.saveStatus(order.getOrderNo().toString(),Const.OrderStatus.PAID);
        if(!b2){
            return Res.errorMsg("订单状态修改失败");
        }
        //webSocket消息推送通知商家
        webSocket.sendOneMessage(order.getShopId(), "您有一条新的Lin sell订单了");
        return Res.successMsg("订单创建成功");
    }
    @GetMapping("list_user")
    public Res<PageInfo<UserOrderVo>> listOfUser(String orderNo,String pageNum){
        String userId = UserUtils.getUser().getId();
        PageInfo<UserOrderVo> orderList = orderService.getUserOrderList(userId,orderNo,pageNum);
        return Res.success(orderList);
    }
    @GetMapping("detail")
    @ApiOperation("用户获取订单详情")
    public Res<Order> detail(String orderNo){
        if(StringUtils.isBlank(orderNo)){
            return Res.errorMsg("订单号参数错误");
        }
        Order order = orderService.getOrderDetail(orderNo);
        if(order == null){
            return Res.errorMsg("该订单号不存在");
        }
        List<OrderItem> orderItemList = orderItemService.getList(orderNo);
        order.setOrderItemList(orderItemList);
        Shipping shipping = shippingService.getByOrderNo(orderNo);
        order.setShippingAddress(shipping.getAddress());
        order.setShippingName(shipping.getName()+shipping.getTel());
        return Res.success(order);
    }
    @GetMapping("status")
    @ApiOperation("用户查看订单配送状态")
    public Res<List<OrderStatus>> statusList(String orderNo){
        if(StringUtils.isBlank(orderNo)){
            return Res.errorMsg("订单号参数错误");
        }
        List<OrderStatus> orderStatusList = orderStatusService.getList(orderNo);
        return Res.success(orderStatusList);
    }
    /**
     * 获取骑手手机号
     */
    @GetMapping("dmobile")
    @ApiOperation("用户获取骑手手机号")
    public Res<String> deliveryMobile(String orderNo){
        String mobile = orderService.getDeliveryMobile(orderNo);
        if(StringUtils.isBlank(mobile)){
            return Res.errorMsg("未找到该骑手电话");
        }
        return Res.success(mobile);
    }
}
