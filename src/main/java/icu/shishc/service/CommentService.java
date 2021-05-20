package icu.shishc.service;

import icu.shishc.Exception.CustomException;
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

}
