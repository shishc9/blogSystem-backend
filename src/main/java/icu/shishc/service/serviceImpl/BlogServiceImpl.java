package icu.shishc.service.serviceImpl;

import icu.shishc.Exception.CustomException;
import icu.shishc.entity.Blog;
import icu.shishc.enumeration.BlogStatus;
import icu.shishc.mapper.BlogMapper;
import icu.shishc.service.BlogService;
import icu.shishc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ShiShc
 * @PackageName:icu.shishc.service.serviceImpl
 * @Date:2021/3/15, 9:39
 */

@Slf4j
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;
    @Autowired
    UserService userService;


    @Override
    public List<Blog> getBlogSquare() {
        log.info("【BlogService】getBlogSquare::getSquare");
        return blogMapper.getBlogSquare();
    }


    @Override
    public List<Blog> getBlogByUserId(Long userId) throws CustomException {
        if(userService.getUserById(userId) == null) {
            log.warn("【BlogService】getBlogByUserId::user == null");
            throw new CustomException(HttpStatus.OK, "USER_NOT_EXIST");
        }
        log.info("【BlogService】getBlogByUserId, userid = {}", userId);
        return blogMapper.getBlogByUserId(userId);
    }


    @Override
    public Blog getBlogByTitle(String title) throws CustomException {
        title = title.trim();
        if("".equals(title)) {
            log.warn("【BlogService】getBlogByTitle::bad title, title = {}", title);
            throw new CustomException(HttpStatus.OK, "BAD_TITLE");
        }
        Blog blog = blogMapper.getBlogByTitle(title);
        log.info("【BlogService】getBlogByTitle::bid = {}", blog == null ? 0 : blog.getBlogId());
        return blog;
    }


    @Override
    public Blog getBlogByBID(Long bid) throws CustomException {
        if(!checkBid(bid)) {
            log.warn("【BlogService】getBlogByBID::bad userid, userid = {}", bid);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_BID");
        }
        Blog blog = blogMapper.getBlogByBID(bid);
        log.info("【BlogService】getBlogByBID::blog exist ? {}", blog == null ? 0 : 1);
        return blog;
    }


    @Override
    public List<Blog> getBlogByStatus(BlogStatus blogStatus, Long userId) throws CustomException {
        if(userService.getUserById(userId) == null) {
            log.warn("【BlogService】getBlogByStatus::user == null");
            throw new CustomException(HttpStatus.OK, "USER_NOT_EXIST");
        }
        if(blogStatus.getKey() != 0 && blogStatus.getKey() != 1) {
            log.warn("【BlogService】getBlogByStatus::bad blog status");
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_STATUS");
        }
        List<Blog> list = blogMapper.getBlogByStatus(blogStatus.getKey(), userId);
        log.info("【BlogService】getBlogByStatus::return list");
        return list;
    }


    @Override
    public Blog getPrevious(Long bid, Long userId) throws CustomException {
        if(userService.getUserById(userId) == null) {
            log.warn("【BlogService】getPrevious::user == null");
            throw new CustomException(HttpStatus.BAD_REQUEST, "USER_NOT_EXIST");
        }
        if(!checkBid(bid)) {
            log.warn("【BlogService】getPrevious::bad bid, bid = {}", bid);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_BID");
        }
        log.info("【BlogService】getPrevious::return blog");
        return blogMapper.getPrevious(bid, userId);
    }


    @Override
    public Blog getNext(Long bid, Long userId) throws CustomException {
        if(!checkBid(bid)) {
            log.warn("【BlogService】getNext::bad bid, bid = {}", bid);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_BID");
        }
        if(userService.getUserById(userId) == null) {
            log.warn("【BlogService】getNext::user == null");
            throw new CustomException(HttpStatus.BAD_REQUEST, "USER_NOT_EXIST");
        }
        log.info("【BlogService】getNext::return blog");
        return blogMapper.getNext(bid, userId);
    }


    @Override
    public Boolean checkBlog(Blog blog) throws CustomException {
        Long blogId = blog.getBlogId();
        String title = blog.getTitle().trim();
        String content = blog.getContent().trim();
        Long userId = blog.getUserId();
        if(!checkBid(blogId)) {
            log.warn("【BlogService】checkBlog::bad bid, bid = {}", blogId);
            return false;
        }
        if("".equals(title) || "".equals(content) || userService.getUserById(userId) == null) {
            log.warn("【BlogService】checkBlog: bad blog entity!");
            return false;
        }
        log.info("【BlogService】checkBlog: correct blog entity");
        return true;
    }


    @Override
    public Boolean checkBid(Long bid) {
        if(bid <= 0) {
            log.warn("【BlogService】checkBid:: bid <= 0, bid = {}", bid);
            return false;
        }
        return true;
    }


    @Override
    public Blog insert(Blog blog) throws CustomException {
        if(!checkBlog(blog)) {
            log.warn("【BlogService】insert::bad blog entity");
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_BLOG_ENTITY");
        }
        Blog blog1 = blogMapper.getBlogByTitle(blog.getTitle());
        if(blog1 != null) {
            log.warn("【BlogService】insert::the blog has exist! blogTitle = {}", blog.getTitle());
            throw new CustomException(HttpStatus.BAD_REQUEST, "BLOG_EXIST");
        }
        blogMapper.insert(blog.getUserId(), blog.getTitle(), blog.getContent(), blog.getStatus().getKey());
        Blog blog2 = blogMapper.getBlogByTitle(blog.getTitle());
        log.info("【BlogService】insert::add blog successfully! bID = {}", blog2.getBlogId());
        return blog2;
    }


    @Override
    public Integer delete(Long bid) throws CustomException {
        if(!checkBid(bid)) {
            log.warn("【BlogService】delete::bad bid, bid = {}", bid);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_BID");
        }
        log.info("【BlogService】delete::delete blog, bid = {}", bid);
        return blogMapper.delete(bid);
    }


    @Override
    public Blog update(Blog blog) throws CustomException {
        if(!checkBlog(blog)) {
            log.warn("【BlogService】update::bad blog entity");
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_BLOG_ENTITY");
        }
        Long bid = blog.getBlogId();
        Blog blog1 = blogMapper.getBlogByBID(bid);
        if(blog1 == null) {
            log.warn("【BlogService】update::the blog doesn't exist!");
            throw new CustomException(HttpStatus.BAD_REQUEST, "BLOG_NOT_EXIST");
        }
        blogMapper.update(blog.getTitle(), blog.getContent(), blog.getStatus().getKey(), bid);
        log.info("【BlogService】update::return blog");
        return blogMapper.getBlogByBID(bid);
    }
//
//
//    @Override
//    public Integer addLike(Long bid) throws CustomException {
//        if(bid <= 0) {
//            log.warn("【Service】BlogService::addLike: bad bid = {}", bid);
//            throw new CustomException(HttpStatus.BAD_REQUEST, "bad bid");
//        }
//        log.info("【Service】BlogService::addLike: bid = {}", bid);
//        return blogMapper.addLike(bid);
//    }
//
//
//    @Override
//    public Integer cancelLike(Long bid) throws CustomException {
//        if(bid <= 0) {
//            log.warn("【Service】BlogService::cancelLike: bad bid = {}", bid);
//            throw new CustomException(HttpStatus.BAD_REQUEST, "bad bid");
//        }
//        log.info("【Service】BlogService::cancelLike: bid = {}", bid);
//        return blogMapper.cancelLike(bid);
//    }
//
//
//    @Override
//    public Integer getAllLike() {
//        log.info("【Service】BlogService::getAllLike");
//        return blogMapper.getAllLike();
//    }
//
//
//    @Override
//    public Integer getAllReadNum() {
//        log.info("【Service】BlogService::getAllReadNum");
//        return blogMapper.getAllReadNum();
//    }
//

}
