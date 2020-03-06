package com.sell.modules.store.controller;

import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.common.utils.UserUtils;
import com.sell.modules.store.entity.Shipping;
import com.sell.modules.store.service.ShippingService;
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
public class ShippingController {
    @Autowired
    private ShippingService shippingService;
    @GetMapping("list")
    public Res<List<Shipping>> list(){
        String userId = UserUtils.getUserId();
        List<Shipping> shippingList = shippingService.getShippingList(userId);
        if(shippingList == null){
            return Res.errorMsg("请添加收获地址");
        }
        return Res.success(shippingList);
    }

    /**
     * 获取用户默认地址
     * @return
     */
    @GetMapping("default")
    public Res<Shipping> defaultShipping(){
        //String userId = UserUtils.getUserId()
        Shipping shipping = shippingService.getDefault("12");
        if(shipping == null){
            return Res.errorMsg("请选择收货地址");
        }
        return Res.success(shipping);
    }
    @PostMapping("save")
    public Res<String> save(Shipping shipping){
        if(StringUtils.isBlank(shipping.getUserId())){
            return Res.errorMsg("用户id为空");
        }
        int result = shippingService.save(shipping);
        if(result == 0){
            Res.errorMsg("保存失败");
        }
        return Res.successMsg("保存成功");
    }
    @PutMapping("update")
    public Res<String> update(Shipping shipping){
        System.out.println(shipping);
        int result = shippingService.update(shipping);
        if(result == 0){
            return Res.errorMsg("修改失败");
        }
        return Res.successMsg("修改成功");
    }
    @DeleteMapping("delete")
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
