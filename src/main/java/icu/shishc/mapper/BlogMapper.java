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
     * @return int
     */
    Integer insert(@Param("userId") Long userId,
                   @Param("title") String title,
                   @Param("content") String content
    );


    /**
     * 删除
     * @param bid
     * @return
     */
    Integer delete(@Param("bid") Long bid);


    /**
     * 删除某个用户的所有博客
     * @param uid 用户id
     * @return int
     */
    Integer deleteByUser(@Param("uid") Long uid);


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
     * list bid 批量查询
     * @param list list
     * @return list
     */
    List<Blog> getBlogByList(@Param("list") List<Long> list);


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


    /**
     * 增加阅读量
     * @param bid bid
     * @return .
     */
    int addRead(@Param("bid") Long bid);


    /**
     * 评论数
     * @param bid bid .
     * @return .
     */
    int addComment(@Param("bid") Long bid);


    /**
     * 减少评论数
     * @param bid bid
     * @param count count
     * @return .
     */
    int cancelComment(@Param("bid")Long bid, @Param("count")Integer count);


    /**
     * 收藏
     * @param bid bid
     * @return .
     */
    int addCollection(@Param("bid") Long bid);

    /**
     * 取消收藏
     * @param bid bid
     * @return .
     */
    int cancelCollection(@Param("bid")Long bid);

}
