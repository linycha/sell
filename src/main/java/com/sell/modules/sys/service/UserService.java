package com.sell.modules.sys.service;

import com.sell.common.Res;
import com.sell.modules.store.entity.Feedback;
import com.sell.modules.sys.entity.Role;
import com.sell.modules.sys.entity.User;

import java.util.List;

/**
 * @author linyuc
 * @date 2019/12/18 15:15
 */
public interface UserService {
    Res<String> register(String username,String phone,String password);

    User selectById(String id);

    Res<String> updateMobile(User user);
    Res<String> updatePassword(String newPwd,User user);

    Res<String> checkValid(String str, String type);

    Res<String> restPassword(String username,String passwordNew,String forgetToken);


    User selectByUsername(String username, String userId);
    String selectUsernameByMobile(String mobile);

    int update(User user);
    int saveFeedback(Feedback feedback);

    Res<String> updateUsername(User user);

}
