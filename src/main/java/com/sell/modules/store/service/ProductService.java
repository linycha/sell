package com.sell.modules.store.service;

import com.github.pagehelper.PageInfo;
import com.sell.modules.store.entity.Product;
import com.sell.modules.store.vo.ProductVo;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/1/20 16:34
 */
public interface ProductService {
    List<ProductVo> getByCategory(String categoryId);
    int update(Product product);
    int saveProduct(Product product);
    PageInfo<Product> getProductList(String categoryId, String name, String status,String pageNum);
    boolean checkStock(String name,Integer num);
}
