package com.sell.modules.sys.dao;

import com.sell.modules.store.dto.QueryDTO;
import com.sell.modules.store.entity.Feedback;
import com.sell.modules.sys.entity.Role;
import com.sell.modules.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);
    int insertFeedback(Feedback feedback);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    User selectByUsernameOrUserId(@Param("username") String username,@Param("userId") Integer userId);

    /**
     * 通过用户id获取对应角色信息
     * @param userId
     * @return
     */
    Role selectRoleByUserId(String userId);

    /**
     * 查询用户列表
     * @param dto
     * @return
     */
    List<User> selectUserList(QueryDTO dto);

    /**
     * 添加普通用户的角色给用户
     */
    int insertUserRole(@Param("userId")Integer userId,@Param("roleId")Integer roleId);

    int updateByPrimaryKey(User record);
    int checkUsername(String username);
    int checkMobile(String mobile);
    User selectLogin(@Param("username")String username,@Param("password")String password);
    int updatePasswordByUsername(@Param("username")String username,@Param("password")String password);
    int checkPassword(@Param("password")String password,@Param("userId")String userId);
    String selectUsernameByMobile(String phone);
    /**
     * 批量假删
     * @param ids
     * @return
     */
    int deleteBatch(String ids);
}