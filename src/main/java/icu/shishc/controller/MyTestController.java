package icu.shishc.controller;

import icu.shishc.entity.Blog;
import icu.shishc.enumeration.BlogStatus;
import icu.shishc.mapper.BlogMapper;
import icu.shishc.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

/**
 * @PackageName:icu.shishc.controller
 * @Date:2021/3/15, 14:18
 * @Auther:ShiShc
 */

@Slf4j
@RestController
public class MyTestController {

    private BlogService blogService;

    @Autowired
    BlogMapper blogMapper;

    public MyTestController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * Insert好像有点问题，这个参数形式应该修改一下
     * @param
     * @return
     */
    @PostMapping("/add")
    public Blog insertBlog(@RequestParam("username") String username,
                           @RequestParam("title") String title,
                           @RequestParam("content") String content,
                           @RequestParam("status") BlogStatus status) {
        log.info("【TestController】 INSERT : username = {}, title = {}, status = {}", username, title, status);
        blogMapper.insert(username, title, content, status.getKey());
        Blog blogReturn = blogService.getBlogByTitle(title);
        return blogReturn;
    }

    @PostMapping("/getbytitle")
    public Blog getByTitle(@RequestParam("title") String title) {
        Blog blog = blogService.getBlogByTitle(title);
        System.out.println(blog);
        return blog;
    }

    @GetMapping("/getAllblog")
    public List<Blog> getAllBlog() {
        List<Blog> allBlog = blogService.getAllBlog();
        for(Iterator ite = allBlog.iterator(); ite.hasNext();) {
            System.out.println(ite.next());
        }
        return allBlog;
    }

    @PostMapping("/test")
    public String test(@RequestParam String s) {
        System.out.println(s);
        return s;
    }
}
