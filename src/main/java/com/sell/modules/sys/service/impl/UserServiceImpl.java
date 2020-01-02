package com.sell.modules.sys.service.impl;

import com.sell.common.Const;
import com.sell.common.IdGenerate;
import com.sell.common.ServerResponse;
import com.sell.common.TokenCache;
import com.sell.modules.sys.dao.UserMapper;
import com.sell.modules.sys.entity.User;
import com.sell.modules.sys.service.UserService;
import com.sell.common.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author linyuc
 * @date 2019/12/18 15:15
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public ServerResponse<User> login(String username, String password) {
        int result = userMapper.checkUsername(username);
        if(result == 0){
            return ServerResponse.errorMsg("用户不存在");
        }
        String md5Passeord = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username,md5Passeord);
        if(user == null){
            return ServerResponse.errorMsg("密码错误");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.success("登录成功",user);
    }
    @Override
    public ServerResponse<String> register(User user){
        ServerResponse<String> vaildResponse = this.checkValid(user.getUsername(),Const.USERNAME);
        if(!vaildResponse.isSuccess()){
            return ServerResponse.errorMsg("用户名已存在");
        }
        vaildResponse = this.checkValid(user.getPhone(),Const.PHONE);
        if(!vaildResponse.isSuccess()){
            return ServerResponse.errorMsg("手机号已存在");
        }
        //保存加密完的密码
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        user.setId(IdGenerate.uuid());
        user.setRole(1);
        int result = userMapper.insert(user);
        if(result == 0) {
            return ServerResponse.errorMsg("注册失败");
        }
        return ServerResponse.successMsg("注册成功");
    }
    /**
     * 校验用户名和email是否存在
     * */
    @Override
    public ServerResponse<String> checkValid(String str, String type){
        if(StringUtils.isNotBlank(type)){
            if(Const.USERNAME.equals(type)){
                int resultCount = userMapper.checkUsername(str);
                if(resultCount > 0){
                    return ServerResponse.errorMsg("用户名已存在");
                }
            }
            if(Const.PHONE.equals(type)){
                int resultCount = userMapper.checkPhone(str);
                if(resultCount > 0){
                    return ServerResponse.errorMsg("手机号码已存在");
                }
            }
        }else{
            return ServerResponse.errorMsg("参数错误");
        }
        return ServerResponse.successMsg("校验成功");
    }
    @Override
    public ServerResponse selectQuestion(String username){
        ServerResponse validResponse = this.checkValid(username,Const.USERNAME);
        if(validResponse.isSuccess()){
            return ServerResponse.errorMsg("用户不存在");
        }
        String question = userMapper.selectQuestionByUsername(username);
        if(StringUtils.isNotBlank(question)){
            return ServerResponse.success(question);
        }
        return ServerResponse.errorMsg("未设置密保问题,请通过其他方式找回密码");
    }
    /**
     * 使用本地缓存检查问题答案，返回一个token
     * */
    @Override
    public ServerResponse<String> checkAnswer(String username, String question, String answer){
        int resultCount = userMapper.checkAnswer(username,question,answer);
        if(resultCount > 0){
            //token在重置密码时需要用到，设置一个有效期
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+username,forgetToken);
            return ServerResponse.success(forgetToken);
        }
        return ServerResponse.errorMsg("问题的答案错误");
    }
    @Override
    public ServerResponse<String> restPassword(String username,String newPassword,String forgetToken){
        if(StringUtils.isBlank(forgetToken)){
            return ServerResponse.errorMsg("参数错误，token需要传递");
        }
        ServerResponse vaildResponse = this.checkValid(username,Const.USERNAME);
        if(vaildResponse.isSuccess()){
            //用户不存在
            return ServerResponse.errorMsg("用户不存在");
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+username);
        if(StringUtils.isBlank(token)){
            return ServerResponse.errorMsg("token无效或者过期");
        }
        if(StringUtils.equals(forgetToken,token)){
            String md5Password = MD5Util.MD5EncodeUtf8(newPassword);
            int rowCount = userMapper.updatePasswordByUsername(username,md5Password);
            if(rowCount > 0){
                return ServerResponse.successMsg("修改密码成功");
            }
        }else{
            return ServerResponse.errorMsg("token错误，请重新获取重置密码的token");
        }
        return ServerResponse.errorMsg("修改密码失败");
    }
    @Override
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user){
        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld),user.getId());
        if(resultCount == 0 ){
            return ServerResponse.errorMsg("旧密码错误");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if(updateCount > 0){
            return ServerResponse.successMsg("密码更新成功");
        }
        return ServerResponse.errorMsg("密码更新失败");
    }
    @Override
    public ServerResponse<User> updateInfo(User user){
        //用户名不可更改,email也做一个校验

        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if(updateCount > 0){
            return ServerResponse.success("更新个人信息成功",user);
        }
        return ServerResponse.errorMsg("更新个人信息失败");
    }
    @Override
    public ServerResponse<User> getInformation(String userId){
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null){
            return ServerResponse.errorMsg("找不到当前用户");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.success(user);
    }
    @Override
    public User selectByUsername(String username){
        return userMapper.selectByUsername(username);
    }
}
