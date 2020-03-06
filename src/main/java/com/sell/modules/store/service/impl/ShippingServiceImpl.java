package com.sell.modules.store.service.impl;

import com.sell.common.IdGenerate;
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
    public List<Shipping> getShippingList(String userId) {
        return shippingMapper.selectListByUserId(userId);
    }

    @Override
    public int save(Shipping shipping) {
        shipping.setId(IdGenerate.uuid());
        return shippingMapper.insert(shipping);
    }

    @Override
    public int update(Shipping shipping) {
        return shippingMapper.updateByPrimaryKeySelective(shipping);
    }

    @Override
    public Shipping getDefault(String userId) {
        return shippingMapper.selectDefaultByUserId(userId);
    }
}
