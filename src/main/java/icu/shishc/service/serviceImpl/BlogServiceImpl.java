package icu.shishc.service.serviceImpl;

import icu.shishc.Exception.CustomException;
import icu.shishc.entity.Blog;
import icu.shishc.enumeration.BlogStatus;
import icu.shishc.mapper.BlogMapper;
import icu.shishc.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PackageName:icu.shishc.service.serviceImpl
 * @Date:2021/3/15, 9:39
 * @Auther:ShiShc
 */

@Slf4j
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;


    @Override
    public Blog insert(Blog blog) throws CustomException {
        Blog blog1 = blogMapper.getBlogByTitle(blog.getTitle());
        if(blog1 != null) {
            log.warn("【Service】BlogService::insert:the blog has exist! blogTitle = {}", blog.getTitle());
            throw new CustomException(HttpStatus.BAD_REQUEST, "Blog Has Exist!");
        }
        blogMapper.insert(blog.getUsername(), blog.getTitle(), blog.getContent(), blog.getStatus().getKey());
        Blog blog2 = blogMapper.getBlogByTitle(blog.getTitle());
        log.info("【Service】BlogService::insert:add blog successfully! bID = {}", blog2.getBlogId());
        return blog2;
    }


    @Override
    public Integer delete(Long id) throws CustomException {
        if(id <= 0) {
            log.warn("【Service】BlogService::delete:Illegal param, bid = {}", id);
            throw new CustomException(HttpStatus.BAD_REQUEST, "bad param");
        }
        blogMapper.delete(id);
        log.info("【Service】BlogService::delete: delete blog, bid = {}", id);
        return 1;
    }


    @Override
    public Blog update(Blog blog) throws CustomException {
        Long id = blog.getBlogId();
        Blog blog1 = blogMapper.getBlogByBID(id);
        if(null == blog1) {
            log.warn("【Service】BlogService::update: the blog doesn't exist! bid = {}", id);
            throw new CustomException(HttpStatus.BAD_REQUEST, "blog doesn't exist!");
        }
        blogMapper.update(blog.getTitle(), blog.getContent(), blog.getStatus().getKey(), id);
        log.info("【Service】BlogService::update: update successfully! bID = {}", id);
        Blog blog2 = blogMapper.getBlogByBID(id);
        return blog2;
    }


    @Override
    public List<Blog> getAllBlog() {
        log.info("【Service】BlogService::getAllBlog");
        List<Blog> blogList = blogMapper.getAllBlog();
        return blogList;
    }


    @Override
    public Blog getBlogByTitle(String title) {
        Blog blog = blogMapper.getBlogByTitle(title);
        log.info("【Service】BlogService::getByTitle: bid = {}", blog == null ? 0 : blog.getBlogId());
        return blog;
    }


    @Override
    public Blog getBlogByBID(Long bid) throws CustomException{
        if(bid <= 0) {
            log.warn("【Service】BlogService::getByID: bad bid = {}", bid);
            throw new CustomException(HttpStatus.BAD_REQUEST, "bad bid");
        }
        Blog blog = blogMapper.getBlogByBID(bid);
        log.info("【Service】BlogService:getByID: bid = {}", bid);
        return blog;
    }


    @Override
    public List<Blog> getBlogByStatus(BlogStatus blogStatus) {
        log.info("【Service】BlogService::getByStatus");
        return blogMapper.getBlogByStatus(blogStatus.getKey());
    }


    @Override
    public Integer getAllLike() {
        log.info("【Service】BlogService::getAllLike");
        return blogMapper.getAllLike();
    }


    @Override
    public Integer getAllReadNum() {
        log.info("【Service】BlogService::getAllReadNum");
        return blogMapper.getAllReadNum();
    }
}
