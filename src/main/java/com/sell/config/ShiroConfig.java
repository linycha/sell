package com.sell.config;


import com.sell.modules.sys.security.AuthRealm;

import com.sell.modules.sys.security.MySessionManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro主要配置类
 * @author linyc
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager){
        System.out.println("执行 ShiroFilterFactoryBean.shiroFilter");

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //没有登录时跳转的url
        bean.setLoginUrl("/login");
        //bean.setSuccessUrl("/index");
        //没有权限访问某个页面的时候跳转的url
        bean.setUnauthorizedUrl("/unauthc");
        //自定filter
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("role",new MyRolesAuthorizationFilter());
        bean.setFilters(filterMap);

        //某些请求该如何拦截，要定义LinkHashMap
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //过滤是从上而下顺序执行，一般/**放到最下面
        // anon不需要做任何校验,authc只有登录用户才可以访问
        filterChainDefinitionMap.put("/logout","logout");
        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/loginUser","anon");
        filterChainDefinitionMap.put("/static/**","anon");
        filterChainDefinitionMap.put("/druid/**","anon");
        filterChainDefinitionMap.put("/list", "authc");
        filterChainDefinitionMap.put("/admin/**","roles[admin,customer]");
        filterChainDefinitionMap.put("/test","role[admin,customer]");
        //filterChainDefinitionMap.put("/admin/delete","perms[delete]");
        filterChainDefinitionMap.put("/user/**","roles[user]");
        filterChainDefinitionMap.put("/shop/**","roles[shop]");
        filterChainDefinitionMap.put("/delivery/**","roles[delivery]");

        filterChainDefinitionMap.put("/**","user");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return bean;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //前后端分离设置sessionManager
        manager.setCacheManager(cacheManager());
        manager.setSessionManager(sessionManager());
        manager.setRealm(authRealm());
        return manager;
    }

    /**
     * 设置自定义加密器credentialsMatcher
     */

    @Bean()
    public AuthRealm authRealm(){
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCacheManager(new MemoryConstrainedCacheManager());
        authRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return authRealm;
    }
    @Bean
    public MySessionManager sessionManager(){
        MySessionManager sessionManager = new MySessionManager();
        //会话超时时间，单位毫秒，20秒时
        //sessionManager.setGlobalSessionTimeout(20000);
        //session持久化
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        System.out.println("开始密码加解密");
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置散列算法，使用MD5算法,散列2次
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(2);
        return credentialsMatcher;
    }
    /**
     * 配置redisManager
     */
    public RedisManager getRedisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("localhost");
        redisManager.setPort(6379);
        return redisManager;
    }
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(getRedisManager());
        //设置过期时间，单位s
        redisCacheManager.setExpire(20);
        return  redisCacheManager;
    }

    /**
     * 自定义session持久化
     * @return
     */
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setRedisManager(getRedisManager());
        return sessionDAO;
    }



    /**
     * 配置shiro与spring的关联，开启AOP注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager")SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return  advisor;
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return  creator;
    }


    /*@Autowired
    protected SystemAuthorizingRealm systemAuthorizingRealm;

    @Autowired
    protected IportFormAuthenticationFilter iportFormAuthenticationFilter;

    @Autowired
    protected UnauthorizedFilter unauthorizedFilter;

    @Value("${ehcache.configFile}")
    private String configFile;

    @Value("${session.sessionTimeout}")
    private String sessionTimeout;

    @Value("${session.sessionTimeoutClean}")
    private String sessionTimeoutClean;

    @Value("${frontPath}")
    private String frontPath;
    @Value("${adminPath}")
    private String adminPath;
    @Value("${server.environment}")
    private String environment;

    @Value("${cas.sso.enabled}")
    private boolean casSsoEnabled;
    @Value("${cas.server.url}")
    private String casServerUrl;
    @Value("${cas.server.url.prefix}")
    private String casServerUrlPrefix;
    @Value("${cas.service}")
    private String casService;
    @Value("${cas.logout.url}")
    private String casLogoutUrl;

    private String getPrePath(){
        String prePath = "";
        if (environment.equals("front")) {
            prePath = frontPath;
        }else{
            prePath = adminPath;
        }
        return prePath;
    }

    *//**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     Filter Chain定义说明
     1、一个URL可以配置多个Filter，使用逗号分隔
     2、当设置多个过滤器时，全部验证通过，才视为通过
     3、部分过滤器可指定参数，如perms，roles
     *
     *//*
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        String prePath = getPrePath();
        if(casSsoEnabled){
            shiroFilterFactoryBean.setLoginUrl(casServerUrl + "?service=" + casService +  "/cas");
            if(environment.equals("front")){
                shiroFilterFactoryBean.setSuccessUrl(frontPath+"/index");
            }else{
                shiroFilterFactoryBean.setSuccessUrl(adminPath);
            }
        }else{
            shiroFilterFactoryBean.setLoginUrl(prePath+"/login");
            shiroFilterFactoryBean.setSuccessUrl(prePath+"?login");
        }

       // shiroFilterFactoryBean.setUnauthorizedUrl("/user/unauth");

        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("cas", casFilter());
        filterMap.put("authc", iportFormAuthenticationFilter);
        filterMap.put("logout", sysLogoutFilter());
        filterMap.put("unauthc", unauthorizedFilter);
        shiroFilterFactoryBean.setFilters(filterMap);

        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
        filterChainDefinitionMap.put("/static/**","anon");
        filterChainDefinitionMap.put("/userfiles/**","anon");
        filterChainDefinitionMap.put("/cas","cas");
        filterChainDefinitionMap.put("/api/**","rest");
        filterChainDefinitionMap.put("/rest/**","anon");
        filterChainDefinitionMap.put("/act/editor/**","user");
        if(environment.equals("admin")){
            filterChainDefinitionMap.put(frontPath+"/**","unauthc");
            filterChainDefinitionMap.put("/md/**","unauthc");
            filterChainDefinitionMap.put(adminPath+"/login","anon");
            filterChainDefinitionMap.put(adminPath+"/logout","logout");
            filterChainDefinitionMap.put(adminPath+"/**","authc");
        }else if(environment.equals("front")){
            filterChainDefinitionMap.put(adminPath+"/**","unauthc");
            filterChainDefinitionMap.put(frontPath+"/logout","logout");
            filterChainDefinitionMap.put(frontPath+"/login","authc");
            filterChainDefinitionMap.put(frontPath+"/**","anon");
            filterChainDefinitionMap.put("/md/**","authc,unauthc");
        }
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public CasFilter casFilter() {
        CasFilter casFilter = new CasFilter();
        String prePath = getPrePath();
        if(casSsoEnabled){
            casFilter.setFailureUrl(prePath + "/login");
        }else{
            casFilter.setFailureUrl("");
        }
        return casFilter;
    }

    public LogoutFilter sysLogoutFilter() {
        LogoutFilter sysLogoutFilter = new SystemLogoutFilter();
        String prePath = getPrePath();
        if(casSsoEnabled){
            if (environment.equals("front")) {
                sysLogoutFilter.setRedirectUrl(casLogoutUrl+"?service=" + casService + "/fg/index");
            }else{
                sysLogoutFilter.setRedirectUrl(casLogoutUrl+"?service=" + casService + "/cas");
            }
        }else{
            sysLogoutFilter.setRedirectUrl(prePath + "/login");
        }
        return sysLogoutFilter;
    }

//    @Bean
//    public WebSecurityManager webSecurityManager(Realm realm, RememberMeManager rememberMeManager) {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(realm);
//        // 自定义缓存实现 使用redis
//        securityManager.setCacheManager(shiroCacheManager());
//        // 自定义session管理 使用redis
//        securityManager.setSessionManager(sessionManager());
//        securityManager.setRememberMeManager(rememberMeManager);
//        return securityManager;
//    }


    @Bean(name="securityManager")
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm.
        if(casSsoEnabled){
            securityManager.setRealm(realm());
        }else{
            securityManager.setRealm(systemAuthorizingRealm);
        }
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(shiroCacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public MySessionManager sessionManager() {
        MySessionManager sessionManager = new MySessionManager();
        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setGlobalSessionTimeout(Long.valueOf(sessionTimeout));
        sessionManager.setSessionValidationInterval(Long.valueOf(sessionTimeoutClean));
        //是否开启删除无效的session对象  默认为true
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdCookie(sessionIdCookie());
        sessionManager.setSessionIdCookieEnabled(true);
        return sessionManager;
    }

    @Bean
    public CacheSessionDAO sessionDAO() {
        CacheSessionDAO cacheSessionDAO = new CacheSessionDAO();
        cacheSessionDAO.setSessionIdGenerator(idGenerate());
        cacheSessionDAO.setActiveSessionsCacheName("activeSessionsCache");
        cacheSessionDAO.setCacheManager(shiroCacheManager());
        return cacheSessionDAO;
    }

    @Bean(name="shiroCacheManager")
    public EhCacheManager shiroCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:" + configFile);
        return ehCacheManager;
    }

    @Bean
    public IdGen idGenerate() {
        IdGen idGenerate = new IdGen();
        return idGenerate;
    }

    @Bean
    public Cookie sessionIdCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("iport.session.id");
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(true);//设置HttpOnly=true的cookie不能被js获取到，无法用document.cookie打出cookie的内容
//        simpleCookie.setSecure(true); //如果一个cookie被设置了Secure=true，那么这个cookie只能用https协议发送给服务器，用http协议是不发送的
        return simpleCookie;
    }


    @Bean(name="lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn({ "lifecycleBeanPostProcessor" })
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public Realm realm() {
        SystemCasRealm realm = new SystemCasRealm();
        realm.setAuthenticationCachingEnabled(true);
        realm.setAuthenticationCacheName("authenticationCache");
        realm.setAuthorizationCacheName("authorizationCache");
        realm.setCasServerUrlPrefix(casServerUrlPrefix);
        realm.setCasService(casService + "/cas");
        return realm;
    }

    *//**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     *//*
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

//    @Bean(name = "exceptionHandler")
//    public HandlerExceptionResolver handlerExceptionResolver() {
//        return new MyExceptionHandler();
//    }
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver=new SimpleMappingExceptionResolver();
        Properties properties=new Properties();
        //这里的 /unauthorized 是页面，不是访问的路径
        properties.setProperty("org.apache.shiro.authz.UnauthorizedException","error/403");
        properties.setProperty("org.apache.shiro.authz.UnauthenticatedException","error/403");
        properties.setProperty("java.lang.Throwable", "error/500");
        simpleMappingExceptionResolver.setExceptionMappings(properties);
        return simpleMappingExceptionResolver;
    }

    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
        return new ErrPageRegistrar();
    }

    @Bean
    public CookieRememberMeManager rememberMeManager(Cookie cookie) {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(cookie);
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        rememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return rememberMeManager;
    }

    @Bean
    public AuthenticationStrategy authenticationStrategy() {
        return new AtLeastOneSuccessfulStrategy();
    }

    @Bean
    public Authenticator authenticator(AuthenticationStrategy authenticationStrategy) {
        ModularRealmAuthenticator authenticator = new CustomizedModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(authenticationStrategy);
        return authenticator;
    }
*/
}
