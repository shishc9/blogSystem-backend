package icu.shishc.controller;

import icu.shishc.dto.MyDTO;
import icu.shishc.entity.Blog;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Closer
 * @PackageName:icu.shishc.controller
 * @Date:2021/3/15, 14:18
 */
@RestController
@RequestMapping("/test")
public class MyTestController {

    @ApiOperation("返回一个字符串")
    @GetMapping("/returnString")
    public String hello() {
        return "Hello, world!";
    }


    @ApiOperation("传入一个字符串测试参数接收")
    @GetMapping("/testString")
    public void hello1(@RequestParam("s") String s) {
        System.out.println(s);
    }


    @ApiOperation("返回对象")
    @GetMapping("/testBlog")
    public Blog testBlog() {
        return new Blog();
    }


    @ApiOperation("返回一个404")
    @GetMapping("/test404")
    public MyDTO test404() {
        return MyDTO.wrongDTO(HttpStatus.BAD_REQUEST, "hello404");
    }

    @GetMapping("/tests")
    public void test01() {
        System.out.println("test01");
    }

    @PostMapping("/tests")
    public void test02() {
        System.out.println("test02");
    }

    @DeleteMapping("/tests")
    public void test03() {
        System.out.println("test03");
    }
}
