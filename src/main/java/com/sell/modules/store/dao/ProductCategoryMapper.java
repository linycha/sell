package com.sell.modules.store.dao;

import com.sell.modules.store.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProductCategory record);

    int insertSelective(ProductCategory record);

    ProductCategory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProductCategory record);

    int updateByPrimaryKey(ProductCategory record);

    List<ProductCategory> selectCategoryByShopId(String shopId);
}