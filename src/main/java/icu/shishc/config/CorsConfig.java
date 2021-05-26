package icu.shishc.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author ShiShc
 * @PackageName:icu.shishc.config
 * @Date:2021/3/16, 15:37
 * @DESC: 跨域配置，允许任何情况下跨域
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路由
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许证书（cookies）
                .allowCredentials(true)
                // 设置允许的方法
                .allowedMethods("*")
                // 跨域允许时间
                .maxAge(3600);
    }
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
//                        .allowCredentials(true)
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTION", "HEAD")
//                        .allowedHeaders("*")
//                        .exposedHeaders("*");
//            }
//        };
//    }
//    @Bean
//    public FilterRegistrationBean<CorsFilter> corsFilter() {
//
//        FilterRegistrationBean<CorsFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
//        CorsFilter corsFilter = new CorsFilter((request) -> {
//            CorsConfiguration corsConfiguration = new CorsConfiguration();
//            corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
//            corsConfiguration.setAllowedMethods(Collections.singletonList("\"GET\", \"POST\", \"PUT\", \"DELETE\", \"OPTION\", \"HEAD\""));
//            corsConfiguration.setAllowCredentials(true);
//            return corsConfiguration;
//        });
//        filterFilterRegistrationBean.setFilter(corsFilter);
//        filterFilterRegistrationBean.setUrlPatterns(Collections.singletonList("/*"));
//        return filterFilterRegistrationBean;
//    }
}
