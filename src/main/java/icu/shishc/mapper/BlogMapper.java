package icu.shishc.mapper;

import icu.shishc.entity.Blog;
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
     * 当前博客的上一篇博客
     * @param bid
     * @return
     */
    Blog getPrevious(@Param("bid") Long bid);


    /**
     * 当前博客的下一篇博客
     * @param bid
     * @return
     */
    Blog getNext(@Param("bid") Long bid);


    /**
     * 博客状态查找
     * @param status
     * @return
     */
    List<Blog> getBlogByStatus(@Param("status") int status);


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


    /**
     * 点赞
     * @param bid
     * @return
     */
    Integer addLike(@Param("bid") Long bid);


    /**
     * 取消点赞
     * @param bid
     * @return
     */
    Integer cancelLike(@Param("bid") Long bid);
}
