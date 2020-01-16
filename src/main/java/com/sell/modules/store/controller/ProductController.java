package com.sell.modules.store.controller;

import com.github.pagehelper.PageInfo;
import com.sell.common.Res;
import com.sell.modules.store.entity.Product;
import com.sell.modules.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linyuc
 * @date 2019/12/20 15:28
 */
@RestController
@RequestMapping("/user/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    public Res<Product> detail(String productId){
        return productService.getProductDetail(productId);
    }
    public Res<PageInfo> list(@RequestParam(required = false)String keyword,
                                         @RequestParam(required = false)Integer categoryId,
                                         @RequestParam(defaultValue = "1")int pageNum,
                                         @RequestParam(defaultValue = "10")int pageSize){
        return null;

    }
}
