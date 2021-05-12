package icu.shishc.service.serviceImpl;

import icu.shishc.entity.Comment;
import icu.shishc.mapper.CommentMapper;
import icu.shishc.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    private List<Comment> tempRelies = new ArrayList<>();

    @Override
    public List<Comment> findCommentsByBlogId(Long bid) {
        return null;
    }


    @Override
    public List<Comment> listComment() {
        List<Comment> comments = commentMapper.findByParentIdNull(Long.parseLong("0"));
        for(Comment comment : comments) {
            Long id = comment.getCommentId();
            String parentUsername = comment.getParentUsername();
            List<Comment> childComments = commentMapper.findByParentIdNotNull(id);
            combineChildren(childComments, parentUsername);
            comment.setReplyComments(tempRelies);
            tempRelies = new ArrayList<>();
        }
        return comments;
    }


    /**
     * 查询出子评论
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


    @Override
    public int saveComment(Comment comment) {
        log.info("【Service】CommentService::saveComment");
        return commentMapper.saveComment(comment);
    }
}
