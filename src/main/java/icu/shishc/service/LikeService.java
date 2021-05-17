package icu.shishc.service;

import icu.shishc.Exception.CustomException;

/**
 * 点赞服务
 */
public interface LikeService {


    /**
     * 用户为某一个博客点赞
     * @param bid 博客ID
     * @param userId 用户id
     * @return 1 0
     * @throws CustomException .
     */
    Integer addLike(Long bid, Long userId) throws CustomException;


    /**
     * 用户取消某一个博客的赞
     * @param bid 博客ID
     * @param userId 用户ID
     * @return 1 0
     * @throws CustomException .
     */
    Integer cancelLike(Long bid, Long userId) throws CustomException;

}
