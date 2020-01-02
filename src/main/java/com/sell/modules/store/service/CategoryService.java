package com.sell.modules.store.service;

import com.sell.common.ServerResponse;
import com.sell.modules.store.entity.Category;

import java.util.List;

/**
 * @author linyuc
 * @date 2019/12/19 11:53
 */
public interface CategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);
    ServerResponse updateCategoryName(Integer categoryId,String categoryName);
    ServerResponse<List<Category>> getSiblingCategory(Integer categoryId);
}
