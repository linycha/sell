package com.sell.modules.store.dao;

import com.sell.modules.store.entity.ShopCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopCategoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(ShopCategory record);

    int insertSelective(ShopCategory record);

    ShopCategory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ShopCategory record);

    int updateByPrimaryKey(ShopCategory record);

    List<ShopCategory> selectSiblingCategory(String id);
    List<String> selectCategoryList(String id);
}