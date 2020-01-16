package com.sell.modules.sys.service;

import com.sell.common.Res;
import com.sell.modules.sys.entity.User;

import java.util.List;

/**
 * @author linyuc
 * @date 2019/12/18 15:15
 */
public interface UserService {
    Res<String> register(String phone,String password);

    Res selectById(String id);

    Res<String> updateMobile(User user);
    Res<String> updatePassword(String newPwd,User user);







    Res<String> checkValid(String str, String type);

    Res<String> restPassword(String username,String passwordNew,String forgetToken);


    User selectByUsername(String username);
    String selectUsernameByMobile(String mobile);

}
