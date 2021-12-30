package com.sell.modules.store.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.lly835.bestpay.rest.type.Delete;
import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.common.utils.FTPUtil;
import com.sell.common.utils.PropertiesUtil;
import com.sell.modules.store.dto.QueryProductDTO;
import com.sell.modules.store.entity.Product;
import com.sell.modules.store.entity.ProductCategory;
import com.sell.modules.store.entity.Shipping;
import com.sell.modules.store.entity.Shop;
import com.sell.modules.store.service.FileService;
import com.sell.modules.store.service.ProductCategoryService;
import com.sell.modules.store.service.ProductService;
import com.sell.modules.store.vo.ProductVo;
import com.sell.modules.store.vo.ShopVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private FTPUtil ftpUtil;

    @GetMapping("list")
    @ApiOperation("用户查询商品列表")
    public Res<List<List<ProductVo>>> list(String shopId){
        if(StringUtils.isBlank(shopId)){
            return Res.errorMsg("该分类无商品信息");
        }
        List<ProductCategory> categoryList = productCategoryService.getProductCategory(shopId);
        List<List<ProductVo>> productLists = new ArrayList<>();
        for(ProductCategory category :categoryList){
            List<ProductVo> productList = productService.getByCategory(category.getId());
            productLists.add(productList);
        }
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
    public Res<String> save(Product product,HttpServletRequest request){
        if(!StringUtils.isBlank(product.getOrigin())){
            product.setOriginPrice(new BigDecimal(product.getOrigin()));
        }
        if(!StringUtils.isBlank(product.getSell())){
            product.setSellPrice(new BigDecimal(product.getSell()));
        }
        if(product.getFile() != null){
            String path = request.getSession().getServletContext().getRealPath("upload");
            try {
                String targetFileName = ftpUtil.uploadFile(product.getFile(),path, Const.FTP_PATH_PRODUCT);
                if(targetFileName == null){
                    return Res.errorMsg("保存商品图片失败");
                }
                String url = PropertiesUtil.getProperty("ftp.prefix")+Const.FTP_PATH_PRODUCT+"/"+targetFileName;
                product.setLogoImg(url);
            }catch (Exception e){
                log.info(e.getMessage());
                return Res.errorMsg("上传评价图片出现异常");
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
    public Res<String> update(Product product,HttpServletRequest request){
        if(!StringUtils.isBlank(product.getOrigin())){
            product.setOriginPrice(new BigDecimal(product.getOrigin()));
        }
        if(!StringUtils.isBlank(product.getSell())){
            product.setSellPrice(new BigDecimal(product.getSell()));
        }
        if(product.getFile() != null){
            String path = request.getSession().getServletContext().getRealPath("upload");
            try {
                String targetFileName = ftpUtil.uploadFile(product.getFile(),path, Const.FTP_PATH_PRODUCT);
                if(targetFileName == null){
                    return Res.errorMsg("保存商品图片失败");
                }
                String url = PropertiesUtil.getProperty("ftp.prefix")+Const.FTP_PATH_PRODUCT+"/"+targetFileName;
                product.setLogoImg(url);
            }catch (Exception e){
                log.info(e.getMessage());
                return Res.errorMsg("上传评价图片出现异常");
            }
        }
        int result = productService.update(product);
        if(result == 0){
            return Res.errorMsg("修改商品信息失败");
        }
        return Res.successMsg("修改商品信息成功");
    }
    @RequestMapping("upload")
    @ApiOperation("上传图片")
    public Res<String> upload(MultipartFile file)throws IOException {
        Long start = System.currentTimeMillis();
        boolean b = ftpUtil.uploadDailyFile(file.getOriginalFilename(),file.getInputStream(),Const.FTP_PATH_DAILY);
        Long end = System.currentTimeMillis();
        System.out.println(end - start);
        if(!b){
            return Res.errorMsg("上传失败");
        }
        return Res.successMsg("上传成功");
        /*String url = PropertiesUtil.getProperty("ftp.prefix")+targetFileName;
        Product product = new Product();
        product.setId(id);
        product.setLogoImg(url);
        int result = productService.updateSelective(product);
        if(result == 0){
            return Res.errorMsg("更改失败");
        }
        return Res.errorMsg("更改成功");*/
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
