package com.sell.config;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Set;

/**
 * 自定义角色过滤器，满足其中一个角色即可通过
 * @author linyuc
 * @date 2020/1/2 13:41
 */
public class MyRolesAuthorizationFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        Subject subject = this.getSubject(request, response);
        //获取当前访问路径所需要的角色集合
        String[] rolesArray = (String[]) o;
        //没有角色限制，可以直接访问
        if (rolesArray != null && rolesArray.length != 0) {
            return true;
        }
        Set<String> roles = CollectionUtils.asSet(rolesArray);
        for(String role:roles){
            if(subject.hasRole(role)){
                return true;
            }
        }
        return false;
    }
}
