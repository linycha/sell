package com.sell.modules.sys.service;

import com.sell.common.Res;
import com.sell.modules.store.entity.Feedback;
import com.sell.modules.sys.dto.PasswordDTO;
import com.sell.modules.sys.entity.User;

/**
 * @author linyuc
 * @date 2019/12/18 15:15
 */
public interface UserService {
    Res<String> insertRegister(String username,String phone,String password);
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

}
