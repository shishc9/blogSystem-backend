package icu.shishc.controller;

import icu.shishc.entity.Blog;
import icu.shishc.service.BlogService;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

/**
 * @PackageName:icu.shishc.controller
 * @Date:2021/3/15, 14:18
 * @Auther:ShiShc
 */

@RestController
public class MyTestController {

    private BlogService blogService;

    public MyTestController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * Insert好像有点问题，这个参数形式应该修改一下
     * @param blog
     * @return
     */
    @PostMapping("/addBlog")
    public Integer foo(@RequestParam("blog") Blog blog) {
        Integer result = blogService.insert(blog);
        System.out.println(result);
        return result;
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
    public void test(@RequestParam String s) {
        System.out.println(s);
    }
}
