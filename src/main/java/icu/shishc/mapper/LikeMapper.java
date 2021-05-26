package icu.shishc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @DESC: 点赞
 * @author Closer
 */
@Mapper
@Repository
public interface LikeMapper {


    /**
     * 点赞
     * @param bid 博客id
     * @param uid 用户id
     * @return 受影响的行数
     */
    int addLike(@Param("bid")Long bid, @Param("uid")Long uid);


    /**
     * 取消点赞
     * @param bid 博客id
     * @param uid 用户id
     * @return 受影响的行数
     */
    int cancelLike(@Param("bid")Long bid, @Param("uid")Long uid);


    /**
     * 删除用户的所有点赞 同时对应的博客点赞数 - 1
     * @param uid uid
     * @return 。
     */
    List<Long> getUserAllLike(@Param("uid") Long uid);


    /**
     * 统计博客的所有点赞 同时博主取消获赞量
     * @param bid bid
     * @return .
     */
    int getBlogAllLike(@Param("bid") Long bid);


    /**
     * 删除一个博客的所有点赞
     * @param bid bid
     * @return .
     */
    int deleteBlogAllLike(@Param("bid") Long bid);


}
