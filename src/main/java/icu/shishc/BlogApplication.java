package icu.shishc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @PackageName:icu.shishc
 * @Date:2021/3/14, 10:36
 * @Auther:ShiShc
 */

@SpringBootApplication
@MapperScan("icu.shishc.mapper")
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
