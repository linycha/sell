package com.sell.modules.sys.service.impl;

import com.sell.common.Const;
import com.sell.common.IdGenerate;
import com.sell.common.Res;
import com.sell.common.TokenCache;
import com.sell.modules.sys.dao.UserMapper;
import com.sell.modules.sys.entity.User;
import com.sell.modules.sys.service.UserService;
import com.sell.common.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author linyuc
 * @date 2019/12/18 15:15
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Res<String> register(String mobile,String password){
        User user = new User();
        int resultCount = userMapper.checkMobile(mobile);
        if(resultCount != 1){
            return Res.errorMsg("手机号已存在");
        }
        //保存加密完的密码
        String md5Password = MD5Util.hashTwo(password);
        user.setPassword(md5Password);
        user.setId(IdGenerate.uuid());
        int result = userMapper.insert(user);
        if(result == 0) {
            return Res.errorMsg("注册失败");
        }
        return Res.successMsg("注册成功");
    }
    @Override
    public User selectById(String id){
        return userMapper.selectByPrimaryKey(id);
    }
    @Override
    public Res<String> updateMobile(User user){
        int result = userMapper.checkMobile(user.getMobile());
        if(result != 1){
            return Res.errorMsg("手机号已存在");
        }
        result = userMapper.updateByPrimaryKeySelective(user);
        if(result >0){
            return Res.successMsg("修改成功");
        }
            return Res.errorMsg("修改失败");
    }
    @Override
    public Res<String> updatePassword(String newPwd,User user){
        User user1 = userMapper.selectByPrimaryKey(user.getId());
        String oldPwd = MD5Util.hashTwo(user.getPassword());
        if(!oldPwd.equals(user1.getPassword())){
            return Res.errorMsg("输入的旧密码错误");
        }
        user.setPassword(MD5Util.hashTwo(newPwd));
        int result = userMapper.updateByPrimaryKeySelective(user);
        if(result > 0){
            return Res.successMsg("修改成功");
        }
        return Res.errorMsg("修改失败");
    }


    /**
     * 校验用户名和email是否存在
     * */
    @Override
    public Res<String> checkValid(String str, String type){
        if(StringUtils.isNotBlank(type)){
            if(Const.USERNAME.equals(type)){
                int resultCount = userMapper.checkUsername(str);
                if(resultCount > 0){
                    return Res.errorMsg("用户名已存在");
                }
            }
            if(Const.PHONE.equals(type)){
                int resultCount = userMapper.checkMobile(str);
                if(resultCount > 0){
                    return Res.errorMsg("手机号码已存在");
                }
            }
        }else{
            return Res.errorMsg("参数错误");
        }
        return Res.successMsg("校验成功");
    }
    @Override
    public Res<String> restPassword(String username,String newPassword,String forgetToken){
        if(StringUtils.isBlank(forgetToken)){
            return Res.errorMsg("参数错误，token需要传递");
        }
        Res vaildResponse = this.checkValid(username,Const.USERNAME);
        if(vaildResponse.isSuccess()){
            //用户不存在
            return Res.errorMsg("用户不存在");
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+username);
        if(StringUtils.isBlank(token)){
            return Res.errorMsg("token无效或者过期");
        }
        if(StringUtils.equals(forgetToken,token)){
            String md5Password = MD5Util.MD5EncodeUtf8(newPassword);
            int rowCount = userMapper.updatePasswordByUsername(username,md5Password);
            if(rowCount > 0){
                return Res.successMsg("修改密码成功");
            }
        }else{
            return Res.errorMsg("token错误，请重新获取重置密码的token");
        }
        return Res.errorMsg("修改密码失败");
    }

    @Override
    public User selectByUsername(String username){
        return userMapper.selectByUsername(username);
    }
    @Override
    public String selectUsernameByMobile(String mobile){
        return userMapper.selectUsernameByMobile(mobile);
    }
    @Override
    public int update(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }

}
