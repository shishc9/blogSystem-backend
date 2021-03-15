package icu.shishc.service.serviceImpl;

import icu.shishc.entity.Blog;
import icu.shishc.enumeration.BlogStatus;
import icu.shishc.service.BlogService;

import java.util.List;

/**
 * @PackageName:icu.shishc.service.serviceImpl
 * @Date:2021/3/15, 9:39
 * @Auther:ShiShc
 */

public class BlogServiceImpl implements BlogService {

    @Override
    public int insert(Blog blog) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int update(Blog blog) {
        return 0;
    }

    @Override
    public List<Blog> getAllBlog() {
        return null;
    }

    @Override
    public Blog getBlogByTitle(String title) {
        return null;
    }

    @Override
    public Blog getBlogByBID(Long bid) {
        return null;
    }

    @Override
    public List<Blog> getBlogByStatus(BlogStatus blogStatus) {
        return null;
    }

    @Override
    public Integer getAllLike() {
        return null;
    }

    @Override
    public Integer getAllReadNum() {
        return null;
    }
}
