package com.sell.modules.store.dao;

import com.sell.modules.store.dto.QueryProductDTO;
import com.sell.modules.store.entity.Product;
import com.sell.modules.store.vo.ProductVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    int deleteByPrimaryKey(String id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<ProductVo> selectProductListByCategory(Integer shopId);
    List<Product> selectProductList(QueryProductDTO dto);
    Integer checkStockByName(@Param("name")String name,@Param("num")Integer num);

    /**
     * 批量假删
     * @param ids
     * @return
     */
    int deleteBatch(String ids);
}