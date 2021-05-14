package icu.shishc.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Closer
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        // 设置shiro内置过滤器
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
        // 设置拦截器
        // 注意拦截顺序
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // one api in Application. default response.
        filterChainDefinitionMap.put("/", "anon");

        // four Api in LoginController
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/register", "anon");
        filterChainDefinitionMap.put("/noAuth", "anon");
        filterChainDefinitionMap.put("/logout", "authc");

        // one api in MyErrorController
        filterChainDefinitionMap.put("/error", "anon");

        // one api in MyTestController
        filterChainDefinitionMap.put("/test/**", "anon");

        // thirteen api in BlogController
        filterChainDefinitionMap.put("/blog/get/**", "anon");
        filterChainDefinitionMap.put("/blog/add/like", "authc");
        filterChainDefinitionMap.put("/blog/delete/like", "authc");
        filterChainDefinitionMap.put("/blog/**", "perms[BLOGGER]");

        //three api in UserController
        filterChainDefinitionMap.put("/user/get/**", "anon");
        filterChainDefinitionMap.put("/user/**", "authc");

        //three api in CommentController
        filterChainDefinitionMap.put("/comment/get/**", "anon");
        filterChainDefinitionMap.put("/comment/add", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 注入安全管理器
     * @param userRealm Shiro userRealm
     * @return wsm
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    /**
     * 注入自定义身份认证
     * @return UserRealm
     */
    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }
}
