package com.jshine.performance.shiro;

import com.jshine.performance.shiro.filter.JwtUserFilter;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
* shiro配置类
*/
@Configuration
public class ShiroConfig {
    @Bean("credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new LoginMatcher();
    }
    @Bean("loginRealm")
    public LoginRealm loginRealm(@Qualifier("credentialsMatcher") CredentialsMatcher credentialsMatcher){
        LoginRealm loginRealm = new LoginRealm();
        loginRealm.setCredentialsMatcher(credentialsMatcher);
        return loginRealm;
    }
    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("loginRealm") LoginRealm loginRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(loginRealm);
        return securityManager;
    }
    /*@Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition shiroFilterChainDefinition = new DefaultShiroFilterChainDefinition();
        shiroFilterChainDefinition.addPathDefinition("/static/**","anon");
        shiroFilterChainDefinition.addPathDefinition("/login","authc");
        shiroFilterChainDefinition.addPathDefinition("/logout","logout");
        return shiroFilterChainDefinition;
    }*/
    @Bean("shirFilter")
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager")
                                                     SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置 SecurityManager
        bean.setSecurityManager(securityManager);
        //设置登录成功跳转Url
        bean.setSuccessUrl("/main");
        //设置登录跳转Url
        bean.setLoginUrl("/toLogin");
        //设置未授权提示Url
        bean.setUnauthorizedUrl("/error/unAuth");
        /**
         * anon：匿名用户可访问
         * authc：认证用户可访问
         * user：使用rememberMe可访问
         * perms：对应权限可访问
         * role：对应角色权限可访问
         **/
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/toLogin","anon");
        filterMap.put("/login","anon");
        filterMap.put("/toLogin","anon");
        filterMap.put("/jwtLogin","anon");
        filterMap.put("/user/index","authc");
        filterMap.put("/vip/index","roles[vip]");
        filterMap.put("/error/unAuth","anon");
        filterMap.put("/user/index","jwtUser,roles[user]");
        filterMap.put("/vip/index","jwtUser,roles[vip]");
        filterMap.put("/static/**","anon");
        filterMap.put("/logout", "logout");
        filterMap.put("/user/index","authc");
        filterMap.put("/**","authc");
        filterMap.put("/**", "jwtUser");
        bean.setFilterChainDefinitionMap(filterMap);
        Map<String, Filter> customFilters = new HashMap<String,Filter>();
        /*customFilters.put("jwtFilter", new JwtAuthenticationFilter());
        customFilters.put("jwtFormFilter",new JwtFormAuthenticationFilter());*/
        customFilters.put("jwtUser", new JwtUserFilter());
        bean.setFilters(customFilters);
        return bean;
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


}
