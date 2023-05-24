package com.sell.modules.store.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sell.common.Const;
import com.sell.common.Res;
import com.sell.common.ResponseCode;
import com.sell.common.utils.UserUtils;
import com.sell.modules.store.dao.ShopCategoryMapper;
import com.sell.modules.store.dao.ShopMapper;
import com.sell.modules.store.dto.QueryDTO;
import com.sell.modules.store.dto.ShopCountDTO;
import com.sell.modules.store.entity.Shop;
import com.sell.modules.store.service.ShopService;
import com.sell.modules.store.vo.ShopVo;
import com.sell.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private UserService userService;

    @Override
    public PageInfo<ShopVo> getShopList(String name, Integer categoryId, Integer sortType,Integer pageNum){
        String sort = Const.ShopList.ORDER_BY.get(sortType);
        //判断是否是顶级分类，如果是查找它的二级分类放到categoryIds里
        List<Integer> categoryIds = new ArrayList<>();
        if(categoryId != null){
            categoryIds.add(categoryId);
        }else {
            categoryIds = null;
        }
        //给予选择的分页大小和分类
        PageHelper.startPage(pageNum,Const.PAGE_DEFAULT_SIZE2);
        List<ShopVo> shopList = shopMapper.selectShopList(name, categoryIds, sort);
        return new PageInfo<>(shopList);
    }
    @Override
    public Shop getShopInfo(Integer id){
        return shopMapper.selectByPrimaryKey(id);
    }
    @Override
    public int updateSelective(Shop shop){
        return shopMapper.updateByPrimaryKeySelective(shop);
    }

    @Override
    public String getshopId(String userId) {
        return shopMapper.selectShopIdByUserId(userId);
    }

    @Override
    public ShopCountDTO getShopCount() {
        return shopMapper.selectShopCount(UserUtils.getShopId());
    }

    @Override
    public List<ShopCountDTO> getLastYearCount(Integer shopId) {
        List<ShopCountDTO> list = shopMapper.getLastYearCount(shopId);
        //如果有某月份的数据为空进行处理
        if(list.size() < 12){
            List<String> monthList = list.stream().map(ShopCountDTO::getMonth).collect(Collectors.toList());
            List<String> allList = getLastSevMonth();
            allList.removeAll(monthList);
            allList.forEach(month -> list.add(ShopCountDTO.builder().month(month)
                    .salesTotal(new BigDecimal("0")).build()));
            return list.stream().sorted(Comparator.comparing(ShopCountDTO::getMonth)).collect(Collectors.toList());
        }
        return list;
    }

    @Override
    public PageInfo<Shop> queryShopList(QueryDTO dto) {
        Const.initPage(dto.getCurrent(),dto.getSize());
        List<Shop> list = shopMapper.selectAdminShopList(dto);
        return new PageInfo<>(list);
    }

    @Override
    public int deleteBatch(String ids) {
        List<Integer> list = shopMapper.selectUserIdByShopId(ids);
        String userIds = list.stream().map(String::valueOf).collect(Collectors.joining(","));
        // 删除用户表对应用户
        userService.deleteBatch(userIds);
        return shopMapper.deleteBatch(ids);
    }
    @Override
    public Res<Integer> save(Shop shop) {
        shop.setPassword("123456");
        //先添加账号信息
        Res<Integer> res = userService.insertRegister(shop.getUsername(), shop.getMobile(), shop.getPassword(),2);
        if(res.getCode() == ResponseCode.ERROR.getCode()){
            return res;
        }
        shop.setUserId(res.getData());
        shop.setOpeningTime(DateUtil.parseTime("00:00:00"));
        shop.setClosingTime(DateUtil.parseTime("23:59:59"));
        shopMapper.insertSelective(shop);
        return Res.successMsg("添加商家店铺成功");
    }

    List<String> getLastSevMonth(){
        List<String> strings = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        strings.add(formatter.format(calendar.getTime()));
        for (int i = 0; i < 11; i++) {
            calendar.add(Calendar.MONTH, -1);
            strings.add(formatter.format(calendar.getTime()));
        }
        return strings;
    }
}
