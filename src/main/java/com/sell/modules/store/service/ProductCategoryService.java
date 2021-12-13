package com.sell.modules.store.service;

import com.github.pagehelper.PageInfo;
import com.sell.modules.store.dto.QueryProductDTO;
import com.sell.modules.store.entity.ProductCategory;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/1/16 18:50
 */
public interface ProductCategoryService {
    List<ProductCategory> getProductCategory(String shopId);

    /**
     * 商家查询自家店铺的商品分类
     * @param dto dto
     * @return
     */
    PageInfo<ProductCategory> getCategoryList(QueryProductDTO dto);
    int saveProductCategory(String name);

    int updateProductCategory(ProductCategory category);

    void insertTest();

    /**
     * 批量假删
     * @param ids
     */
    int deleteBatch(String ids);

}
