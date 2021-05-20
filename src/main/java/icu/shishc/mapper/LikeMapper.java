package icu.shishc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


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

}
