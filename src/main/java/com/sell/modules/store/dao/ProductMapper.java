package com.sell.modules.store.dao;

import com.sell.modules.store.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author linyc
 * @date 2019/12/12 12:43
 */
public interface ProductMapper {
    int deleteByPrimaryKey(String id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    List<Product> selectList();
    List<Product> selectByNamAndProductId(@Param("productName")String productName,@Param("productId")Integer productId);
}