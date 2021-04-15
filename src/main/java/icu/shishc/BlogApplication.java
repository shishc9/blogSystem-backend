package icu.shishc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PackageName:icu.shishc
 * @Date:2021/3/14, 10:36
 * @Auther:ShiShc
 */

@SpringBootApplication
@MapperScan("icu.shishc.mapper")
@RestController
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @GetMapping("/")
    public String hello() {
        return "HelloFuckingWorld";
    }
}
