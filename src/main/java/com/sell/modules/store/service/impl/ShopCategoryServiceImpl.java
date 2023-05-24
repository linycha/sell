package com.sell.modules.store.service.impl;

import com.github.pagehelper.PageInfo;
import com.sell.common.Const;
import com.sell.modules.store.dao.ShopCategoryMapper;
import com.sell.modules.store.dto.QueryDTO;
import com.sell.modules.store.entity.ProductCategory;
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
    public List<ShopCategory> getSiblingCategory(Integer id){
        return shopCategoryMapper.selectSiblingCategory(id);
    }

    @Override
    public PageInfo<ShopCategory> getCategoryList(QueryDTO dto) {
        Const.initPage(dto.getCurrent(),dto.getSize());
        List<ShopCategory> list = shopCategoryMapper.selectShopCategoryList(dto);
        return new PageInfo<>(list);
    }

    @Override
    public int saveShopCategory(String name) {
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setName(name);
        return shopCategoryMapper.insertSelective(shopCategory);
    }

    @Override
    public int updateShopCategory(ShopCategory category) {
        return shopCategoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public int deleteBatch(String ids) {
        return 0;
    }
}
