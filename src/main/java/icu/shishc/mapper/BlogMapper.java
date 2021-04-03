package icu.shishc.mapper;

import icu.shishc.entity.Blog;
import icu.shishc.enumeration.BlogStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {

    /**
     * 新增博客
     * @param username
     * @param title
     * @param content
     * @param status
     * @return
     */
    Integer insert(@Param("username") String username,
                   @Param("title") String title,
                   @Param("content") String content,
                   @Param("status") int status
    );


    /**
     * 删除
     * @param bid
     * @return
     */
    Integer delete(@Param("id") Long bid);


    /**
     * 更新博客
     * @param title
     * @param content
     * @param status
     * @return
     */
    Integer update(@Param("title") String title,
                   @Param("content") String content,
                   @Param("status") int status,
                   @Param("blog_id") Long blogId
    );


    /**
     * 获取所有博客
     * @return
     */
    List<Blog> getAllBlog();


    /**
     * 标题查找
     * @param title
     * @return
     */
    Blog getBlogByTitle(@Param("title") String title);


    /**
     * 博客id查找
     * @param bid
     * @return
     */
    Blog getBlogByBID(@Param("bid") Long bid);


    /**
     * 博客状态查找
     * @param blogStatus
     * @return
     */
    List<Blog> getBlogByStatus(@Param("blogStatus") int blogStatus);


    /**
     * 获取所有点赞
     * @return
     */
    Integer getAllLike();


    /**
     * 所有阅读量
     * @return
     */
    Integer getAllReadNum();
}
