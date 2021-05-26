package icu.shishc.service.serviceImpl;

import icu.shishc.entity.Blog;
import icu.shishc.exception.CustomException;
import icu.shishc.entity.Comment;
import icu.shishc.mapper.CommentMapper;
import icu.shishc.service.BlogService;
import icu.shishc.service.CommentService;
import icu.shishc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2021-5-12, 13:57
 * @author: ShiShc
 */

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    BlogService blogService;
    @Autowired
    UserService userService;

    private List<Comment> tempComments = new ArrayList<>();


    /**
     * 找出一篇博客的所有一级评论
     * @param bid 博客id
     * @return list
     * @throws CustomException CustomException
     */
    @Override
    public List<Comment> findCommentsByBlogId(Long bid) throws CustomException {
        if(!blogService.checkBid(bid)) {
            log.warn("【CommentService】findCommentsByBlogId::bad bid, bid = {}", bid);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        List<Comment> firstComments = commentMapper.findFirstComments(bid, Long.parseLong("0"));
        for(Comment comment : firstComments) {
            Long commentId = comment.getCommentId();
            String parentUsername = comment.getParentUsername();
            List<Comment> secondComments = commentMapper.findByParentIdNotNull(commentId);
            combineChildren(secondComments, parentUsername);
            comment.setReplyComments(tempComments);
            tempComments = new ArrayList<>();
        }
        return firstComments;
    }


    /**
     * 将二级评论和父评论拼接起来
     * @param childComments 二级评论
     * @param parentUsername 父评论者用户名
     */
    private void combineChildren(List<Comment> childComments, String parentUsername) {
        if(childComments.size() > 0) {
            for(Comment comment :childComments) {
                String tempParentUsername = comment.getParentUsername();
                comment.setParentUsername(parentUsername);
                tempComments.add(comment);
                Long secondCommentId = comment.getCommentId();
                recursively(secondCommentId, tempParentUsername);
            }
        }
    }


    /**
     * 将二级评论和所有子评论连接起来
     * @param secondCommentId 二级评论Id
     * @param parentUsername 二级评论者的用户名
     */
    private void recursively(Long secondCommentId, String parentUsername) {
        List<Comment> replyComments = commentMapper.findByReplyId(secondCommentId);
        if(replyComments.size() > 0) {
            for(Comment comment : replyComments) {
                String tempParentUsername = comment.getParentUsername();
                comment.setParentUsername(parentUsername);
                Long replyId = comment.getCommentId();
                tempComments.add(comment);
                recursively(replyId, tempParentUsername);
            }
        }
    }


    /**
     * 保存评论
     * @param comment 评论实体
     * @return int
     * @throws CustomException .
     */
    @Override
    public int saveComment(Comment comment) throws CustomException {
        if(!commentCheck(comment)) {
            log.warn("【CommentService】saveComment::bad comment entity");
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        log.info("【CommentService】saveComment::saveComment");
        return commentMapper.saveComment(comment.getBlogId(),
                comment.getUsername(),
                comment.getContent(),
                comment.getParentCommentId());
    }


    private int count = 0;


    @Override
    public int deleteComment(Long cid) {
        List<Long> toDeleteId = commentMapper.toDeleteComments(cid);
        count += commentMapper.deleteCommentById(cid);
        if(toDeleteId.size() > 0) {
            for(Long id : toDeleteId) {
                help(id);
                count += commentMapper.deleteCommentById(id);
            }
        }
        return count;
    }


    @Override
    public int deleteBlogComments(Long bid) {
        return commentMapper.deleteBlogComments(bid);
    }


    private void help(Long cid) {
        List<Long> toDeleteId = commentMapper.toDeleteComments(cid);
        if(toDeleteId.size() > 0) {
            for(Long id : toDeleteId) {
                help(id);
                count += commentMapper.deleteCommentById(id);
            }
        }
    }


    /**
     * 检查评论实体：
     * @param comment 评论实体
     * @return T / F
     * @throws CustomException .
     */
    private boolean commentCheck(Comment comment) throws CustomException {
        if(blogService.getBlogByBID(comment.getBlogId()) == null) {
            return false;
        }
        if(userService.getUserByName(comment.getUsername()) == null) {
            return false;
        }
        if("".equals(comment.getContent().trim())) {
            return false;
        }
        Long parentCommentId = comment.getParentCommentId();
        if(parentCommentId != 0 && commentMapper.findCommentById(parentCommentId) == null) {
            return false;
        }
        log.info("【CommentService】commentCheck::good entity");
        return true;
    }
}
