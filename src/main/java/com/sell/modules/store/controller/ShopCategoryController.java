package com.sell.modules.store.controller;

import com.sell.common.Res;
import com.sell.modules.store.entity.ShopCategory;
import com.sell.modules.store.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/1/16 17:04
 */
@RestController
@RequestMapping("category")
public class ShopCategoryController {
    @Autowired
    private ShopCategoryService shopCategoryService;
    /**
     * 获取同级分类
     */
    @GetMapping("get_shop")
    public Res getSiblingCategory(@RequestParam(defaultValue = "0") String id){
        List<ShopCategory> categoryList = shopCategoryService.getSiblingCategory(id);
        if(categoryList == null){
            return Res.errorMsg("查找商铺分类失败");
        }
        return Res.success(categoryList);
    }


}
