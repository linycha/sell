package com.sell.modules.store.service.impl;

import com.sell.common.Const;
import com.sell.common.IdGenerate;
import com.sell.common.utils.UserUtils;
import com.sell.modules.store.dao.ProductCategoryMapper;
import com.sell.modules.store.dao.ProductMapper;
import com.sell.modules.store.entity.ProductCategory;
import com.sell.modules.store.service.ProductCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/1/16 18:51
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    /**
     * 查询指定商家的商品分类
     * @param shopId
     * @return
     */
    @Override
    public List<ProductCategory> getProductCategory(String shopId){
        return productCategoryMapper.selectCategoryByShopId(shopId);
    }
    @Override
    public int saveProductCategory(String name){
        if(StringUtils.isBlank(name)){
            return -1;
        }
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(IdGenerate.uuid());
        productCategory.setDelFlag("0");
        //通过当前登录用户查找shopId
        productCategory.setShopId(UserUtils.getUserId());
        return this.productCategoryMapper.insertSelective(productCategory);
    }
    @Override
    public int updateProductCategory(String id, String name){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(id);
        productCategory.setName(name);
        return productCategoryMapper.updateByPrimaryKeySelective(productCategory);
    }
    @Override
    public int deleteProductCategory(String id){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(id);
        productCategory.setDelFlag(Const.DELETED);
        return productCategoryMapper.updateByPrimaryKeySelective(productCategory);
    }

}
