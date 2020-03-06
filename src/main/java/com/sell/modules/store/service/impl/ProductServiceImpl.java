package com.sell.modules.store.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sell.common.Const;
import com.sell.common.IdGenerate;
import com.sell.modules.store.dao.ProductMapper;
import com.sell.modules.store.entity.Product;
import com.sell.modules.store.service.ProductService;
import com.sell.modules.store.vo.ProductVo;
import com.sell.modules.store.vo.ShopVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/1/20 16:35
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<ProductVo> getByCategory(String categoryId){
        return productMapper.selectProductListByCategory(categoryId);
    }
    @Override
    public int update(Product product){
        return productMapper.updateByPrimaryKeySelective(product);
    }
    @Override
    public int saveProduct(Product product){
        product.setId(IdGenerate.uuid());
        product.setDelFlag("0");
        return productMapper.insertSelective(product);
    }
    @Override
    public PageInfo<Product> getProductList(String categoryId,String name,String status,String pageNum){
        int page = Const.PAGE_DEFAULT_NUM;
        if(!StringUtils.isBlank(pageNum)){
            page = Integer.parseInt(pageNum);
        }
        PageHelper.startPage(page,Const.PAGE_DEFAULT_SIZE2);
        List<Product> productList = productMapper.selectProductList(categoryId,name,status);
        PageInfo<Product> pageResult = new PageInfo<>(productList);
        return pageResult;
    }

    @Override
    public boolean checkStock(String name,Integer num) {
        Integer resule = productMapper.checkStockByName(name, num);
        if(resule >= 1){
            return true;
        }
        return false;
    }
}
