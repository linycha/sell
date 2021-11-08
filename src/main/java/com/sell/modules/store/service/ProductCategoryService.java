package com.sell.modules.store.service;

import com.sell.modules.store.entity.ProductCategory;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/1/16 18:50
 */
public interface ProductCategoryService {
    List<ProductCategory> getProductCategory(String shopId);
    int saveProductCategory(String name);

    int updateProductCategory(String id, String name);

    int deleteProductCategory(String id);

    void insertTest();

}
