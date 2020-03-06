package com.sell.modules.store.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.modules.store.dao.ShopCategoryMapper;
import com.sell.modules.store.dao.ShopMapper;
import com.sell.modules.store.entity.Shop;
import com.sell.modules.store.entity.ShopCategory;
import com.sell.modules.store.service.ShopService;
import com.sell.modules.store.vo.ShopVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linyuc
 * @date 2020/1/20 10:02
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ShopCategoryMapper shopCategoryMapper;

    @Override
    public PageInfo<ShopVo> getShopList(String name, String categoryId, Integer sortType,Integer pageNum){
        String sort = Const.ShopList.ORDER_BY.get(sortType);
        //判断是否是顶级分类，如果是查找它的二级分类放到categoryIds里
        List<String> categoryIds = new ArrayList<>();
        List<ShopVo> shopList = new ArrayList<>();
        if(!StringUtils.isBlank(categoryId)){
            ShopCategory shopCategory = shopCategoryMapper.selectByPrimaryKey(categoryId);
            if(shopCategory.getParentId().equals(Const.CATEGORY_PARENT_ID)){
                categoryIds = shopCategoryMapper.selectCategoryList(categoryId);
                if(categoryIds.size() == 0){
                    return null;
                }
            }else{
                categoryIds.add(categoryId);
            }
        }else{
            categoryIds = null;
        }
        //给予选择的分页大小和分类
        PageHelper.startPage(pageNum,Const.PAGE_DEFAULT_SIZE2);
        shopList = shopMapper.selectShopList(name, categoryIds, sort);
        if(shopList.size() == 0){
            return null;
        }
        PageInfo<ShopVo> pageResult = new PageInfo<>(shopList);
        return pageResult;
    }
    @Override
    public Shop getShopInfo(String id){
        return shopMapper.selectByPrimaryKey(id);
    }
    @Override
    public int updateSelective(Shop shop){
        return shopMapper.updateByPrimaryKeySelective(shop);
    }
}
