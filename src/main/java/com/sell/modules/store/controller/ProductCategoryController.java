package com.sell.modules.store.controller;

import com.github.pagehelper.PageInfo;
import com.sell.common.Res;
import com.sell.common.utils.UserUtils;
import com.sell.modules.store.dto.QueryProductDTO;
import com.sell.modules.store.entity.ProductCategory;
import com.sell.modules.store.service.ProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
    public Res<List<ProductCategory>> getCategory(Integer shopId){
        if(shopId == null){
            shopId = UserUtils.getShopId();
        }
        List<ProductCategory> categoryList = productCategoryService.getProductCategory(shopId);
        return Res.success(categoryList);
    }
    /**
     * 商家查询自家店铺的商品分类
     * @param dto
     * @return
     */
    @GetMapping("list")
    @RequiresRoles("business")
    @ApiOperation("查找某个商家的商品分类")
    public Res<PageInfo<ProductCategory>> getCategoryList(QueryProductDTO dto){
        dto.setShopId(UserUtils.getShopId());
        return Res.success(productCategoryService.getCategoryList(dto));
    }
    @PostMapping("save")
    @RequiresRoles("business")
    @ApiOperation("添加商品分类")
    public Res<String> saveCategory(@RequestBody ProductCategory category){
        int result = productCategoryService.saveProductCategory(category.getName());
        if(result > 0){
            return Res.successMsg("添加商品分类成功");
        }
        return Res.errorMsg("添加商品分类失败");
    }
    @PutMapping("update")
    @RequiresRoles("business")
    @ApiOperation("修改商品分类")
    public Res<String> updateCategory(@RequestBody ProductCategory category){
        if(category.getId() == null && StringUtils.isBlank(category.getName())){
            return Res.errorMsg("参数错误");
        }
        int result = productCategoryService.updateProductCategory(category);
        if(result > 0){
            return Res.successMsg("修改成功");
        }
        return Res.errorMsg("修改失败");
    }

    @DeleteMapping("delete")
    @RequiresRoles("business")
    @ApiOperation("删除商品分类")
    public Res<String> deleteCategory(String ids){
        if(StringUtils.isBlank(ids)){
            return Res.errorMsg("传递的id参数失败");
        }
        int result = productCategoryService.deleteBatch(ids);
        if(result == 0){
            return Res.errorMsg("删除失败");
        }
        return Res.successMsg("删除成功");
    }

}
