package com.sell.modules.store.controller;

import com.sell.common.Res;
import com.sell.common.utils.UserUtils;
import com.sell.modules.store.entity.ProductCategory;
import com.sell.modules.store.service.ProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/1/16 18:51
 */
@RestController
@RequestMapping("category")
@Api(tags = "商品分类相关接口")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 查找某个商家的商品分类
     * @param shopId
     * @return
     */
    @GetMapping("product")
    @ApiOperation("查找某个商家的商品分类")
    public Res getCategory(String shopId){
        if(StringUtils.isBlank(shopId)){
            shopId = UserUtils.getShopId();
        }
        List<ProductCategory> categoryList = productCategoryService.getProductCategory(shopId);
        if(categoryList == null){
            return Res.errorMsg("查找商铺分类失败");
        }
        return Res.success(categoryList);
    }
    @PostMapping("save")
    @ApiOperation("添加商品分类")
    public Res<String> saveCategory(String name){
        int result = productCategoryService.saveProductCategory(name);
        if(result > 0){
            return Res.successMsg("添加商品分类成功");
        }
        return Res.errorMsg("添加商品分类失败");
    }
    @PutMapping("update")
    @ApiOperation("修改商品分类")
    public Res<String> updateCategory(String id,String name){
        if(StringUtils.isBlank(id) && StringUtils.isBlank(name)){
            return Res.errorMsg("参数错误");
        }
        int result = productCategoryService.updateProductCategory(id, name);
        if(result > 0){
            return Res.successMsg("修改成功");
        }
        return Res.errorMsg("修改失败");
    }
    @DeleteMapping
    @ApiOperation("删除商品分类")
    public Res<String> deleteCategory(String id){
        int result = productCategoryService.deleteProductCategory(id);
        if(result > 0){
            return Res.successMsg("删除成功");
        }
        return Res.errorMsg("删除失败");
    }

}
