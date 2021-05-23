package icu.shishc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ShiShc
 * @PackageName:icu.shishc.config
 * @Date:2021/3/16, 15:37
 * @DESC: 跨域配置，允许任何情况下跨域
 */
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowCredentials(true)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTION", "HEAD")
                        .allowedHeaders("*")
                        .exposedHeaders("*");
            }
        };
    }
}
