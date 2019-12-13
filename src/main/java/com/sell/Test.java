package com.sell;

import com.sell.dao.UserMapper;
import com.sell.entity.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author linyuc
 * @date 2019/12/12 12:52
 */
public class Test {
    @Autowired
    private UserMapper userMapper;
    @org.junit.Test
    public void test(){
        User user = userMapper.selectByPrimaryKey("1");
        System.out.println(user.toString());
    }

}
