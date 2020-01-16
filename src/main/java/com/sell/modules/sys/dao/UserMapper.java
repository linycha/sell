package com.sell.modules.sys.dao;

import com.sell.modules.sys.entity.Role;
import com.sell.modules.sys.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    User selectByUsername(String username);
    List<Role> selectRoleByUsername(String username);
    List<User> selectUserList();

    int updateByPrimaryKey(User record);
    int checkUsername(String username);
    int checkMobile(String mobile);
    User selectLogin(@Param("username")String username,@Param("password")String password);
    String selectQuestionByUsername(String username);
    int checkAnswer(@Param("username")String username,@Param("question")String question,@Param("answer")String answer);
    int updatePasswordByUsername(@Param("username")String username,@Param("password")String password);
    int checkPassword(@Param("password")String password,@Param("userId")String userId);
    String selectUsernameByMobile(String phone);
}