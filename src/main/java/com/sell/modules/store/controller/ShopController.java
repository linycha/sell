package com.sell.modules.store.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.common.utils.DateTimeUtil;
import com.sell.common.utils.PropertiesUtil;
import com.sell.modules.store.entity.Delivery;
import com.sell.modules.store.entity.Order;
import com.sell.modules.store.entity.OrderStatus;
import com.sell.modules.store.entity.Shop;
import com.sell.modules.store.service.*;
import com.sell.modules.store.vo.NewOrderVo;
import com.sell.modules.store.vo.ShopVo;
import com.sell.modules.sys.security.WebSocket;
import javassist.tools.web.Webserver;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * @author linyuc
 * @date 2020/1/20 9:46
 */
@RestController
@RequestMapping("shop")
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private FileService fileService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderStatusService orderStatusService;
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private WebSocket webSocket;

    @GetMapping("login1")
    public Res login1(){
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager)securityManager.getSessionManager();
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
        //获取当前已登录的用户session列表
        for(Session session:sessions){
            System.out.println("---------------");
            System.out.println("session: "+session);
            String str = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
            System.out.println(str);
            //清除该用户以前登录时保存的session
            //if(userName.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)))) {
            //    sessionManager.getSessionDAO().delete(session);
            //}
        }
        return Res.success("aaaacl");
    }
    /**
     * 商家搜索列表
     */
    @GetMapping("list")
    public Res<PageInfo<ShopVo>> list(String name, String categoryId, @RequestParam(defaultValue = "0") Integer sortType,
                                      @RequestParam(defaultValue = "1")Integer pageNum){
        PageInfo<ShopVo> shopList = shopService.getShopList(name,categoryId,sortType,pageNum);
        if(shopList == null){
            return Res.errorMsg("该分类下暂无相应店铺");
        }
        if(shopList.getTotal() == 0 ){
            return Res.errorMsg("未找到相应店铺");
        }
        return Res.success(shopList);
    }
    @GetMapping("info")
    public Res<Shop> info(String id){
        if(StringUtils.isBlank(id)){
            return Res.errorMsg("该店铺已关闭或不存在");
        }
        Shop shop = shopService.getShopInfo(id);
        if(shop == null){
            return Res.errorMsg("该店铺已关闭或不存在");
        }
        return Res.success(shop);
    }
    @PutMapping("update")
    public Res<String> update(Shop shop){
        int  result = shopService.updateSelective(shop);
        if(result == 0){
            return Res.errorMsg("修改店铺信息失败");
        }
        return Res.successMsg("修改店铺信息成功");
    }
    @PutMapping("update_time")
    public Res<String> updateOpeningTime(Shop shop){
        int  result = shopService.updateSelective(shop);
        if(result == 0){
            return Res.errorMsg("修改店铺营业时间失败");
        }
        return Res.successMsg("修改店铺营业时间成功");
    }
    @RequestMapping("update_logo")
    public Res<String> upload(@RequestParam(defaultValue = "logo_img",required=false) MultipartFile file,
                      HttpServletRequest request,String id){
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = fileService.upload(file,path,Const.FTPPATH_SHOP);

        if(targetFileName == null){
            return Res.errorMsg("上传图片失败");
        }
        String url = PropertiesUtil.getProperty("ftp.prefix")+Const.FTPPATH_SHOP+"/"+targetFileName;
        Shop shop = new Shop();
        shop.setId(id);
        shop.setLogoImg(url);
        int result = shopService.updateSelective(shop);
        if(result == 0){
            return Res.errorMsg("更改店铺logo失败");
        }
        return Res.success(url);
    }

    /**
     * 商家查看已支付待接单列表
     */
    @GetMapping("new_list")
    public Res<PageInfo<NewOrderVo>> newOrderList(String shopId, String orderNo, String pageNum){
        if(StringUtils.isBlank(shopId)){
            return Res.errorMsg("商家id参数错误");
        }
        PageInfo<NewOrderVo> orderList = orderService.getOrderList(shopId,orderNo,Const.OrderStatus.PAID,pageNum);
        return Res.success(orderList);
    }
    /**
     * 查看全部订单列表
     */
    @GetMapping("order_list")
    @RequiresRoles("business")
    public Res<PageInfo<NewOrderVo>> orderList(String shopId, String orderNo, String status,String pageNum){
        if(StringUtils.isBlank(shopId)){
            return Res.errorMsg("商家id参数错误");
        }
        PageInfo<NewOrderVo> orderList = orderService.getOrderList(shopId,orderNo,status,pageNum);
        return Res.success(orderList);
    }
    /**
     * 商家确认接单
     * @return orderNo
     */
    @PutMapping("accept")
    @RequiresRoles("business")
    public Res<String> accept(String orderNo){
        if(StringUtils.isBlank(orderNo)){
            return Res.errorMsg("订单号参数错误");
        }
        //分派订单给骑手,匹配最佳骑手
        Delivery delivery = deliveryService.getBest();
        Order order = new Order();
        order.setOrderNo(Long.valueOf(orderNo));
        order.setDeliverId(delivery.getId());
        order.setDeliveryName(delivery.getTrueName());
        order.setStatus(Const.OrderStatus.SHOP_ACCEPT);
        int result = orderService.update(order);
        if(result == 0){
            return Res.errorMsg("分派订单给骑手失败");
        }
        //记录订单状态
        boolean b2 = orderStatusService.saveStatus(orderNo,Const.OrderStatus.SHOP_ACCEPT);
        if(!b2){
            return Res.errorMsg("商家确认接单失败");
        }
        String userId = orderService.getUserId(orderNo);
        //把消息推送给骑手和用户
        webSocket.sendOneMessage(delivery.getId(),"您有一条新的Lin sell订单了");
        webSocket.sendOneMessage(userId,"您有一条订单已被商家接单");
        return Res.successMsg("商家确认接单成功，已派单给相应的骑手");
    }

    /**
     * 商家取消接单
     * @return orderNo
     */
    @PutMapping("cancel")
    @RequiresRoles("business")
    public Res<String> cancel(String orderNo){
        if(StringUtils.isBlank(orderNo)){
            return Res.errorMsg("订单号参数错误");
        }
        int result = orderService.updateStatusByOrderNo(orderNo,Const.OrderStatus.REFUND_SHOP);
        if(result == 0){
            return Res.errorMsg("商家取消接单失败");
        }
        boolean b2 = orderStatusService.saveStatus(orderNo,Const.OrderStatus.REFUND_SHOP);
        if(!b2){
            return Res.errorMsg("商家取消接单失败");
        }
        return Res.successMsg("商家取消接单成功");
    }

    /**
     * 获取订餐人手机号
     */
    @GetMapping("user_mobile")
    public Res<String> userMobile(String orderNo){
        String mobile = orderService.getUserMobile(orderNo);
        if(StringUtils.isBlank(mobile)){
            return Res.errorMsg("未找到该订餐人电话");
        }
        return Res.success(mobile);
    }
    /**
     * 获取骑手手机号
     */
    @GetMapping("delivery_mobile")
    public Res<String> deliveryMobile(String orderNo){
        String mobile = orderService.getDeliveryMobile(orderNo);
        if(StringUtils.isBlank(mobile)){
            return Res.errorMsg("未找到该骑手电话");
        }
        return Res.success(mobile);
    }
    @RequestMapping("hour")
    public Res<String> hour(){
        return Res.successMsg("this is new version 2.0 !!!");
    }
}
