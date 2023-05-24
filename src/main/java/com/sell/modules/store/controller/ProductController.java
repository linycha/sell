package com.sell.modules.store.controller;

import com.github.pagehelper.PageInfo;
import com.sell.common.Res;
import com.sell.common.utils.FileUploadUtil;
import com.sell.modules.store.dto.QueryProductDTO;
import com.sell.modules.store.entity.Product;
import com.sell.modules.store.entity.ProductCategory;
import com.sell.modules.store.service.ProductCategoryService;
import com.sell.modules.store.service.ProductService;
import com.sell.modules.store.vo.ProductVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 * @author linyuc
 * @date 2020/1/20 16:34
 */
@Slf4j
@RestController
@RequestMapping("product")
@Api(tags = "商品相关接口")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private FileUploadUtil fileUploadUtil;

    @GetMapping("list")
    @ApiOperation("用户查询商品列表")
    public Res<List<List<ProductVo>>> list(Integer shopId){
        if(shopId == null){
            return Res.errorMsg("该分类无商品信息");
        }
        List<ProductCategory> categoryList = productCategoryService.getProductCategory(shopId);
        List<ProductVo> productList = productService.getByCategory(shopId);
        List<List<ProductVo>> productLists = new ArrayList<>();
        categoryList.forEach(e->{
            List<ProductVo> list = new ArrayList<>();
            for(ProductVo item : productList){
                if(item.getCategoryId().equals(e.getId())){
                    item.setCategoryName(e.getName());
                    list.add(item);
                }
            }
            if(CollectionUtils.isNotEmpty(list)){
                productLists.add(list);
            }
        });
        if(productLists.size() == 0){
            return Res.errorMsg("该分类无商品信息");
        }
        return Res.success(productLists);
    }
    @GetMapping("shop_list")
    @ApiOperation("商家查询商品列表")
    public Res<PageInfo<Product>> getProductList(QueryProductDTO dto){
        PageInfo<Product> productList = productService.getProductList(dto);
        return Res.success(productList);
    }
    @PostMapping("save")
    @ApiOperation("新增保存商品信息")
    public Res<String> save(Product product){
        if(!StringUtils.isBlank(product.getOrigin())){
            product.setOriginPrice(new BigDecimal(product.getOrigin()));
        }
        if(!StringUtils.isBlank(product.getSell())){
            product.setSellPrice(new BigDecimal(product.getSell()));
        }
        if(product.getFile() != null){
            try {
                String newFileName = fileUploadUtil.uploadFile(product.getFile());
                product.setLogoImg(newFileName);
            } catch (IOException e) {
                e.printStackTrace();
                return Res.errorMsg("上传文件失败");
            }
        }
        int result = productService.saveProduct(product);
        if(result == 0){
            return Res.errorMsg("保存商品信息失败");
        }
        return Res.successMsg("保存商品信息成功");
    }

    @PutMapping("update")
    @ApiOperation("修改商品信息")
    public Res<String> update(Product product){
        if(!StringUtils.isBlank(product.getOrigin())){
            product.setOriginPrice(new BigDecimal(product.getOrigin()));
        }
        if(!StringUtils.isBlank(product.getSell())){
            product.setSellPrice(new BigDecimal(product.getSell()));
        }
        if(product.getFile() != null){
            try {
                String newFileName = fileUploadUtil.uploadFile(product.getFile());
                product.setLogoImg(newFileName);
            } catch (IOException e) {
                e.printStackTrace();
                return Res.errorMsg("上传文件失败");
            }
        }
        int result = productService.update(product);
        if(result == 0){
            return Res.errorMsg("修改商品信息失败");
        }
        return Res.successMsg("修改商品信息成功");
    }

    @DeleteMapping("delete")
    @ApiOperation("删除商品信息")
    public Res<String> delete(String ids){
        if(StringUtils.isBlank(ids)){
            return Res.errorMsg("传递的id参数失败");
        }
        int result = productService.deleteBatch(ids);
        if(result == 0){
            return Res.errorMsg("删除失败");
        }
        return Res.successMsg("删除成功");
    }

}
