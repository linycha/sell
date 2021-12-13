package com.sell.modules.store.dao;

import com.sell.modules.store.dto.QueryProductDTO;
import com.sell.modules.store.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductCategoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(ProductCategory record);

    int insertSelective(ProductCategory record);

    ProductCategory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProductCategory record);

    int updateByPrimaryKey(ProductCategory record);

    List<ProductCategory> selectCategoryList(QueryProductDTO dto);
    /**
     * 批量假删
     * @param ids
     * @return
     */
    int deleteBatch(String ids);
}