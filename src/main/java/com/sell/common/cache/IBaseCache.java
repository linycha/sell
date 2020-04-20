package com.sell.common.cache;

import com.sell.modules.store.entity.ShopCategory;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/4/8 20:36
 */
public interface IBaseCache {
    List<ShopCategory> getShopTopCategoryList(String categoryId);
    List<ShopCategory> getShopSiblingCategoryList(String categoryId);
    void del();
}
