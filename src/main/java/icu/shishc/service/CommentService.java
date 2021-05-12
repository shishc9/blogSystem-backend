package icu.shishc.service;

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
    List<Comment> findCommentsByBlogId(Long bid);


    List<Comment> listComment();

    /**
     * 保存评论
     * @param comment
     * @return
     */
    int saveComment(Comment comment);

}
