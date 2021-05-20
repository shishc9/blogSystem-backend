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
     * @param userId 用户id
     * @param title 标题
     * @param content 内容
     * @param status 状态
     * @return
     */
    Integer insert(@Param("userId") Long userId,
                   @Param("title") String title,
                   @Param("content") String content,
                   @Param("status") int status
    );


    /**
     * 删除
     * @param bid
     * @return
     */
    Integer delete(@Param("bid") Long bid);


    /**
     * 博客更新
     * @param title 博客标题
     * @param content 内容
     * @param status 状态
     * @param blogId 博客id
     * @return 1 0
     */
    Integer update(@Param("title") String title,
                   @Param("content") String content,
                   @Param("status") int status,
                   @Param("blog_id") Long blogId
    );



    /**
     * 博客广场
     * @return list
     */
    List<Blog> getBlogSquare();


    /**
     * 某一具体用户的博客
     * @param userId 用户ID
     * @return list
     */
    List<Blog> getBlogByUserId(@Param("userId") Long userId);


    /**
     * 标题查找
     * @param title 博客标题
     * @return 博客实体
     */
    Blog getBlogByTitle(@Param("title") String title);


    /**
     * 博客id查找
     * @param bid 博客id
     * @return 博客实体
     */
    Blog getBlogByBID(@Param("bid") Long bid);


    /**
     * 获取前一篇博客
     * @param bid 博客ID
     * @param userId 用户ID
     * @return 博客实体
     */
    Blog getPrevious(@Param("bid") Long bid,
                     @Param("userId") Long userId);


    /**
     * 获取下一篇博客
     * @param bid 博客id
     * @param userId 用户ID
     * @return 博客实体
     */
    Blog getNext(@Param("bid") Long bid,
                 @Param("userId") Long userId);


    /**
     * 用户的状态博客
     * @param status 博客状态
     * @param userId 用户id
     * @return list
     */
    List<Blog> getBlogByStatus(@Param("status") int status,
                               @Param("userId") Long userId);


    /**
     * 点赞
     * @param bid 博客id
     * @return 受影响的行
     */
    int addALike(@Param("bid")Long bid);


    /**
     * 取消点赞
     * @param bid 博客id
     * @return 受影响的行
     */
    int cancelALike(@Param("bid")Long bid);

}
