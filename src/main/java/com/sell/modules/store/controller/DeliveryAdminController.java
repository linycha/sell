package com.sell.modules.store.controller;

import com.github.pagehelper.PageInfo;
import com.sell.common.Res;
import com.sell.modules.store.dto.QueryDTO;
import com.sell.modules.store.entity.Delivery;
import com.sell.modules.store.service.DeliveryService;
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
@RequestMapping("deliveryAdmin")
public class DeliveryAdminController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("list")
    @ApiOperation("查找骑手列表")
    public Res<PageInfo<Delivery>> getUserList(QueryDTO dto){
        return Res.success(deliveryService.getDeliveryList(dto));
    }

    @PostMapping("saveOrUpdate")
    @ApiOperation("修改骑手信息")
    public Res<Integer> saveOrUpdate(@RequestBody Delivery delivery){
        if(delivery.getId() == null){
            return deliveryService.save(delivery);
        }
        int result = deliveryService.update(delivery);
        if(result < 1){
            return Res.errorMsg("修改用户失败");
        }
        return Res.successMsg("修改用户成功");
    }

    @DeleteMapping("delete")
    @ApiOperation("批量删除骑手")
    public Res<String> deleteUser(String ids){
        if(StringUtils.isBlank(ids)){
            return Res.errorMsg("传递的id参数不能为空");
        }
        int result = deliveryService.deleteBatch(ids);
        if(result == 0){
            return Res.errorMsg("删除失败");
        }
        return Res.successMsg("删除成功");
    }
}
