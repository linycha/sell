package com.sell.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.sell.modules.sys.entity.Location;

/**
 * @author linyuc
 * @date 2020/9/2 18:02
 */
public interface LocationService {
    int save(Location location);
    PageInfo<Location> getList(String pageNum);

}
