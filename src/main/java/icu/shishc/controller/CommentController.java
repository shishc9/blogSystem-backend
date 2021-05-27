package icu.shishc.controller;

import icu.shishc.exception.CustomException;
import icu.shishc.dto.MyDTO;
import icu.shishc.entity.Comment;
import icu.shishc.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @date: 2021-5-12, 16:34
 * @author: ShiShc
 */


@Slf4j
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {this.commentService = commentService;}

    /**
     * 获取一篇博客下面的评论
     * @param bid
     * @return
     * @throws CustomException
     */
    @GetMapping("/b")
    public MyDTO getCommentByBid(@RequestParam("bid") Long bid)
            throws CustomException {
        return MyDTO.successDTO(commentService.findCommentsByBlogId(bid));
    }

    /**
     * 发布评论
     * @param comment
     * @return
     * @throws CustomException
     */
    @PostMapping("/comment")
    public MyDTO saveComment(@RequestBody Comment comment)
            throws CustomException {
        log.info("【CommentController】saveComment");
        return MyDTO.successDTO(commentService.saveComment(comment));
    }


    /**
     * 删除评论
     * @param cid
     * @return
     */
    @DeleteMapping("/comment")
    public MyDTO deleteComment(@RequestParam("cid") Long cid) {
        log.info("【CommentController】deleteComment, delete cid = {}", cid);
        return MyDTO.successDTO(commentService.deleteComment(cid));
    }
}
