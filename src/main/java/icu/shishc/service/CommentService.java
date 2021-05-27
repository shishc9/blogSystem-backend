package icu.shishc.service;

import icu.shishc.exception.CustomException;
import icu.shishc.entity.Comment;

import java.util.List;

/**
 * 评论Service
 * @author ShiShc
 */
public interface CommentService {

    /**
     * 返回某一博客的评论
     * @param bid 博客id
     * @return list
     * @throws CustomException .
     */
    List<Comment> findCommentsByBlogId(Long bid) throws CustomException;


    /**
     * 发布评论
     * @param comment 评论实体
     * @return 0 1
     * @throws CustomException .
     */
    int saveComment(Comment comment) throws CustomException;


    /**
     * 删除评论和所有回复
     * @param cid 待删除的评论id
     * @return 受影响行数
     */
    int deleteComment(Long cid) throws CustomException;


    /**
     * 删除一篇博客的所有评论
     * @param bid bid
     * @return .
     */
    int deleteBlogComments(Long bid);

}
