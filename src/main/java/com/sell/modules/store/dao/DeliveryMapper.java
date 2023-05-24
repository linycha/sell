package com.sell.modules.store.dao;

import com.sell.modules.store.dto.QueryDTO;
import com.sell.modules.store.entity.Delivery;
import com.sell.modules.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author linyuc
 * @date 2019/12/20 11:50
 */
@Mapper
public interface DeliveryMapper {
    int deleteByPrimaryKey(String id);

    int insert(Delivery record);

    int insertSelective(Delivery record);

    Delivery selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Delivery record);

    int updateByPrimaryKey(Delivery record);

    int selectCount();

    Delivery selectBest(Integer number);

    Delivery selectByUserId(Integer id);

    int updateTaskNumByUserId(Integer userId);

    /**
     * 查询骑手列表
     * @param dto
     * @return
     */
    List<Delivery> selectDeliveryList(QueryDTO dto);
    /**
     * 批量假删
     * @param ids
     * @return
     */
    int deleteBatch(String ids);

    List<Integer> selectUserIdByDeliveryId(String ids);
}