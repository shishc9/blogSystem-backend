package icu.shishc.service.serviceImpl;

import icu.shishc.entity.Comment;
import icu.shishc.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @date: 2021-5-12, 13:57
 * @author: ShiShc
 */


@Slf4j
@Service
public class CommentServiceImpl implements CommentService {


    @Override
    public List<Comment> findCommentsByBlogId(Long bid) {

        return null;
    }


    @Override
    public int saveComment(Comment comment) {

        return 0;
    }
}
