package icu.shishc.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

/**
 * @date: 2021-5-26, 16:25
 * @author: ShiShc
 */
@Configuration
public class CorsConfig {
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        CorsFilter corsFilter = new CorsFilter((request) -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
            corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD"));
            corsConfiguration.setAllowCredentials(true);
            return corsConfiguration;
        });
        filterFilterRegistrationBean.setFilter(corsFilter);
        filterFilterRegistrationBean.setUrlPatterns(Collections.singletonList("/*"));
        return filterFilterRegistrationBean;
    }
}
