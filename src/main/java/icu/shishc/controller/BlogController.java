package icu.shishc.controller;

import icu.shishc.Exception.CustomException;
import icu.shishc.dto.MyDTO;
import icu.shishc.entity.Blog;
import icu.shishc.entity.Pager;
import icu.shishc.enumeration.BlogStatus;
import icu.shishc.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * @PackageName:icu.shishc.controller
 * @Date:2021/3/15, 15:56
 * @Auther:ShiShc
 */

@Slf4j
@RestController
@RequestMapping("/blog")
public class BlogController {
    /**
     * 博客接口
     */
    private BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }


    /**
     * 获取所有博客
     * @return
     */
    @GetMapping("/get/all")
    public MyDTO getAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ) throws CustomException {
        log.info("【Controller】Blog::get-all");
        Pager<Blog> pager = blogService.getAllBlog(page, size);
        return MyDTO.successDTO(pager);
    }


    /**
     * 通过标题查找博客
     * @param title
     * @return
     */
    @GetMapping("/get/by-title")
    public MyDTO getByTitle(
            @RequestParam("title") String title
    ) throws CustomException {
        if(title.trim().equals("")) {
            return MyDTO.wrongDTO(HttpStatus.BAD_REQUEST, "title is null");
        }
        log.info("【Controller】Blog::get-by-title：title = {}", title);
        Blog blog = blogService.getBlogByTitle(title);
        return MyDTO.successDTO(blog);
    }


    /**
     * 通过bid查找博客
     * @param bid
     * @return
     */
    @GetMapping("/get/by-id")
    public MyDTO getById(@RequestParam("bid") Long bid
    ) throws CustomException {
        log.info("【Controller】Blog::get-by-id：bid = {}", bid);
        Blog blog = blogService.getBlogByBID(bid);
        return MyDTO.successDTO(blog);
    }


    /**
     * 通过状态查找博客
     * @param blogStatus
     * @return
     */
    @GetMapping("/get/by-status")
    public MyDTO getByStatus(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam("blogStatus")BlogStatus blogStatus
    ) throws CustomException {
        log.info("【Controller】Blog::get-by-status：blogStatus = {}", blogStatus);
        Pager<Blog> pager = blogService.getBlogByStatus(page, size, blogStatus);
        return MyDTO.successDTO(pager);
    }


    /**
     * 获取所有点赞
     * @return
     */
    @GetMapping("/get/all-like")
    public MyDTO getAllLike(
    ) throws CustomException {
        log.info("【Controller】Blog::get-all-like");
        Integer allLike = blogService.getAllLike();
        return MyDTO.successDTO(allLike);
    }


    /**
     * 获取所有阅读
     * @return
     */
    @GetMapping("/get/all-read")
    public MyDTO getAllReadNum(
    ) throws CustomException {
        log.info("【Controller】Blog::get-all-read");
        Integer allReadNum = blogService.getAllReadNum();
        return MyDTO.successDTO(allReadNum);
    }


    /**
     * 添加博客
     * @param blog
     * @return
     */
    @PostMapping("/add")
    public MyDTO insertBlog(
        @RequestBody Blog blog
    ) throws CustomException {
        if(!blogService.checkBlog(blog)) {
            return MyDTO.wrongDTO(HttpStatus.BAD_REQUEST, "bad blog entity");
        }
        log.info("【Controller】Blog::add：blog = {}", blog);
        Blog blog2 = blogService.insert(blog);
        log.info("【Controller】Blog::after insert: get：title = {}", blog2.getTitle());
        return MyDTO.successDTO(blog2);
    }


    /**
     * 更新博客
     * @param blog
     * @return
     */
    @PostMapping("/update")
    public MyDTO updateBlog(
        @RequestBody Blog blog
    ) throws CustomException {
        if(!blogService.checkBlog(blog)) {
            return MyDTO.wrongDTO(HttpStatus.BAD_REQUEST, "bad blog entity");
        }
        log.info("【Controller】Blog::update：blog = {}", blog);
        Blog blog2 = blogService.update(blog);
        log.info("【Controller】Blog::after update: get：bid = {}", blog2.getBlogId());
        return MyDTO.successDTO(blog2);
    }


    /**
     * 删除博客
     * @param bid
     * @return
     */
    @GetMapping("/delete")
    public MyDTO deleteBlog(
            @RequestParam("bid") Long bid
    ) throws CustomException {
        log.info("【Controller】Blog::delete：bid = {}", bid);
        Integer status = blogService.delete(bid);
        if(null == blogService.getBlogByBID(bid)) {
            log.info("【Controller】Blog::delete success");
            return MyDTO.successDTO(status);
        } else {
            log.info("【Controller】Blog::delete failed! bid = {}", bid);
            return MyDTO.wrongDTO(HttpStatus.INTERNAL_SERVER_ERROR, "bLOG::delete failed!");
        }
    }


    @GetMapping("/get/previous")
    public MyDTO getPrevious(
            @RequestParam("bid") Long bid
    ) throws CustomException {
        log.info("【Controller】Blog::getPrevious: bid = {}", bid);
        Blog blog = blogService.getPrevious(bid);
        return MyDTO.successDTO(blog);
    }


    @GetMapping("/get/next")
    public MyDTO getNext(
            @RequestParam("bid") Long bid
    ) throws CustomException {
        log.info("【Controller】Blog::getPrevious: bid = {}", bid);
        Blog blog = blogService.getNext(bid);
        return MyDTO.successDTO(blog);
    }

}
