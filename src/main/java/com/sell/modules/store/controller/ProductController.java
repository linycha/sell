package com.sell.modules.store.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.lly835.bestpay.rest.type.Delete;
import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.common.utils.PropertiesUtil;
import com.sell.modules.store.entity.Product;
import com.sell.modules.store.entity.ProductCategory;
import com.sell.modules.store.entity.Shipping;
import com.sell.modules.store.entity.Shop;
import com.sell.modules.store.service.FileService;
import com.sell.modules.store.service.ProductCategoryService;
import com.sell.modules.store.service.ProductService;
import com.sell.modules.store.vo.ProductVo;
import com.sell.modules.store.vo.ShopVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author linyuc
 * @date 2020/1/20 16:34
 */
@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private FileService fileService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("list")
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
    public Res<PageInfo<Product>> getProductList(String categoryId, String name, String status,String pageNum){
        PageInfo<Product> productList = productService.getProductList(categoryId,name,status,pageNum);
        if(productList == null){
            return Res.errorMsg("未找到相应的商品信息");
        }
        return Res.success(productList);
    }
    @PostMapping("save")
    public Res<String> save(Product product,HttpServletRequest request){
        if(!StringUtils.isBlank(product.getOrigin())){
            product.setOriginPrice(new BigDecimal(product.getOrigin()));
        }
        if(!StringUtils.isBlank(product.getSell())){
            product.setSellPrice(new BigDecimal(product.getSell()));
        }
        if(product.getFile() != null){
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = fileService.upload(product.getFile(),path, Const.FTPPATH_PRODUCT);
            if(targetFileName == null){
                return Res.errorMsg("保存商品图片失败");
            }
            String url = PropertiesUtil.getProperty("ftp.prefix")+Const.FTPPATH_PRODUCT+"/"+targetFileName;
            product.setLogoImg(url);
        }
        int result = productService.saveProduct(product);
        if(result == 0){
            return Res.errorMsg("保存商品信息失败");
        }
        return Res.successMsg("保存商品信息成功");
    }
    @PutMapping("update")
    public Res<String> update(Product product,HttpServletRequest request){
        if(!StringUtils.isBlank(product.getOrigin())){
            product.setOriginPrice(new BigDecimal(product.getOrigin()));
        }
        if(!StringUtils.isBlank(product.getSell())){
            product.setSellPrice(new BigDecimal(product.getSell()));
        }
        if(product.getFile() != null){
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = fileService.upload(product.getFile(),path, Const.FTPPATH_PRODUCT);
            if(targetFileName == null){
                return Res.errorMsg("修改商品图片失败");
            }
            String url = PropertiesUtil.getProperty("ftp.prefix")+Const.FTPPATH_PRODUCT+"/"+targetFileName;
            product.setLogoImg(url);
        }
        int result = productService.update(product);
        if(result == 0){
            return Res.errorMsg("修改商品信息失败");
        }
        return Res.successMsg("修改商品信息成功");
    }
    @RequestMapping("upload")
    public Res<String> upload(MultipartFile file, HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = fileService.upload(file,path, Const.FTPPATH_TEST);

        if(targetFileName == null){
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
    public Res<String> delete(String ids){
        System.out.println(ids);
        if(StringUtils.isBlank(ids)){
            return Res.errorMsg("传递的id参数失败");
        }
        String[] idArray = ids.split(",");
        for(String id : idArray){
            Product product = new Product();
            product.setId(id);
            product.setDelFlag(Const.DELETED);
            int result = productService.update(product);
            if(result == 0){
                return Res.errorMsg("删除失败");
            }
        }
        return Res.successMsg("删除成功");
    }

}
