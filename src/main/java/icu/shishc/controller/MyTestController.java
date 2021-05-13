package icu.shishc.controller;

import icu.shishc.Exception.CustomException;
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


    @PostMapping("/testBody")
    public void hello2(@RequestBody String s) {
        System.out.println(s);
    }


    @ApiOperation("返回对象")
    @GetMapping("/testBlog")
    public Blog testBlog() {
        Blog blog = new Blog();
        return blog;
    }


    @ApiOperation("返回一个404")
    @GetMapping("/test404")
    public Blog test404() throws Exception {
        throw new CustomException(HttpStatus.NOT_FOUND, "hello404");
    }


    @ApiOperation("返回一个DTO")
    @GetMapping("/test-mydto")
    public MyDTO testMyDTO() {
        return MyDTO.successDTO(new Blog());
    }

}
