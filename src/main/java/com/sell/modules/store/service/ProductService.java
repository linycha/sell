package com.sell.modules.store.service;

import com.github.pagehelper.PageInfo;
import com.sell.common.ServerResponse;
import com.sell.modules.store.entity.Product;

/**
 * @author linyuc
 * @date 2019/12/20 10:02
 */
public interface ProductService {
    ServerResponse saveOrUpdateProduct(Product product);
    ServerResponse<String> setStatus(String productId, Integer status);
    ServerResponse getProductList(Integer pageNum, Integer pageSize);
    ServerResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize);
    ServerResponse<Product> getProductDetail(String productId);
}
