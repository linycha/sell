package com.sell.modules.store.controller;

import com.github.pagehelper.PageInfo;
import com.sell.common.Res;
import com.sell.modules.store.dto.QueryDTO;
import com.sell.modules.store.entity.Delivery;
import com.sell.modules.store.entity.Shop;
import com.sell.modules.store.service.DeliveryService;
import com.sell.modules.store.service.ShopService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员端，骑手管理相关接口
 * @author linyuc
 * @Description TODO
 * @date 2022/2/28 23:02
 */
@RestController
@RequestMapping("shopAdmin")
public class ShopAdminController {

    @Autowired
    private ShopService shopService;

    @GetMapping("list")
    @ApiOperation("查找商家店铺列表")
    public Res<PageInfo<Shop>> getUserList(QueryDTO dto){
        return Res.success(shopService.queryShopList(dto));
    }

    @PostMapping("saveOrUpdate")
    @ApiOperation("修改商家店铺信息")
    public Res<Integer> saveOrUpdate(@RequestBody Shop shop){
        if(shop.getId() == null){
            return shopService.save(shop);
        }
        int result = shopService.updateSelective(shop);
        if(result < 1){
            return Res.errorMsg("修改店铺失败");
        }
        return Res.successMsg("修改店铺成功");
    }

    @DeleteMapping("delete")
    @ApiOperation("批量删除店铺")
    public Res<String> deleteUser(String ids){
        if(StringUtils.isBlank(ids)){
            return Res.errorMsg("传递的id参数不能为空");
        }
        int result = shopService.deleteBatch(ids);
        if(result == 0){
            return Res.errorMsg("删除失败");
        }
        return Res.successMsg("删除成功");
    }
}
