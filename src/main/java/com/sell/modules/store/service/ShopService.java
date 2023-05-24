package com.sell.modules.store.service;

import com.github.pagehelper.PageInfo;
import com.sell.common.Res;
import com.sell.modules.store.dto.QueryDTO;
import com.sell.modules.store.dto.ShopCountDTO;
import com.sell.modules.store.entity.Delivery;
import com.sell.modules.store.entity.Shop;
import com.sell.modules.store.vo.ShopVo;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/1/20 10:04
 */
public interface ShopService {

    PageInfo<ShopVo> getShopList(String name, Integer categoryId, Integer sortType,Integer pageNum);
    Shop getShopInfo(Integer id);
    int updateSelective(Shop shop);
    String getshopId(String userId);

    /**
     * 获取店铺首页统计信息
     * @return dto
     */
    ShopCountDTO getShopCount();
    /**
     * 查询最近12个月每个月的数据统计
     * @param shopId
     * @return
     */
    List<ShopCountDTO> getLastYearCount(Integer shopId);
    /**
     * 管理端查询店铺列表
     */
    PageInfo<Shop> queryShopList(QueryDTO dto);
    /**
     * 批量假删
     * @param ids
     */
    int deleteBatch(String ids);

    Res<Integer> save(Shop delivery);
}
