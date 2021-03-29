package icu.shishc.controller;

import icu.shishc.entity.Blog;
import icu.shishc.enumeration.BlogStatus;
import icu.shishc.service.BlogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @PackageName:icu.shishc.controller
 * @Date:2021/3/15, 15:56
 * @Auther:ShiShc
 */

@RestController
@RequestMapping("/blogbackend/blog")
public class BlogController {

    private BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/getall")
    public List<Blog> getAll() {
        List<Blog> list = blogService.getAllBlog();
        return list;
    }

    @GetMapping("/getbytitle")
    public Blog getByTitle(@RequestParam("title") String title) {
        Blog blog = blogService.getBlogByTitle(title);
        return blog;
    }

    @GetMapping("/getbyid")
    public Blog getById(@RequestParam("bid") Long bid) {
        Blog blog = blogService.getBlogByBID(bid);
        return blog;
    }

    @GetMapping("/getbystatus")
    public List<Blog> getByStatus(@RequestParam("blogStatus")BlogStatus blogStatus) {
        List<Blog> list = blogService.getBlogByStatus(blogStatus);
        return list;
    }

    @GetMapping("/getalllike")
    public Integer getAllLike() {
        Integer allLike = blogService.getAllLike();
        return allLike;
    }

    @GetMapping("/getallread")
    public Integer getAllReadNum() {
        Integer allReadNum = blogService.getAllReadNum();
        return allReadNum;
    }

    @PostMapping("/add")
    public Blog insertBlog(@RequestParam("blog") Blog blog) {
        blogService.insert(blog);
        Blog blogReturn = blogService.getBlogByTitle(blog.getTitle());
        return blogReturn;
    }

}
