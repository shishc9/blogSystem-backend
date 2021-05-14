package icu.shishc.mapper;


import icu.shishc.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ShiShc
 */
@Mapper
@Repository
public interface CommentMapper {


    /**
     *  保存评论
     * @param bid
     * @param username
     * @param email
     * @param content
     * @param pid
     * @return
     */
    int saveComment(@Param("bid") Long bid,
                    @Param("username") String username,
                    @Param("email") String email,
                    @Param("content") String content,
                    @Param("parentCommentId") Long pid
    );


    /**
     * 查询父级评论
     * @param parentId
     * @return
     */
    List<Comment> findCommentByParentIdNull(@Param("parentId") Long parentId);


    /**
     * 查询父级留言
     * @param parentId
     * @return
     */
    List<Comment> findMessageByParentIdNull(@Param("parentId") Long parentId);


    /**
     * 查询一级回复
     * @param id
     * @return
     */
    List<Comment> findByParentIdNotNull(@Param("id") Long id);


    /**
     * 查询二级以及所有子集回复
     * @param childId
     * @return
     */
    List<Comment> findByReplyId(@Param("childId") Long childId);


    /**
     * 通过id查找评论
     * @param commentId
     * @return
     */
    Comment findCommentById(@Param("commentId")Long commentId);
}
