package icu.shishc.config;

import javax.servlet.Filter;
import icu.shishc.filter.MyAuthFilter;
import icu.shishc.filter.MyRestFilter;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
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
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // 设置拦截器
        // 注意拦截顺序
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //one api in Application. default response.
        filterChainDefinitionMap.put("/", "anon");

        // four Api in LoginController
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/join", "anon");
        filterChainDefinitionMap.put("/noauth", "anon");
        filterChainDefinitionMap.put("/logout", "authc");
        // one api in MyErrorController
        filterChainDefinitionMap.put("/error", "anon");

        // one api in MyTestController
        filterChainDefinitionMap.put("/test/**", "anon");

        // thirteen api in BlogController
        filterChainDefinitionMap.put("/blogs/square", "anon");
        filterChainDefinitionMap.put("/blogs/u/**", "anon");
        filterChainDefinitionMap.put("/blogs/blog", "rest[BLOG]");
        filterChainDefinitionMap.put("/blogs/**", "anon");

        //three api in UserController
        filterChainDefinitionMap.put("/users/user", "rest[USER]");
        filterChainDefinitionMap.put("/users/pass_change", "rest[CHANGE]");
        filterChainDefinitionMap.put("/users/management", "rest[MANAGE]");
        filterChainDefinitionMap.put("/users/**", "anon");

        //two api in CommentController
        filterChainDefinitionMap.put("/comments/comment", "rest[COMMENT]");
        filterChainDefinitionMap.put("/comments/**", "anon");

        //three api in LikeController
        filterChainDefinitionMap.put("/likes/like", "rest[LIKE]");

        // five api in AttentionController
        filterChainDefinitionMap.put("/attentions/attention", "rest[ATTENTION]");
        filterChainDefinitionMap.put("/attentions/u/attention", "anon");

        // four api in CollectionController
        filterChainDefinitionMap.put("/collections/collection", "rest[COLLECTION]");
        filterChainDefinitionMap.put("/attentions/u", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        //设置默认拦截器
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc", new MyAuthFilter());
        filterMap.put("rest", new MyRestFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 注入安全管理器
     * @param userRealm Shiro userRealm
     * @return wsm
     */
    @Bean(name = "defaultWebSecurityManager")
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
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }


    /**
     * MD5加盐加密10次
     * @return HashedCredentialsMatcher
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(10);
        return hashedCredentialsMatcher;
    }


    @Bean
    public AllowAllCredentialsMatcher allowAllCredentialsMatcher() {
        return new AllowAllCredentialsMatcher();
    }
}
