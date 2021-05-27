package icu.shishc.service;

import icu.shishc.exception.CustomException;

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
    Integer addLike(Long bid, Long userId) throws CustomException;


    /**
     * 用户取消某一个博客的赞
     * @param bid 博客ID
     * @param userId 用户ID
     * @return 1 0
     */
    Integer cancelLike(Long bid, Long userId) throws CustomException;


    /**
     * 删除博客的所有点赞
     * @param bid bid
     * @return .
     */
    Integer deleteBlogLikes(Long bid);


    /**
     * 是否点过赞了
     * @param bid
     * @param uid
     * @return
     */
    Integer likeOrNot(Long bid, Long uid);
}
