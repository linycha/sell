package com.sell.modules.sys.service;

import com.sell.common.ServerResponse;
import com.sell.modules.sys.entity.User;

import java.util.List;

/**
 * @author linyuc
 * @date 2019/12/18 15:15
 */
public interface UserService {
    ServerResponse<User> login(String username, String password);
    ServerResponse<String> register(User user);
    ServerResponse<String> checkValid(String str, String type);
    ServerResponse selectQuestion(String username);
    ServerResponse<String> checkAnswer(String username, String question, String answer);
    ServerResponse<String> restPassword(String username,String passwordNew,String forgetToken);
    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    ServerResponse<User> updateInfo(User user);
    ServerResponse<User> getInformation(String userId);

    User selectByUsername(String username);

}
