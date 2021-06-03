package icu.shishc.service.serviceImpl;

import icu.shishc.entity.User;
import icu.shishc.exception.CustomException;
import icu.shishc.entity.Blog;
import icu.shishc.mapper.BlogMapper;
import icu.shishc.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    LikeService likeService;
    @Autowired
    CollectionService collectionService;
    @Autowired
    CommentService commentService;


    @Override
    public List<Blog> getBlogSquare() {
        log.info("【BlogService】getBlogSquare::getSquare");
        return blogMapper.getBlogSquare();
    }


    @Override
    public List<Blog> getBlogByUserId(Long userId) throws CustomException {
        if(userService.getUserById(userId) == null) {
            log.warn("【BlogService】getBlogByUserId::user == null");
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        log.info("【BlogService】getBlogByUserId, userid = {}", userId);
        return blogMapper.getBlogByUserId(userId);
    }


    @Override
    public Blog getBlogByTitle(String title) throws CustomException {
        title = title.trim();
        if("".equals(title)) {
            log.warn("【BlogService】getBlogByTitle::bad title, title = {}", title);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        Blog blog = blogMapper.getBlogByTitle(title);
        log.info("【BlogService】getBlogByTitle::bid = {}", blog == null ? 0 : blog.getBlogId());
        return blog;
    }

    @Override
    public List<Blog> fuzzyQueryBlog(String title) throws CustomException {
        title = title.trim();
        if("".equals(title)) {
            log.warn("【BlogService】getBlogByTitle::bad title, title = {}", title);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        return blogMapper.fuzzyQueryBlog(title);
    }

    @Override
    public List<Blog> getBlogByList(List<Long> list) {
        return blogMapper.getBlogByList(list);
    }


    @Override
    public Blog getBlogByBID(Long bid) throws CustomException {
        if(!checkBid(bid)) {
            log.warn("【BlogService】getBlogByBID::bad userid, userid = {}", bid);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        Blog blog = blogMapper.getBlogByBID(bid);
        blogMapper.addRead(bid);
        log.info("【BlogService】getBlogByBID::blog exist ? {}", blog == null ? 0 : 1);
        return blog;
    }


    @Override
    public Blog getPrevious(Long bid, Long userId) throws CustomException {
        if(userService.getUserById(userId) == null) {
            log.warn("【BlogService】getPrevious::user == null");
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        if(!checkBid(bid)) {
            log.warn("【BlogService】getPrevious::bad bid, bid = {}", bid);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        log.info("【BlogService】getPrevious::return blog");
        return blogMapper.getPrevious(bid, userId);
    }


    @Override
    public Blog getNext(Long bid, Long userId) throws CustomException {
        if(!checkBid(bid)) {
            log.warn("【BlogService】getNext::bad bid, bid = {}", bid);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        if(userService.getUserById(userId) == null) {
            log.warn("【BlogService】getNext::user == null");
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
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
        if(blogMapper.getBlogByBID(bid) == null) {
            log.warn("【BlogService】checkBid:: bid <= 0, bid = {}", bid);
            return false;
        }
        return true;
    }


    @Override
    public void updateBlogNum(Long bid, Integer commentNum, Integer collectionNum) {
        blogMapper.updateBlogNum(bid, commentNum, collectionNum);
    }

    @Override
    public Blog insert(Blog blog) throws CustomException {
        // 博客实体检查
        String title = blog.getTitle().trim();
        String content = blog.getContent().trim();
        Long userId = blog.getUserId();
        if("".equals(title) || "".equals(content) || userService.getUserById(userId) == null) {
            log.warn("【BlogService】checkBlog: bad blog entity!");
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        Blog blog1 = blogMapper.getBlogByTitle(title);
        if(blog1 != null) {
            log.warn("【BlogService】insert::the blog has exist! blogTitle = {}", blog.getTitle());
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        blogMapper.insert(blog.getUserId(), blog.getUsername(), title, blog.getContent().trim());
        Blog blog2 = blogMapper.getBlogByTitle(title);
        // 增加用户的发布数量
        User user = userService.getUserById(blog.getUserId());
        userService.updateUserNum(blog.getUserId(), user.getPostCount() + 1, user.getLikeCount(), user.getCollectionCount(), user.getFollowing(), user.getFollowed());
        log.info("【BlogService】insert::add blog successfully! bID = {}", blog2.getBlogId());
        return blog2;
    }

    @Override
    public Integer delete(Long uid, Long bid) throws CustomException {
        if(!checkBid(bid) || !uid.equals(blogMapper.getUserByBid(bid))) {
            log.warn("【BlogService】delete::bad bid, bid = {}", bid);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        log.info("【BlogService】delete::delete blog, bid = {}", bid);
//        User user = userService.getUserById(blogMapper.getUserByBid(bid));
//        likeService.deleteBlogLikes(bid);
//        // 删除收藏该博客的记录
//        collectionService.deleteBlogCollection(bid);
//        // 删除该博客的所有评论
//        commentService.deleteBlogComments(bid);
//        // 更新该博客所属用户的信息
//        userService.updateUserNum(user.getUserId(), user.getPostCount() - 1, user.getLikeCount(), user.getCollectionCount(), user.getFollowing(), user.getFollowed());
        return blogMapper.delete(bid);
    }


    @Override
    public Blog update(Long uid, Blog blog) throws CustomException {
        if(!checkBlog(blog) || !uid.equals(blog.getUserId())) {
            log.warn("【BlogService】update::bad blog entity");
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        Long bid = blog.getBlogId();
        Blog blog1 = blogMapper.getBlogByBID(bid);
        if(blog1 == null) {
            log.warn("【BlogService】update::the blog doesn't exist!");
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        blogMapper.update(blog.getTitle().trim(), blog.getContent().trim(), bid);
        log.info("【BlogService】update::return blog");
        return blogMapper.getBlogByBID(bid);
    }
}
