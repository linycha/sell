package com.sell.modules.store.service.impl;

import com.github.pagehelper.PageInfo;
import com.sell.common.Const;
import com.sell.common.utils.UserUtils;
import com.sell.modules.store.dao.ProductCategoryMapper;
import com.sell.modules.store.dto.QueryProductDTO;
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
        QueryProductDTO dto = new QueryProductDTO();
        dto.setShopId(shopId);
        return productCategoryMapper.selectCategoryList(dto);
    }

    @Override
    public PageInfo<ProductCategory> getCategoryList(QueryProductDTO dto) {
        Const.initPage(dto.getPageNum(),dto.getPageSize());
        List<ProductCategory> list = productCategoryMapper.selectCategoryList(dto);
        return new PageInfo<>(list);
    }

    @Override
    public int saveProductCategory(String name){
        if(StringUtils.isBlank(name)){
            return -1;
        }
        ProductCategory productCategory = new ProductCategory();
        //通过当前登录用户查找shopId
        productCategory.setShopId(UserUtils.getShopId());
        productCategory.setName(name);
        return this.productCategoryMapper.insertSelective(productCategory);
    }
    @Override
    public int updateProductCategory(ProductCategory category){
        return productCategoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public void insertTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setShopId("123");
        productCategory.setName("测试分类");
        productCategoryMapper.insert(productCategory);
        int i = 1/0;
    }

    @Override
    public int deleteBatch(String ids) {
        return productCategoryMapper.deleteBatch(ids);
    }

}
