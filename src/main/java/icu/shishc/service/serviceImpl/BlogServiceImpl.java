package icu.shishc.service.serviceImpl;

import icu.shishc.entity.Blog;
import icu.shishc.enumeration.BlogStatus;
import icu.shishc.mapper.BlogMapper;
import icu.shishc.service.BlogService;
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
        Integer result = blogMapper.insert(blog);
        return result;
    }

    @Override
    public Integer delete(int id) {
        return 0;
    }

    @Override
    public Integer update(Blog blog) {
        return 0;
    }

    @Override
    public List<Blog> getAllBlog() {
        List<Blog> blogList = blogMapper.getAllBlog();
        return blogList;
    }

    @Override
    public Blog getBlogByTitle(String title) {
        Blog blog = blogMapper.getBlogByTitle(title);
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
