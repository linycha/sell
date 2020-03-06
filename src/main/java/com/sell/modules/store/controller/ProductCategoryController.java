package com.sell.modules.store.controller;

import com.sell.common.Res;
import com.sell.modules.store.entity.ProductCategory;
import com.sell.modules.store.service.ProductCategoryService;
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
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 查找某个商家的商品分类
     * @param id
     * @return
     */
    @GetMapping("product")
    public Res getCategory(String id){
        List<ProductCategory> categoryList = productCategoryService.getProductCategory(id);
        if(categoryList == null){
            return Res.errorMsg("查找商铺分类失败");
        }
        return Res.success(categoryList);
    }
    @PostMapping("save")
    public Res<String> saveCategory(String name){
        int result = productCategoryService.saveProductCategory(name);
        if(result > 0){
            return Res.successMsg("添加商品分类成功");
        }
        return Res.errorMsg("添加商品分类失败");
    }
    @PutMapping("update")
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
    public Res<String> deleteCategory(String id){
        int result = productCategoryService.deleteProductCategory(id);
        if(result > 0){
            return Res.successMsg("删除成功");
        }
        return Res.errorMsg("删除失败");
    }

}
