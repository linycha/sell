package com.sell.modules.store.controller;

import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.common.cache.IBaseCache;
import com.sell.common.cache.RedisCache;
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
    private IBaseCache iBaseCache;
    @Autowired
    private ShopCategoryService shopCategoryService;
    /**
     * 获取顶级分类
     */
    @GetMapping("shop_top")
    public Res getTopCategory(@RequestParam(defaultValue = "0") String categoryId){
        List<ShopCategory> categoryList = iBaseCache.getShopTopCategoryList(categoryId);
        if(categoryList == null){
            return Res.errorMsg("查找商铺分类失败");
        }
        return Res.success(categoryList);
    }
    @RequestMapping("del")
    public String del(){
        iBaseCache.del();
        return "删除";
    }
    @GetMapping("shop")
    public Res getSiblingCategory(@RequestParam(defaultValue = "0") String categoryId){
        List<ShopCategory> categoryList = shopCategoryService.getSiblingCategory(categoryId);
        if(categoryList == null){
            return Res.errorMsg("查找商铺分类失败");
        }
        categoryList.add(0,new ShopCategory(categoryId,"全部"));
        return Res.success(categoryList);
    }



}
