package com.sell.modules.store.dao;

import com.sell.modules.store.entity.Shop;
import com.sell.modules.store.entity.ShopExample;
import java.util.List;

import com.sell.modules.store.vo.ShopVo;
import org.apache.ibatis.annotations.Param;
/**
 * @author linyuc
 * @date 2020/01/20 09:40
 */
public interface ShopMapper {
    int deleteByPrimaryKey(String id);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);

    List<ShopVo> selectShopList(@Param("name") String name, @Param("categoryIds")List<String> categoryIds,
                                @Param("sort")String sort);
    String selectShopIdByUserId(String userId);
}