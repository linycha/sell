package com.sell.modules.store.dao;

import com.sell.modules.store.entity.Product;
import com.sell.modules.store.vo.ProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(String id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<ProductVo> selectProductListByCategory(String categoryId);
    List<Product> selectProductList(@Param("categoryId") String categoryId, @Param("name")String name,
                                    @Param("status") String status);
    Integer checkStockByName(@Param("name")String name,@Param("num")Integer num);
}