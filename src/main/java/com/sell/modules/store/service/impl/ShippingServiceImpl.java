package com.sell.modules.store.service.impl;

import com.sell.common.Const;
import com.sell.common.utils.CheckUtil;
import com.sell.modules.store.dao.ShippingMapper;
import com.sell.modules.store.entity.Shipping;
import com.sell.modules.store.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/2/5 16:16
 */
@Service
public class ShippingServiceImpl implements ShippingService {
    @Autowired
    private ShippingMapper shippingMapper;
    @Override
    public List<Shipping> getShippingList(Integer userId) {
        return shippingMapper.selectListByUserId(userId);
    }

    @Override
    public int save(Shipping shipping) {
        shipping.setDelFlag(Const.NOT_DELETE);
        boolean b = CheckUtil.isMobile(shipping.getTel());
        if(!b){
            return 0;
        }
        return shippingMapper.insert(shipping);
    }

    @Override
    public int update(Shipping shipping) {
        return shippingMapper.updateByPrimaryKeySelective(shipping);
    }

    @Override
    public Shipping getDefault(Integer userId) {
        return shippingMapper.selectDefaultByUserId(userId);
    }

    /**
     * 改掉原来的默认地址
     */
    @Override
    public void updateDefault(Integer userId) {
        shippingMapper.updateDefault(userId);
    }

    @Override
    public Shipping getByOrderNo(String orderNo) {
        return shippingMapper.selectByOrderNo(orderNo);
    }
}
