package com.sell.common.cache;

import com.sell.common.utils.RedisUtil;
import com.sell.modules.store.entity.ShopCategory;
import com.sell.modules.store.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/4/8 20:36
 */
@Component
public class RedisCache implements IBaseCache {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedisUtil.redisList redisList;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Override
    public List getShopTopCategoryList(String categoryId) {
        //List list = new ArrayList<>();
        if (redisUtil.hasKey("shopTopCategoryList")) {
            return redisList.get("shopTopCategoryList", 0, -1);
        }else{
            List<ShopCategory> list2 = shopCategoryService.getSiblingCategory(categoryId);
            redisList.set("shopTopCategoryList", list2,1800);
            return list2;
        }
    }
    @Override
    public void del(){
        redisUtil.del("shopTopCategoryList");
    }
    @Override
    public List<ShopCategory> getShopSiblingCategoryList(String categoryId) {
        //List list = new ArrayList<>();
        if (redisUtil.hasKey("shopSiblingCategoryList")) {
            System.out.println("直接从redis里读取");
            List list = redisList.get("shopSiblingCategoryList", 0, 0);
            for(Object l :list){
                System.out.println(l.toString());
            }
            return list;
        }else{
            System.out.println("redis里没有这个值，从Mysql里读取");
            List<ShopCategory> list2 = shopCategoryService.getSiblingCategory(categoryId);
            redisList.set("shopSiblingCategoryList", list2,5);
            return list2;
        }
    }
}
