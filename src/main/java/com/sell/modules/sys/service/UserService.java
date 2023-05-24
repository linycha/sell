package com.sell.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.sell.common.Res;
import com.sell.modules.store.dto.QueryDTO;
import com.sell.modules.store.entity.Feedback;
import com.sell.modules.store.entity.ProductCategory;
import com.sell.modules.sys.dto.PasswordDTO;
import com.sell.modules.sys.entity.User;

/**
 * @author linyuc
 * @date 2019/12/18 15:15
 */
public interface UserService {
    /**
     * 注册添加账号
     */
    Res<Integer> insertRegister(String username,String phone,String password, Integer roleId);
    Res<String> updateMobile(User user);

    /**
     * 修改用户密码
     * @param dto dto
     * @return
     */
    Res<String> updatePassword(PasswordDTO dto);

    Res<String> checkValid(String str, String type);

    Res<String> restPassword(String username,String passwordNew,String forgetToken);


    User selectByUsername(String username, Integer userId);
    String selectUsernameByMobile(String mobile);

    int update(User user);
    int saveFeedback(Feedback feedback);

    Res<String> updateUsername(User user);

    /**
     * 查询普通用户列表
     * @param dto
     * @return
     */
    PageInfo<User> getUserList(QueryDTO dto);
    /**
     * 批量假删
     * @param ids
     */
    int deleteBatch(String ids);
}
