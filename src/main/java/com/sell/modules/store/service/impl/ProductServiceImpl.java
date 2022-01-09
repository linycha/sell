package com.sell.modules.store.service.impl;

import com.github.pagehelper.PageInfo;
import com.sell.common.Const;
import com.sell.common.utils.UserUtils;
import com.sell.modules.store.dao.ProductMapper;
import com.sell.modules.store.dto.QueryProductDTO;
import com.sell.modules.store.entity.Product;
import com.sell.modules.store.service.ProductService;
import com.sell.modules.store.vo.ProductVo;
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
    public List<ProductVo> getByCategory(Integer shopId){
        return productMapper.selectProductListByCategory(shopId);
    }
    @Override
    public int update(Product product){
        return productMapper.updateByPrimaryKeySelective(product);
    }
    @Override
    public int saveProduct(Product product){
        product.setShopId(UserUtils.getUser().getShopId());
        return productMapper.insertSelective(product);
    }
    @Override
    public PageInfo<Product> getProductList(QueryProductDTO dto){
        Const.initPage(dto.getPageNum(),dto.getPageSize());
        dto.setShopId(UserUtils.getShopId());
        List<Product> productList = productMapper.selectProductList(dto);
        return new PageInfo<>(productList);
    }

    @Override
    public boolean checkStock(String name,Integer num) {
        Integer result = productMapper.checkStockByName(name, num);
        if(result >= 1){
            return true;
        }
        return false;
    }

    @Override
    public int deleteBatch(String ids) {
        return productMapper.deleteBatch(ids);
    }
}
