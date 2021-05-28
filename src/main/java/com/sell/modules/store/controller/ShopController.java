package com.sell.modules.store.controller;

import com.github.pagehelper.PageInfo;
import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.common.utils.FTPUtil;
import com.sell.common.utils.PropertiesUtil;
import com.sell.common.utils.UserUtils;
import com.sell.modules.store.entity.Delivery;
import com.sell.modules.store.entity.Order;
import com.sell.modules.store.entity.Shop;
import com.sell.modules.store.service.*;
import com.sell.modules.store.vo.NewOrderVo;
import com.sell.modules.store.vo.ShopVo;
import com.sell.modules.sys.security.WebSocket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author linyuc
 * @date 2020/1/20 9:46
 */
@Slf4j
@RestController
@RequestMapping("shop")
@Api(tags = "商家操作相关接口")
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderStatusService orderStatusService;
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private WebSocket webSocket;
    @Autowired
    private FTPUtil ftpUtil;
    /**
     * 用户端-搜索店铺列表
     */
    @GetMapping("list")
    @ApiOperation("用户端-搜索店铺列表")
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
    @ApiOperation("商家/用户端-查询店铺信息")
    public Res<Shop> info(String shopId){
        if(StringUtils.isBlank(shopId)){
            shopId = UserUtils.getShopId();
        }
        Shop shop = shopService.getShopInfo(shopId);
        if(shop == null){
            return Res.errorMsg("该店铺已关闭或不存在");
        }
        return Res.success(shop);
    }
    @PutMapping("update")
    @ApiOperation("商家端-商家修改店铺信息")
    public Res<String> update(Shop shop){
        int  result = shopService.updateSelective(shop);
        if(result == 0){
            return Res.errorMsg("修改店铺信息失败");
        }
        return Res.successMsg("修改店铺信息成功");
    }
    @PutMapping("update_time")
    @ApiOperation("商家端-商家确认接单")
    public Res<String> updateOpeningTime(Shop shop){
        int  result = shopService.updateSelective(shop);
        if(result == 0){
            return Res.errorMsg("修改店铺营业时间失败");
        }
        return Res.successMsg("修改店铺营业时间成功");
    }
    @PutMapping("update_logo")
    @ApiOperation("商家端-修改店铺logo")
    public Res<String> upload(@RequestParam(defaultValue = "logo_img",required=false) MultipartFile file,
                      HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("upload");
        Shop shop = new Shop();
        shop.setId(UserUtils.getShopId());
        try {
            String fileName = ftpUtil.uploadFile(file,path,Const.FTP_PATH_SHOP);
            if(fileName == null){
                return Res.errorMsg("上传图片失败");
            }
            String url = PropertiesUtil.getProperty("ftp.prefix")+Const.FTP_PATH_SHOP+"/"+fileName;
            shop.setLogoImg(url);
        }catch (Exception e){
            log.info(e.getMessage());
            return Res.errorMsg("ftp上传图片出现异常");
        }
        int result = shopService.updateSelective(shop);
        if(result == 0){
            return Res.errorMsg("更改店铺logo失败");
        }
        return Res.successMsg("修改店铺logo成功");
    }

    /**
     * 商家查看已支付待接单列表
     */
    @GetMapping("new_list")
    @ApiOperation("商家端-商家查看已支付待接单列表")
    public Res<PageInfo<NewOrderVo>> newOrderList(String orderNo, String pageNum){
        String shopId = UserUtils.getShopId();
        PageInfo<NewOrderVo> orderList = orderService.getOrderList(shopId,orderNo,Const.OrderStatus.PAID,pageNum);
        return Res.success(orderList);
    }
    /**
     * 查看全部订单列表
     */
    @GetMapping("order_list")
    @RequiresRoles("business")
    @ApiOperation("商家端-查看全部订单列表")
    public Res<PageInfo<NewOrderVo>> orderList(String orderNo, String status,String pageNum){
        String shopId = UserUtils.getShopId();
        PageInfo<NewOrderVo> orderList = orderService.getOrderList(shopId,orderNo,status,pageNum);
        return Res.success(orderList);
    }
    /**
     * 商家确认接单
     * @return orderNo
     */
    @PutMapping("accept")
    @RequiresRoles("business")
    @ApiOperation("商家端-商家确认接单")
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
    @ApiOperation("商家端-商家取消接单")
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
    @ApiOperation("商家端-获取订餐人手机号")
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
    @ApiOperation("商家端-获取骑手手机号")
    public Res<String> deliveryMobile(String orderNo){
        String mobile = orderService.getDeliveryMobile(orderNo);
        if(StringUtils.isBlank(mobile)){
            return Res.errorMsg("未找到该骑手电话");
        }
        return Res.success(mobile);
    }
    @GetMapping("hour")
    public Res<String> hour(){
        return Res.successMsg("this is new version 3.3 !!!"+new Date());
    }
}
