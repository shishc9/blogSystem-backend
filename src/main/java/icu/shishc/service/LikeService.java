package icu.shishc.service;

/**
 * 点赞服务
 * @author ShiShc
 */
public interface LikeService {


    /**
     * 用户为某一个博客点赞
     * @param bid 博客ID
     * @param userId 用户id
     * @return 1 0
     */
    Integer addLike(Long bid, Long userId);


    /**
     * 用户取消某一个博客的赞
     * @param bid 博客ID
     * @param userId 用户ID
     * @return 1 0
     */
    Integer cancelLike(Long bid, Long userId);

}
