package com.shiren.sjwt.shiro.config;

import com.shiren.sjwt.shiro.realm.ShiroRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // cookie有效期
    @Value("${shiro.cookie.maxAge}")
    private int cookieMaxAge;

    // cookie域名
    @Value("${shiro.cookie.domain}")
    private String cookieDomain;

    // cookie有效访问路径
    @Value("${shiro.cookie.path}")
    private String cookiePath;

    // cookie的httpOnly属性
    @Value("${shiro.cookie.httpOnly}")
    private boolean cookieHttpOnly;

    // cookie密钥
    @Value("${shiro.cookie.cipherKey}")
    private String cipherKey;

    // 登录地址
    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    // 登录成功地址
    @Value("${shiro.user.successUrl}")
    private String successUrl;

    @Bean
    public EhCacheManager getEhCacheManager(){
        EhCacheManager ehcacheManager = new EhCacheManager();
        ehcacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return ehcacheManager;
    }

    @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        // 设置cookie生命周期（rememberMe的生命周期）
        cookie.setMaxAge(cookieMaxAge * 60 * 60);
        // 设置cookie保存路径
        cookie.setPath(cookiePath);
        // 设置cookie域名，默认为空
        cookie.setDomain(cookieDomain);
        // 设置httpOnly
        cookie.setHttpOnly(cookieHttpOnly);
        return cookie;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager(SimpleCookie simpleCookie) {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        // 添加cookie配置
        cookieRememberMeManager.setCookie(simpleCookie);
        // 设置cookie密钥，推荐为每个域名单独使用一个
        cookieRememberMeManager.setCipherKey(Base64.decode("fCq+/xW488hMTCD+cmJ3aQ=="));
        return cookieRememberMeManager;
    }

    /**
     * 设置加密方式
     */
    @Bean
    public CredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("SHA-256");
        matcher.setHashIterations(4);
        return matcher;
    }

    @Bean
    public SecurityManager securityManager(ShiroRealm shiroRealm, CredentialsMatcher matcher, EhCacheManager ehCacheManager, RememberMeManager rememberMeManager) {
        // 更改加密方式
        shiroRealm.setCredentialsMatcher(matcher);

        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(shiroRealm);
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager);
        defaultWebSecurityManager.setCacheManager(ehCacheManager);
        return defaultWebSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map urlMap = new LinkedHashMap();

        // 释放静态资源
        urlMap.put("/css/**", "anon");
        urlMap.put("/js/**", "anon");
        urlMap.put("/fonts/**", "anon");
        urlMap.put("/jqvmap/**", "anon");

        // 登出
        urlMap.put("/accounts/logout", "logout");

        // 登录注册
        urlMap.put("/accounts/**", "anon");
        // 验证码
        urlMap.put("/captcha/**", "anon");

        // swagger
        urlMap.put("/swagger-ui.html", "anon");
        urlMap.put("/swagger-resources/**", "anon");
        urlMap.put("/configuration/**", "anon");
        urlMap.put("/v2/api-docs/**", "anon");
        urlMap.put("/webjars/springfox-swagger-ui/**", "anon");

        // 权限拦截
        urlMap.put("/**", "user");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(urlMap);

        shiroFilterFactoryBean.setLoginUrl("/accounts/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");

        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
