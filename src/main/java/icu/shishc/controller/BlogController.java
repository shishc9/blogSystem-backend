package icu.shishc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import icu.shishc.Exception.CustomException;
import icu.shishc.dto.MyDTO;
import icu.shishc.entity.Blog;
import icu.shishc.entity.Pager;
import icu.shishc.enumeration.BlogStatus;
import icu.shishc.service.BlogService;
import icu.shishc.util.PagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ShiShc
 * @PackageName:icu.shishc.controller
 * @Date:2021/3/15, 15:56
 * 博客控制器
 */
@Slf4j
@RestController
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * 获取博客广场
     * @param page page
     * @param size size
     * @return MyDTO
     */
    @GetMapping("/square")
    public MyDTO getSquare(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ) {
        PageHelper.startPage(page, size);
        List<Blog> blogSquare = blogService.getBlogSquare();
        Pager pager = PagerUtils.getPager(new PageInfo<>(blogSquare));
        log.info("【BlogController】getSquare::return square");
        return MyDTO.successDTO(pager);
    }


    /**
     * 列出某个用户的所有博客
     * @param page page
     * @param size size
     * @return MyDTO
     * @throws CustomException .
     */
    @GetMapping("/u/{userId}")
    public MyDTO getUserBlogs(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @PathVariable("userId") Long userId
    ) throws CustomException {
        log.info("【BlogController】getUserBlogs::userId = {}", userId);
        PageHelper.startPage(page, size);
        List<Blog> list = blogService.getBlogByUserId(userId);
        Pager pager = PagerUtils.getPager(new PageInfo<>(list));
        return MyDTO.successDTO(pager);
    }
//
//
    /**
     * 通过标题查找博客
     * @param title 博客标题
     * @return MyDTO
     */
    @GetMapping("/{title}")
    public MyDTO getByTitle(
            @PathVariable("title") String title
    ) throws CustomException {
        Blog blog = blogService.getBlogByTitle(title);
        log.info("【Controller】Blog::get-by-title, title = {}", title);
        return MyDTO.successDTO(blog);
    }


//    /**
//     * 废弃的方法
//     * 通过bid查找博客
//     * @param bid
//     * @return
//     */
//    @GetMapping("/get/by-id")
//    public MyDTO getById(@RequestParam("bid") Long bid
//    ) throws CustomException {
//        log.info("【Controller】Blog::get-by-id：bid = {}", bid);
//        Blog blog = blogService.getBlogByBID(bid);
//        return MyDTO.successDTO(blog);
//    }
//
//
    /**
     * 通过状态查找博客
     * @return MyDTO
     */
    @GetMapping("/u/{userId}/{status}")
    public MyDTO getByStatus(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @PathVariable("userId") Long userId,
            @PathVariable("status") int status
    ) throws CustomException {
        BlogStatus blogStatus = BlogStatus.ValueOf(status);
        log.info("【BlogController】getByStatus::blogStatus = {}, userId = {}", blogStatus, userId);
        PageHelper.startPage(page, size);
        List<Blog> list = blogService.getBlogByStatus(blogStatus, userId);
        Pager pager = PagerUtils.getPager(new PageInfo<>(list));
        return MyDTO.successDTO(pager);
    }
//
//
//    /**
//     * 点赞
//     * @param bid
//     * @return
//     * @throws CustomException
//     */
//    @GetMapping("/add/like")
//    public MyDTO addLike(@RequestParam Long bid) throws CustomException {
//        log.info("【Controller】Blog::addLike, bid = {}", bid);
//        return MyDTO.successDTO(blogService.addLike(bid));
//    }
//
//
//    /**
//     * 取消点赞
//     * @param bid
//     * @return
//     * @throws CustomException
//     */
//    @GetMapping("/delete/like")
//    public MyDTO cancelLike(@RequestParam Long bid) throws CustomException {
//        log.info("【Controller】Blog::cancelLike, bid = {}", bid);
//        return MyDTO.successDTO(blogService.cancelLike(bid));
//    }
//
//    /**
//     * 获取所有点赞
//     * @return
//     */
//    @GetMapping("/get/all-like")
//    public MyDTO getAllLike(
//    ) throws CustomException {
//        log.info("【Controller】Blog::get-all-like");
//        Integer allLike = blogService.getAllLike();
//        return MyDTO.successDTO(allLike);
//    }
//
//
//    /**
//     * 获取所有阅读
//     * @return
//     */
//    @GetMapping("/get/all-read")
//    public MyDTO getAllReadNum(
//    ) throws CustomException {
//        log.info("【Controller】Blog::get-all-read");
//        Integer allReadNum = blogService.getAllReadNum();
//        return MyDTO.successDTO(allReadNum);
//    }
//
//

    /**
     * 用户当前博客的上一篇博客
     * @param bid 当前博客id
     * @param userId 用户id
     * @return MyDTO
     * @throws CustomException .
     */
    @GetMapping("/u/{userId}/{bid}/previous")
    public MyDTO getPrevious(
            @PathVariable("bid") Long bid,
            @PathVariable("userId") Long userId
    ) throws CustomException {
        log.info("【Controller】Blog::getPrevious: bid = {},userid = {}", bid, userId);
        Blog blog = blogService.getPrevious(bid, userId);
        return MyDTO.successDTO(blog);
    }


    /**
     * 获取当前博客的下一篇博客
     * @param bid 当前博客id
     * @param userId 当前用户id
     * @return MyDTO
     * @throws CustomException .
     */
    @GetMapping("/u/{userId}/{bid}/next")
    public MyDTO getNext(
            @PathVariable("bid") Long bid,
            @PathVariable("userId") Long userId
    ) throws CustomException {
        log.info("【Controller】Blog::getPrevious: bid = {}, user = {}", bid, userId);
        Blog blog = blogService.getNext(bid, userId);
        return MyDTO.successDTO(blog);
    }
//
//
    /**
     * 添加博客
     * @param blog 博客实体
     * @return MyDTO
     */
    @RequestMapping(value = "/blog", method = RequestMethod.POST)
    public MyDTO insertBlog(
            @RequestBody Blog blog
    ) throws CustomException {
        Blog blog1 = blogService.insert(blog);
        log.info("【BlogController】insertBlog::insert blog：title = {}", blog1.getTitle());
        return MyDTO.successDTO(blog1);
    }


    /**
     * 更新博客
     * @param blog 博客实体
     * @return MyDTO
     */
    @RequestMapping(value = "/blog", method = RequestMethod.PUT)
    public MyDTO updateBlog(
        @RequestBody Blog blog
    ) throws CustomException {
        log.info("【BlogController】updateBlog::before blog = {}", blog);
        Blog blog2 = blogService.update(blog);
        log.info("【BlogController】updateBlog::after blog = {}", blog2);
        return MyDTO.successDTO(blog2);
    }


    /**
     * 删除博客
     * @param bid 博客id
     * @return MyDTO
     */
    @RequestMapping(value = "/blog", method = RequestMethod.DELETE)
    public MyDTO deleteBlog(
            @RequestParam("bid") Long bid
    ) throws CustomException {
        log.info("【Controller】Blog::delete：bid = {}", bid);
        Integer status = blogService.delete(bid);
        return MyDTO.successDTO(status);
    }
}
