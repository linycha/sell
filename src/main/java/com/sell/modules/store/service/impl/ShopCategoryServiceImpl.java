package com.sell.modules.store.service.impl;

import com.sell.modules.store.dao.ShopCategoryMapper;
import com.sell.modules.store.entity.ShopCategory;
import com.sell.modules.store.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/1/16 17:13
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryMapper shopCategoryMapper;
    @Override
    public List<ShopCategory> getSiblingCategory(String id){
        return shopCategoryMapper.selectSiblingCategory(id);
    }
}
