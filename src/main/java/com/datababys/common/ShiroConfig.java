package com.datababys.common;


import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import com.datababys.shiro.AjaxUserFilter;
import com.datababys.shiro.CaptchaFormAuthenticationFilter;
import com.datababys.shiro.ShiroDbRealm;
import com.google.common.collect.Maps;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;



@Configuration
public class ShiroConfig {

    /**
     * FilterRegistrationBean
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
    }

    /**
     * @see ShiroFilterFactoryBean
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/login");
        //bean.setUnauthorizedUrl("/unauthor");
        //bean.setSuccessUrl("/index");
        Map<String, Filter>filters = Maps.newHashMap();
        filters.put("authc",captchaFormAuthenticationFilter());
        //filters.put("user",userFilter());
        filters.put("anon", new AnonymousFilter());
        bean.setFilters(filters);
        ///management/api/** = anon
//			    /ehrLoginSSO/**= anon
//         /loginSSO/**= anon
//         /Captcha.jpg = anon
//         /styles/** = anon
//         /ws/** = anon
//         /resources/** = anon
//         /management/schoolroll/schoolRoll/** = anon
//         <!--  /management/security/user/** = anon -->
//         /management/tct/teacher/** = anon
//         <!-- /management/security/** = anon
//         /management/include/** = anon
//         /webapp/resources/** = anon -->
//         /styles/** = anon
//         /login/timeout = anon
//         /login = authc
//         /logout = logout
//         /** = user
        Map<String, String> chains = Maps.newHashMap();
        chains.put("/management/api/**", "anon");
        chains.put("/ehrLoginSSO/**", "anon");
        chains.put("/loginSSO/**", "anon");
        chains.put("/Captcha.jpg", "logout");
        chains.put("/styles/**", "anon");
        chains.put("/ws/**", "anon");
        chains.put("/resources/**", "anon");
        chains.put("/management/schoolroll/schoolRoll/**", "anon");
        chains.put("/management/tct/teacher/**", "anon");
        chains.put("/management/include/**", "anon");
        chains.put("/webapp/resources/**", "anon");
        chains.put("/login","authc");
        chains.put("/logout","logout");
        //chains.put("/**","user");
        bean.setFilterChainDefinitionMap(chains);
        return bean;
    }

    /**
     * @see org.apache.shiro.mgt.SecurityManager
     * @return
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userRealm());
        manager.setCacheManager(cacheManager());
        manager.setSessionManager(defaultWebSessionManager());
        return manager;
    }

    /**
     * @see DefaultWebSessionManager
     * @return
     */
    @Bean(name="sessionManager")
    public DefaultWebSessionManager defaultWebSessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
            sessionManager.setCacheManager(cacheManager());
            sessionManager.setGlobalSessionTimeout(1800000);
            sessionManager.setDeleteInvalidSessions(true);
            sessionManager.setSessionValidationSchedulerEnabled(true);
            sessionManager.setDeleteInvalidSessions(true);
        return sessionManager;
    }



    /**
     * @see --->AuthorizingRealm
     * @return
     */
    @Bean(name = "shiroRealm")
    @DependsOn(value="lifecycleBeanPostProcessor")
    public ShiroDbRealm userRealm() {
        ShiroDbRealm userRealm = new ShiroDbRealm();
        userRealm.setCacheManager(cacheManager());
        return userRealm;
    }


    @Bean
    public EhCacheManager cacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return cacheManager;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public CaptchaFormAuthenticationFilter captchaFormAuthenticationFilter() {
        return new CaptchaFormAuthenticationFilter();
    }

}