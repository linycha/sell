package com.sell.modules.store.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.common.utils.PropertiesUtil;
import com.sell.modules.store.entity.Delivery;
import com.sell.modules.store.entity.Order;
import com.sell.modules.store.entity.OrderStatus;
import com.sell.modules.store.entity.Shop;
import com.sell.modules.store.service.*;
import com.sell.modules.store.vo.NewOrderVo;
import com.sell.modules.store.vo.ShopVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author linyuc
 * @date 2020/1/20 9:46
 */
@RestController
@RequestMapping("/shop")
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
    @RequestMapping("update_logo")
    public Res upload(@RequestParam(defaultValue = "logo_img",required=false) MultipartFile file,
                      HttpServletRequest request,String id){
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = fileService.upload(file,path,Const.FTPPATH_SHOP);

        if(targetFileName == null){
            return Res.errorMsg("上传失败");
        }
        String url = PropertiesUtil.getProperty("ftp.prefix")+Const.FTPPATH_SHOP+"/"+targetFileName;
        Shop shop = new Shop();
        shop.setId(id);
        shop.setLogoImg(url);
        int result = shopService.updateSelective(shop);
        if(result == 0){
            return Res.errorMsg("更改失败");
        }
        return Res.success("更改成功");
    }

    /**
     * 查看待接单列表
     */
    @GetMapping("new_list")
    public Res<PageInfo<NewOrderVo>> newList(String shopId, String orderNo, String pageNum){
        if(StringUtils.isBlank(shopId)){
            return Res.errorMsg("商家id参数错误");
        }
        PageInfo<NewOrderVo> orderList = orderService.getNewOrderList(shopId,orderNo,pageNum);
        return Res.success(orderList);
    }
    /**
     * 商家确认接单
     * @return orderNo
     */
    @PutMapping("accept")
    public Res<String> accept(String orderNo){
        if(StringUtils.isBlank(orderNo)){
            return Res.errorMsg("订单号参数错误");
        }
        int result = orderService.updateStatusByOrderNo(orderNo,Const.OrderStatus.SHOP_ACCEPT);
        if(result == 0){
            return Res.errorMsg("商家确认接单失败");
        }
        OrderStatus orderStatus = new OrderStatus();
        if(!StringUtils.isBlank(orderNo)){
            orderStatus.setOrderNo(Long.valueOf(orderNo));
        }
        orderStatus.setStatus(Const.OrderStatus.SHOP_ACCEPT);
        boolean b2 = orderStatusService.saveStatus(orderStatus);
        if(!b2){
            return Res.errorMsg("商家确认接单失败");
        }
        //指派订单给骑手
        boolean b = deliveryService.assign(orderNo);
        if(!b){
            return Res.errorMsg("派单给相应的骑手失败");
        }
        return Res.successMsg("商家确认接单成功，已派单给相应的骑手");
    }

    /**
     * 商家取消接单
     * @return orderNo
     */
    @PutMapping("cancel")
    public Res<String> cancel(String orderNo){
        if(StringUtils.isBlank(orderNo)){
            return Res.errorMsg("订单号参数错误");
        }
        int result = orderService.updateStatusByOrderNo(orderNo,Const.OrderStatus.REFUND_SHOP);
        if(result == 0){
            return Res.errorMsg("商家取消接单失败");
        }
        OrderStatus orderStatus = new OrderStatus();
        if(!StringUtils.isBlank(orderNo)){
            orderStatus.setOrderNo(Long.valueOf(orderNo));
        }
        orderStatus.setStatus(Const.OrderStatus.REFUND_SHOP);
        boolean b2 = orderStatusService.saveStatus(orderStatus);
        if(!b2){
            return Res.errorMsg("商家取消接单失败");
        }
        return Res.successMsg("商家取消接单成功");
    }
}
