package icu.shishc.service;

import icu.shishc.entity.Blog;
import icu.shishc.enumeration.BlogStatus;


import java.util.List;

public interface BlogService{


    /**
     * 返回所有博文
     * @return
     */
    List<Blog> getAllBlog();


    /**
     * 根据标题查找博客
     * @param title
     * @return
     */
    Blog getBlogByTitle(String title);


    /**
     * 根据BID查找博客
     * @param bid
     * @return
     */
    Blog getBlogByBID(Long bid);


    /**
     * 根据状态查找博客
     * @param blogStatus
     * @return
     */
    List<Blog> getBlogByStatus(BlogStatus blogStatus);


    /**
     * 统计所有点赞
     * @return
     */
    Integer getAllLike();


    /**
     * 统计所有阅读数
     * @return
     */
    Integer getAllReadNum();


    /**
     * 新增一篇博客
     * @param blog
     * @return
     */
    Integer insert(Blog blog);


    /**
     * 删除一篇博客
     * @param id
     * @return
     */
    Integer delete(Long id);


    /**
     * 更新一篇博客
     * @param blog
     * @return
     */
    Integer update(Blog blog);
}
