package com.sell.modules.store.controller;

import com.github.pagehelper.PageInfo;
import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.common.utils.UserUtils;
import com.sell.modules.store.entity.Order;
import com.sell.modules.store.entity.OrderItem;
import com.sell.modules.store.entity.OrderStatus;
import com.sell.modules.store.service.OrderItemService;
import com.sell.modules.store.service.OrderService;
import com.sell.modules.store.service.OrderStatusService;
import com.sell.modules.store.service.ProductService;
import com.sell.modules.store.vo.NewOrderVo;
import com.sell.modules.store.vo.UserOrderVo;
import com.sell.modules.sys.entity.User;
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
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderStatusService orderStatusService;
    @PostMapping("create")
    public Res<String> create(Order order) throws Exception{
        System.out.println(order);
        //生成订单号
        Long orderNo = Const.generateOrderNo();
        List<OrderItem> orderItemList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(order.getCartStr());
        for(int i = 0; i< jsonArray.length();i++){
            JSONObject o= jsonArray.getJSONObject(i);
            OrderItem orderItem = new OrderItem();
            orderItem.setProductName(o.getString("name"));
            orderItem.setProductImg(o.getString("logoImg"));
            orderItem.setNumber(o.getInt("num"));
            orderItem.setSellPrice(new BigDecimal(o.getString("sellPrice")));
            orderItem.setShopId("3");
            orderItem.setOrderNo(orderNo);
            orderItemList.add(orderItem);
        }
        for(OrderItem item : orderItemList){
            boolean b = productService.checkStock(item.getProductName(),item.getNumber());
            if(!b){
                return Res.errorMsg(item.getProductName()+"商品库存不足");
            }
            int result = orderItemService.insert(item);
            if(result == 0){
                return Res.errorMsg("创建订单失败");
            }
        }
        //创建订单
        //order.setUserId(UserUtils.getUserId());
        order.setOrderNo(orderNo);
        order.setUserId("22");
        order.setBoxCost(new BigDecimal(order.getbCost()));
        order.setSendCost(new BigDecimal(order.getsCost()));
        order.setPayMoney(new BigDecimal(order.getMoney()));
        order.setPayMoney(new BigDecimal(order.getMoney()));
        order.setStatus("1");
        boolean b1 = orderService.save(order);
        if(!b1){
            return Res.errorMsg("创建订单失败");
        }
        //记录订单状态
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderNo(order.getOrderNo());
        orderStatus.setStatus("1");
        boolean b2 = orderStatusService.saveStatus(orderStatus);
        if(!b2){
            return Res.errorMsg("订单状态修改失败");
        }
        return Res.successMsg("订单创建成功");
    }
    @GetMapping("list_user")
    public Res<PageInfo<UserOrderVo>> listOfUser(String orderNo,String pageNum){
        //String userId = UserUtils.getUserId();
        PageInfo<UserOrderVo> orderList = orderService.getUserOrderList("12",orderNo,pageNum);
        return Res.success(orderList);
    }
}
