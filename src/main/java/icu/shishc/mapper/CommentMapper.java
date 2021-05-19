package icu.shishc.mapper;

import icu.shishc.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
     * @param bid 所属博客id
     * @param username 评论者用户名
     * @param content 评论内容
     * @param pid 父评论id
     * @return int
     */
    int saveComment(@Param("bid") Long bid,
                    @Param("username") String username,
                    @Param("content") String content,
                    @Param("parentCommentId") Long pid
    );


    /**
     * 查询某博客父级评论
     * @param bid 博客id
     * @param parentId 父评论id
     * @return
     */
    List<Comment> findFirstComments(@Param("bid")Long bid, @Param("parentId") Long parentId);


    /**
     * 查询父级留言
     * @param parentId 父评论id
     * @return list
     */
    List<Comment> findMessageByParentIdNull(@Param("parentId") Long parentId);


    /**
     * 查询一级回复
     * @param id 评论id
     * @return list
     */
    List<Comment> findByParentIdNotNull(@Param("id") Long id);


    /**
     * 查询二级以及所有子集回复
     * @param childId 子评论id
     * @return list
     */
    List<Comment> findByReplyId(@Param("childId") Long childId);


    /**
     * 通过id查找评论
     * @param commentId 评论id
     * @return 评论实体
     */
    Comment findCommentById(@Param("commentId")Long commentId);
}
