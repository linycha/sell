package com.sell.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.sell.common.Res;
import com.sell.modules.sys.entity.Location;
import com.sell.modules.sys.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author linyuc
 * @date 2020/9/2 18:03
 */
@RestController
@RequestMapping("location")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @PostMapping("save")
    public Res<String> save(Location location){
        System.out.println(location.toString());
        location.setLat(new BigDecimal(location.getLatitude()));
        location.setLng(new BigDecimal(location.getLongitude()));
        int result = locationService.save(location);
        if(result == 0){
            return Res.errorMsg("保存定位信息失败");
        }
        return Res.successMsg("保存定位信息成功");
    }
    @GetMapping("list")
    public Res<PageInfo<Location>> list(String pageNum){
        PageInfo<Location> list = locationService.getList(pageNum);
        if(list.getSize() == 0){
            return Res.errorMsg("未找到相关信息");
        }
        return Res.success(list);
    }
}
