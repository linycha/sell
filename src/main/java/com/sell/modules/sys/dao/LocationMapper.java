package com.sell.modules.sys.dao;

import com.sell.modules.sys.entity.Location;

import java.util.List;

public interface LocationMapper {
    int deleteByPrimaryKey(String id);

    int insert(Location record);

    int insertSelective(Location record);

    Location selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Location record);

    int updateByPrimaryKey(Location record);

    List<Location> selectLocationList();
}