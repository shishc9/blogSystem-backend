package icu.shishc.config;

import com.sun.net.httpserver.Filter;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        //ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
//
//        bean.setSecurityManager(defaultWebSecurityManager);
//        Map<String, String> filterMap = new LinkedHashMap<>();
//        // authc表示登录才能访问
//        filterMap.put("/shiro/testIndex", "authc");
//        // 登录跳转
//        bean.setLoginUrl("/shiro/testLogin");
//        // 需要相应权限才能访问
//        filterMap.put("/shiro/testAuth", "perms[BLOGGER]");
//        // 未授权跳转
//        bean.setUnauthorizedUrl("/shiro/xx");
//        bean.setFilterChainDefinitionMap(filterMap);
//        return bean;
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        // 设置shiro内置过滤器
        //Map<String, Filter> filters = new LinkedHashMap<>();
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/login");
        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 注入安全管理器
     * @param userRealm
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    /**
     * 注入自定义身份认证
     * @return
     */
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }
}
