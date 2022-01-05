package com.sell.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sell.common.Const;
import com.sell.modules.sys.dao.LocationMapper;
import com.sell.modules.sys.entity.Location;
import com.sell.modules.sys.service.LocationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linyuc
 * @date 2020/9/2 18:02
 */
@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationMapper locationMapper;
    @Override
    public int save(Location location) {
        return locationMapper.insertSelective(location);
    }

    @Override
    public PageInfo<Location> getList(String pageNum) {
        int page = Const.PAGE_DEFAULT_NUM;
        if(!StringUtils.isBlank(pageNum)){
            page = Integer.parseInt(pageNum);
        }
        PageHelper.startPage(page,Const.PAGE_DEFAULT_SIZE);
        List<Location> list = locationMapper.selectLocationList();
        PageInfo<Location> pageResult = new PageInfo<>(list);
        return pageResult;
    }
}
