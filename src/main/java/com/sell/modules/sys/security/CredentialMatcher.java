package com.sell.modules.sys.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.context.annotation.Bean;

/**
 * @author linyuc
 * @date 2019/12/29 16:16
 */
public class CredentialMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken =(UsernamePasswordToken) token;
        //password用户输入的密码，dbpassword数据库里存储的密码
        String password = new String(usernamePasswordToken.getPassword());
        String dbpassword = (String) info.getCredentials();
        return this.equals(password, dbpassword);
    }
}
