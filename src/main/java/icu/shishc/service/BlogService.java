package icu.shishc.service;

import icu.shishc.entity.Blog;
import icu.shishc.enumeration.BlogStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService extends BaseService<Blog> {


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
}
