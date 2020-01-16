package com.sell.modules.sys.security;

import com.sell.common.Const;
import com.sell.modules.sys.entity.Permission;
import com.sell.modules.sys.entity.Role;
import com.sell.modules.sys.entity.User;
import com.sell.modules.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Realm
 * @author linyuc
 * @date 2019/12/29 15:41
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权 doGetAuthorizationInfo");
        //从session里拿user,查找该用户有哪些角色、权限
        User user = (User) principals.fromRealm(this.getClass().getName()).iterator().next();

        /*//视频里的方法
        User u = (User)principals.getPrimaryPrincipal();
        User user = userService.selectByUsername(u.getUsername());*/

        List<String> permissionList = new ArrayList<>();
        List<Role> roleList = user.getRoles();
        List<String> roleNameList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(roleList)){
            for(Role role : roleList){
                roleNameList.add(role.getName());
                List<Permission> permissions = role.getPermissions();
                if(!CollectionUtils.isEmpty(permissions)){
                    for(Permission permission : permissions){
                        permissionList.add(permission.getName());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info  = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.addRoles(roleNameList);
        return info;
    }
    /**
     * 认证登录
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenToken) throws AuthenticationException {

        System.out.println("开始认证 doGetAuthenticationInfo");
        //从token中获取用户信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenToken;
        String username = token.getUsername();
        //判断是手机号登陆还是用户名
        if(Const.isNumber(username)){
            username = userService.selectUsernameByMobile(username);
        }

        User user = userService.selectByUsername(username);


        if(user == null){
            return null;
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }
}