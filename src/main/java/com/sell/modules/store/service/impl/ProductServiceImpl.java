package com.sell.modules.store.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sell.common.Const;
import com.sell.common.IdGenerate;
import com.sell.common.ResponseCode;
import com.sell.common.ServerResponse;
import com.sell.modules.store.dao.ProductMapper;
import com.sell.modules.store.entity.Product;
import com.sell.modules.store.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linyuc
 * @date 2019/12/20 10:02
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public ServerResponse saveOrUpdateProduct(Product product){
        if(product != null){
            if(StringUtils.isBlank(product.getSubImages())){
                String[] subImgArray = product.getSubImages().split(",");
                if(subImgArray.length > 0){
                    product.setMainImg(subImgArray[0]);
                }
            }
            if(product.getId() != null){
                productMapper.updateByPrimaryKey(product);
                return ServerResponse.success("更新商品成功");
            }else{
                product.setId(IdGenerate.uuid());
                productMapper.insert(product);
                return ServerResponse.success("添加商品成功");
            }
        }
        return ServerResponse.errorMsg("新增或更新商品信息失败");
    }
    @Override
    public ServerResponse<String> setStatus(String productId, Integer status){
        if(productId == null || status == null){
            return ServerResponse.errorCodeMsg(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if(rowCount > 0){
            return ServerResponse.success("修改商品状态成功");
        }
        return ServerResponse.errorMsg("修改商品状态失败");
    }
    public ServerResponse<Object> shopProductDetail(String productId){
        Product product = productMapper.selectByPrimaryKey(productId);
        if(productId == null){
            return ServerResponse.errorMsg("已下架");
        }
        return null;
    }
    @Override
    public ServerResponse getProductList(Integer pageNum, Integer pageSize){
        //startPage--start
        //填充自己的sql查询逻辑
        //pageHelper-收尾
        PageHelper.startPage(pageNum,pageSize);
        List<Product> productList = productMapper.selectList();
        PageInfo pageResult = new PageInfo(productList);
        //pageResult.setList(productList);
        return ServerResponse.success(pageResult);
    }
    @Override
    public ServerResponse<PageInfo> searchProduct(String productName,Integer productId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        if(StringUtils.isNotBlank(productName)){
            productName = new StringBuilder().append("%").append(productName).append("%").toString();
        }
        List<Product> productList = productMapper.selectByNamAndProductId(productName,productId);
        PageInfo pageResult = new PageInfo(productList);
        return ServerResponse.success(pageResult);
    }
    @Override
    public ServerResponse<Product> getProductDetail(String productId){
        if(productId == null){
            return ServerResponse.errorCodeMsg(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if(product == null){
            return ServerResponse.errorMsg("产品已下架或删除");
        }
        if(product.getStatus() != Const.ProductStatusEnum.ON_SALE.getCode()){
            return ServerResponse.errorMsg("产品已下架或删除");
        }
        return ServerResponse.success(product);
    }
    /*@Override
    public ServerResponse<PageInfo> getProductByKeywordCategory(String keyword,Integer categoryId,int pageNum,int pageSize,String orderBy){
        if(StringUtils.isBlank(keyword) && categoryId == null){
            return ServerResponse.errorCodeMsg(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        List<Integer> categoryIdList = new ArrayList<Integer>();

        if(categoryId != null){
            Category category = categoryMapper.selectByPrimaryKey(categoryId);
            if(category == null && StringUtils.isBlank(keyword)){
                //没有该分类,并且还没有关键字,这个时候返回一个空的结果集,不报错
                *//*PageHelper.startPage(pageNum,pageSize);
                List<ProductListVo> productListVoList = Lists.newArrayList();
                PageInfo pageInfo = new PageInfo(productListVoList);*//*
                return null;
            }
            categoryIdList = categoryService.selectCategoryAndChildrenById(category.getId()).getData();
        }
        if(StringUtils.isNotBlank(keyword)){
            keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        }

        PageHelper.startPage(pageNum,pageSize);
        //重点：排序处理
        if(StringUtils.isNotBlank(orderBy)){
            if(Const.ProductListOrderBy.PRICE_ASC_DESC.contains(orderBy)){
                String[] orderByArray = orderBy.split("_");
                PageHelper.orderBy(orderByArray[0]+" "+orderByArray[1]);
            }
        }
        List<Product> productList = productMapper.selectByNamAndProductId(StringUtils.isBlank(keyword)?null:keyword,categoryIdList.size()==0?null:1);

        PageInfo pageInfo = new PageInfo(productList);
        return ServerResponse.success(pageInfo);
    }*/

}
