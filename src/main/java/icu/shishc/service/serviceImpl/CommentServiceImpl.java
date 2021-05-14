package icu.shishc.service.serviceImpl;

import icu.shishc.Exception.CustomException;
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
    UserService userService;
    @Autowired
    BlogService blogService;

    private List<Comment> tempRelies = new ArrayList<>();

    /**
     * 通过博客id列出所有评论
     * @param bid
     * @return
     */
    @Override
    public List<Comment> findCommentsByBlogId(Long bid) throws CustomException {
        if(bid <= 0) {
            log.warn("【Service】CommentService::findCommentsByBlogId:Illegal param, bid = {}", bid);
            throw new CustomException(HttpStatus.BAD_REQUEST, "bad param");
        }
        // 查找评论
        List<Comment> comments = commentMapper.findCommentByParentIdNull((long) 0);
        for(Comment comment : comments) {
            Long id = comment.getCommentId();
            String parentUsername = comment.getParentUsername();
            List<Comment> childComments = commentMapper.findByParentIdNotNull(id);
            combineChildren(childComments, parentUsername);
            comment.setReplyComments(tempRelies);
            // 这里找到了一个评论/留言极其所有子属性，讲tempRelies清零
            tempRelies = new ArrayList<>();
        }
        log.info("【Service】CommentService::findComentsByBlogId::return comments");
        return comments;
    }


    /**
     * 列出所有评论
     * ps: 把这个当做留言来做，所以comment.blogId = 0; 如果是非零的话就说明是博客的评论，因为博客的id是从1000开始自增的。
     * @return
     */
    @Override
    public List<Comment> listMessage() {
        // 查找留言
        List<Comment> comments = commentMapper.findMessageByParentIdNull((long) 0);
        for(Comment comment : comments) {
            Long id = comment.getCommentId();
            String parentUsername = comment.getParentUsername();
            List<Comment> childComments = commentMapper.findByParentIdNotNull(id);
            combineChildren(childComments, parentUsername);
            comment.setReplyComments(tempRelies);
            // 这里找到了一个评论/留言极其所有子属性，讲tempRelies清零
            tempRelies = new ArrayList<>();
        }
        log.info("【Service】CommentService::listMessage, return leave message");
        return comments;
    }


    /**
     * 查询出子评论/子留言
     * @param childComments
     * @param parentUsername1
     */
    private void combineChildren(List<Comment> childComments, String parentUsername1) {
        if(childComments.size() > 0) {
            for(Comment childComment : childComments) {
                String parentUsername = childComment.getParentUsername();
                childComment.setParentUsername(parentUsername1);
                tempRelies.add(childComment);
                Long childId = childComment.getCommentId();
                recursively(childId, parentUsername);
            }
        }
    }


    /**
     * 迭代找出子集回复
     * @param childId
     * @param parentUsername1
     */
    private void recursively(Long childId, String parentUsername1) {
        List<Comment> replyComments = commentMapper.findByReplyId(childId);
        if(replyComments.size() > 0) {
            for(Comment comment : replyComments) {
                String parentUsername = comment.getParentUsername();
                comment.setParentUsername(parentUsername1);
                Long replyId = comment.getCommentId();
                tempRelies.add(comment);
                recursively(replyId, parentUsername);
            }
        }

    }


    /**
     * 保存评论
     * @param comment
     * @return
     */
    @Override
    public int saveComment(Comment comment) throws CustomException{
        if(!commentCheck(comment)) {
            log.warn("【Service】CommentService::comment entity check, bad entity");
            throw new CustomException(HttpStatus.BAD_REQUEST, "bad comment entity");
        }
        log.info("【Service】CommentService::saveComment");
        return commentMapper.saveComment(comment.getBlogId(),
                comment.getUsername(), comment.getEmail(),
                comment.getContent(), comment.getParentCommentId()
        );
    }

    @Override
    public Comment getCommentById(Long commentId) {
        return commentMapper.findCommentById(commentId);
    }


    /**
     * 评论实体检查
     * 1. 对blogId合法性进行检查
     * 2. 如果不是0，则检查该博客是否存在
     * 3. 对username进行检查
     * 4. 邮箱合法性检验
     * 5. 内容不为空检查
     * 6. 对父评论id进行检查
     * @param comment
     * @return
     */
    private boolean commentCheck(Comment comment) throws CustomException {
        Long blogId = comment.getBlogId();
        String username = comment.getUsername();
        String email = comment.getEmail();
        String content = comment.getContent();
        Long parentCommentId = comment.getParentCommentId();
        if(blogId < 0 || "".equals(username.trim()) || !userService.regexMatch(email)
            || "".equals(content.trim()) || parentCommentId < 0) {
            log.warn("【Service】CommentService::commentCheck, bad param");
            return false;
        }
        if(blogId != 0 && blogService.getBlogByBID(blogId) == null) {
            log.warn("【Service】CommentService::commentCheck, bad bid");
            return false;
        }
        if(userService.getUserByName(username) == null) {
            log.warn("【Service】CommentService::commentCheck, bad username");
            return false;
        }
        if(parentCommentId != 0 && getCommentById(parentCommentId) == null) {
            log.warn("【Service】CommentService::commentCheck, bad parentCommentId");
            return false;
        }
        log.info("【Service】CommentService::commentCheck, good entity");
        return true;
    }
}
