package com.sell.modules.store.controller;

import com.github.pagehelper.PageInfo;
import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.common.cache.IBaseCache;
import com.sell.common.cache.RedisCache;
import com.sell.common.utils.UserUtils;
import com.sell.modules.store.dto.QueryDTO;
import com.sell.modules.store.dto.QueryProductDTO;
import com.sell.modules.store.entity.ProductCategory;
import com.sell.modules.store.entity.ShopCategory;
import com.sell.modules.store.service.ShopCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/1/16 17:04
 */
@RestController
@RequestMapping("shopCategory")
@Api(tags = "商家分类相关接口")
public class ShopCategoryController {
    @Autowired
    private IBaseCache iBaseCache;
    @Autowired
    private ShopCategoryService shopCategoryService;
    /**
     * 获取顶级分类
     */
    @GetMapping("shop_top")
    @ApiOperation("获取第一级的商家分类")
    public Res getTopCategory(@RequestParam(defaultValue = "0") Integer categoryId){
        List<ShopCategory> categoryList = iBaseCache.getShopTopCategoryList(categoryId);
        if(categoryList == null){
            return Res.errorMsg("查找商铺分类失败");
        }
        return Res.success(categoryList);
    }
    @GetMapping("del")
    public String del(){
        iBaseCache.del();
        return "删除";
    }
    @GetMapping("shop")
    @ApiOperation("获取第二级的商家分类")
    public Res getSiblingCategory(@RequestParam(defaultValue = "0") Integer categoryId){
        List<ShopCategory> categoryList = shopCategoryService.getSiblingCategory(categoryId);
        if(categoryList == null){
            return Res.errorMsg("查找商铺分类失败");
        }
        categoryList.add(0,new ShopCategory(categoryId,"全部"));
        return Res.success(categoryList);
    }

    /**
     * 查找商家分类列表
     * @param dto
     * @return
     */
    @GetMapping("list")
    @ApiOperation("查找店铺分类列表")
    public Res<PageInfo<ShopCategory>> getCategoryList(QueryDTO dto){
        return Res.success(shopCategoryService.getCategoryList(dto));
    }
    @PostMapping("save")
    @RequiresRoles("admin")
    @ApiOperation("添加商品分类")
    public Res<String> saveCategory(@RequestBody ShopCategory category){
        int result = shopCategoryService.saveShopCategory(category.getName());
        if(result > 0){
            return Res.successMsg("添加商品分类成功");
        }
        return Res.errorMsg("添加商品分类失败");
    }

    @PutMapping("update")
    @RequiresRoles("admin")
    @ApiOperation("修改店铺分类")
    public Res<String> updateCategory(@RequestBody ShopCategory category){
        if(category.getId() == null && StringUtils.isBlank(category.getName())){
            return Res.errorMsg("参数错误");
        }
        int result = shopCategoryService.updateShopCategory(category);
        if(result > 0){
            return Res.successMsg("修改成功");
        }
        return Res.errorMsg("修改失败");
    }

    @DeleteMapping("delete")
    @RequiresRoles("business")
    @ApiOperation("删除店铺分类")
    public Res<String> deleteCategory(String ids){
        if(StringUtils.isBlank(ids)){
            return Res.errorMsg("传递的id参数失败");
        }
        int result = shopCategoryService.deleteBatch(ids);
        if(result == 0){
            return Res.errorMsg("删除失败");
        }
        return Res.successMsg("删除成功");
    }


}
