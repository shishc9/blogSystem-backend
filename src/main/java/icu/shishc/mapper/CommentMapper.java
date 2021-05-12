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
     * 保存评论
     * @param comment
     * @return
     */
    int saveComment(Comment comment);


    /**
     * 查询父级评论
     * @param parentId
     * @return
     */
    List<Comment> findByParentIdNull(@Param("parentId") Long parentId);


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

}
