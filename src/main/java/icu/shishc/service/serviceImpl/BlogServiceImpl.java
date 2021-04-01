package icu.shishc.service.serviceImpl;

import icu.shishc.entity.Blog;
import icu.shishc.enumeration.BlogStatus;
import icu.shishc.mapper.BlogMapper;
import icu.shishc.service.BlogService;
import icu.shishc.util.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PackageName:icu.shishc.service.serviceImpl
 * @Date:2021/3/15, 9:39
 * @Auther:ShiShc
 */

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;


    @Override
    public Integer insert(Blog blog) {
        Blog blogTemp = blogMapper.getBlogByTitle(blog.getTitle());
        if(blogTemp != null) {
            MyUtils.print("Can't create blogs with the same title!");
            return -1;
        }
        blogMapper.insert(blog.getUsername(), blog.getTitle(), blog.getContent(), blog.getStatus().getKey());
        MyUtils.print("Blog created successfully");
        return 1;
    }


    @Override
    public Integer delete(Long id) {
        Integer flag = blogMapper.delete(id);
        return 1;
    }


    @Override
    public Integer update(Blog blog) {
        blogMapper.update(blog.getTitle(), blog.getContent(), blog.getStatus().getKey(), blog.getBlogId());
        return 1;
    }


    @Override
    public List<Blog> getAllBlog() {
        List<Blog> blogList = blogMapper.getAllBlog();
        return blogList;
    }


    @Override
    public Blog getBlogByTitle(String title) {
        Blog blog = blogMapper.getBlogByTitle(title);
        MyUtils.print("Successfully found the blog by title");
        return blog;
    }


    @Override
    public Blog getBlogByBID(Long bid) {
        Blog blog = blogMapper.getBlogByBID(bid);
        return blog;
    }


    @Override
    public List<Blog> getBlogByStatus(BlogStatus blogStatus) {
        List<Blog> list = blogMapper.getBlogByStatus(blogStatus);
        return list;
    }


    @Override
    public Integer getAllLike() {
        Integer allLike = blogMapper.getAllLike();
        return allLike;
    }


    @Override
    public Integer getAllReadNum() {
        Integer allReadNum = blogMapper.getAllReadNum();
        return allReadNum;
    }
}
