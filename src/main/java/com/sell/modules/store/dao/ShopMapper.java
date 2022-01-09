package com.sell.modules.store.dao;

import com.sell.modules.store.dto.ShopCountDTO;
import com.sell.modules.store.entity.Shop;

import java.util.List;

import com.sell.modules.store.vo.ShopVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * @author linyuc
 * @date 2020/01/20 09:40
 */
@Mapper
public interface ShopMapper {
    int deleteByPrimaryKey(String id);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);

    List<ShopVo> selectShopList(@Param("name") String name, @Param("categoryIds")List<Integer> categoryIds,
                                @Param("sort")String sort);
    String selectShopIdByUserId(String userId);
    /**
     * 获取店铺首页统计信息
     * @return dto
     */
    ShopCountDTO selectShopCount(Integer shopId);

    /**
     * 查询最近12个月每个月的数据统计
     * @param shopId
     * @return
     */
    List<ShopCountDTO> getLastYearCount(Integer shopId);
}