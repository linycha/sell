package com.sell.modules.store.service;

import com.github.pagehelper.PageInfo;
import com.sell.common.Res;
import com.sell.modules.store.entity.Shop;
import com.sell.modules.store.vo.ShopVo;

/**
 * @author linyuc
 * @date 2020/1/20 10:04
 */
public interface ShopService {

    PageInfo<ShopVo> getShopList(String name, String categoryId, Integer sortType,Integer pageNum);
    Shop getShopInfo(String id);
    int updateSelective(Shop shop);
    String getshopId(String userId);
}
