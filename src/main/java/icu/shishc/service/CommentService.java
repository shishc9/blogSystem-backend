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
     * return blog's comments;
     * @param bid
     * @return
     */
    List<Comment> findCommentsByBlogId(Long bid) throws CustomException;


    /**
     * 显示博客留言
     * @return
     */
    List<Comment> listMessage() throws CustomException;

    /**
     * 保存评论
     * @param comment
     * @return
     */
    int saveComment(Comment comment) throws CustomException;


    /**
     * 通过id判断评论是否存在
     * @param commentId
     * @return
     */
    Comment getCommentById(Long commentId);
}
