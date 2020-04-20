package com.sell.modules.store.service;

import com.sell.modules.store.entity.ShopCategory;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/1/16 17:12
 */
public interface ShopCategoryService {

    List<ShopCategory> getSiblingCategory(String categoryid);
}
