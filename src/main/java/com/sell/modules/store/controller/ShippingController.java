package com.sell.modules.store.controller;

import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.common.utils.UserUtils;
import com.sell.modules.store.entity.Shipping;
import com.sell.modules.store.service.ShippingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/2/5 16:22
 */
@RestController
@RequestMapping("shipping")
@Api(tags = "收货地址相关接口")
public class ShippingController {
    @Autowired
    private ShippingService shippingService;
    @GetMapping("list")
    @ApiOperation("查看收货地址列表")
    public Res<List<Shipping>> list(){
        String userId = UserUtils.getUser().getId();
        List<Shipping> shippingList = shippingService.getShippingList(userId);
        if(shippingList == null){
            return Res.errorMsg("请添加收获地址");
        }
        return Res.success(shippingList);
    }

    /**
     * 用户生成订单时获取用户默认收货地址
     * @return
     */
    @GetMapping("default")
    @ApiOperation("用户生成订单时获取用户默认收货地址")
    public Res<Shipping> defaultShipping(){
        String userId = UserUtils.getUser().getId();
        Shipping shipping = shippingService.getDefault(userId);
        if(shipping == null){
            return Res.errorMsg("请选择收货地址");
        }
        return Res.success(shipping);
    }
    @PostMapping("save")
    @ApiOperation("保存收货地址信息")
    public Res<String> save(Shipping shipping){
        String userId = UserUtils.getUser().getId();
        if("1".equals(shipping.getIsDefault())){
            shippingService.updateDefault(userId);
        }
        shipping.setUserId(userId);
        int result = shippingService.save(shipping);
        if(result == 0){
            Res.errorMsg("保存失败");
        }
        return Res.successMsg("保存成功");
    }
    @PutMapping("update")
    @ApiOperation("修改收货地址信息")
    public Res<String> update(Shipping shipping){
        System.out.println(shipping);
        if("1".equals(shipping.getIsDefault())){
            shippingService.updateDefault(UserUtils.getUser().getId());
        }
        int result = shippingService.update(shipping);
        if(result == 0){
            return Res.errorMsg("修改失败");
        }
        return Res.successMsg("修改成功");
    }
    @DeleteMapping("delete")
    @ApiOperation("删除收货地址信息")
    public Res<String> delete(String id){
        Shipping shipping = new Shipping();
        shipping.setId(id);
        shipping.setDelFlag(Const.DELETED);
        int result = shippingService.update(shipping);
        if(result == 0){
            return Res.errorMsg("删除失败");
        }
        return Res.successMsg("删除成功");
    }
}
