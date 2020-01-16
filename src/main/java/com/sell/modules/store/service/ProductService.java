package com.sell.modules.store.service;

import com.github.pagehelper.PageInfo;
import com.sell.common.Res;
import com.sell.modules.store.entity.Product;

/**
 * @author linyuc
 * @date 2019/12/20 10:02
 */
public interface ProductService {
    Res saveOrUpdateProduct(Product product);
    Res<String> setStatus(String productId, Integer status);
    Res getProductList(Integer pageNum, Integer pageSize);
    Res<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize);
    Res<Product> getProductDetail(String productId);
}
