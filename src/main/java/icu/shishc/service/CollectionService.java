package icu.shishc.service;


import icu.shishc.entity.Blog;
import icu.shishc.exception.CustomException;

import java.util.List;

public interface CollectionService {

    /**
     * 删除收藏该博客的记录
     * @param bid bid
     * @return ,
     */
    Integer deleteBlogCollection(Long bid);

    /**
     * 添加收藏
     * @param uid uid
     * @param bid bid
     * @return .
     */
    int addCollection(Long uid, Long bid) throws CustomException;

    /**
     * 取消收藏
     * @param uid uid
     * @param bid bid
     * @return .
     */
    int cancelCollection(Long uid, Long bid) throws CustomException;

    /**
     * 用户的收藏列表
     * @param uid uid
     * @return .
     */
    List<Blog> collectionList(Long uid);

    /**
     * 是否收藏
     * @param uid uid
     * @param bid bid
     * @return .
     */
    int collectionOrNot(Long uid, Long bid);
}
