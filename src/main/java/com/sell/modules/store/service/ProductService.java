package com.sell.modules.store.service;

import com.github.pagehelper.PageInfo;
import com.sell.modules.store.dto.QueryProductDTO;
import com.sell.modules.store.entity.Product;
import com.sell.modules.store.vo.ProductVo;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/1/20 16:34
 */
public interface ProductService {
    List<ProductVo> getByCategory(Integer categoryId);
    int update(Product product);
    int saveProduct(Product product);
    PageInfo<Product> getProductList(QueryProductDTO dto);
    boolean checkStock(String name,Integer num);

    /**
     * 批量假删
     * @param ids
     */
    int deleteBatch(String ids);
}
