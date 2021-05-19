package icu.shishc.service;

import icu.shishc.Exception.CustomException;
import icu.shishc.entity.Comment;

import java.util.List;

/**
 * 评论Service
 * @author ShiShc
 */
public interface CommentService {

    //    private List<Comment> tempRelies = new ArrayList<>();
//
//    /**
//     * 通过博客id列出所有评论
//     * @param bid
//     * @return
//     */
//    @Override
//    public List<Comment> findCommentsByBlogId(Long bid) throws CustomException {
//        if(bid <= 0) {
//            log.warn("【Service】CommentService::findCommentsByBlogId:Illegal param, bid = {}", bid);
//            throw new CustomException(HttpStatus.BAD_REQUEST, "bad param");
//        }
//        // 查找评论
//        List<Comment> comments = commentMapper.findCommentByParentIdNull((long) 0);
//        for(Comment comment : comments) {
//            Long id = comment.getCommentId();
//            String parentUsername = comment.getParentUsername();
//            List<Comment> childComments = commentMapper.findByParentIdNotNull(id);
//            combineChildren(childComments, parentUsername);
//            comment.setReplyComments(tempRelies);
//            // 这里找到了一个评论/留言极其所有子属性，讲tempRelies清零
//            tempRelies = new ArrayList<>();
//        }
//        log.info("【Service】CommentService::findComentsByBlogId::return comments");
//        return comments;
//    }
    //    /**
//     * 查询出子评论/子留言
//     * @param childComments
//     * @param parentUsername1
//     */
//    private void combineChildren(List<Comment> childComments, String parentUsername1) {
//        if(childComments.size() > 0) {
//            for(Comment childComment : childComments) {
//                String parentUsername = childComment.getParentUsername();
//                childComment.setParentUsername(parentUsername1);
//                tempRelies.add(childComment);
//                Long childId = childComment.getCommentId();
//                recursively(childId, parentUsername);
//            }
//        }
//    }
//
//
//    /**
//     * 迭代找出子集回复
//     * @param childId
//     * @param parentUsername1
//     */
//    private void recursively(Long childId, String parentUsername1) {
//        List<Comment> replyComments = commentMapper.findByReplyId(childId);
//        if(replyComments.size() > 0) {
//            for(Comment comment : replyComments) {
//                String parentUsername = comment.getParentUsername();
//                comment.setParentUsername(parentUsername1);
//                Long replyId = comment.getCommentId();
//                tempRelies.add(comment);
//                recursively(replyId, parentUsername);
//            }
//        }
//
//    }


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
     * 通过id查找评论是否存在
     * @param commentId 评论id
     * @return 评论实体
     */
    Comment getCommentById(Long commentId);

}
