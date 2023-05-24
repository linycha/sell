package com.sell.modules.store.service;

import com.github.pagehelper.PageInfo;
import com.sell.modules.store.dto.QueryDTO;
import com.sell.modules.store.dto.QueryProductDTO;
import com.sell.modules.store.entity.ProductCategory;
import com.sell.modules.store.entity.ShopCategory;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/1/16 17:12
 */
public interface ShopCategoryService {

    List<ShopCategory> getSiblingCategory(Integer categoryId);
    PageInfo<ShopCategory> getCategoryList(QueryDTO dto);
    int saveShopCategory(String name);

    int updateShopCategory(ShopCategory category);

    /**
     * 批量假删
     * @param ids
     */
    int deleteBatch(String ids);
}
