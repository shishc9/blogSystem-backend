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


    @GetMapping("/{bid}")
    public MyDTO getCommentByBid(@PathVariable("bid") Long bid)
            throws CustomException {
        return MyDTO.successDTO(commentService.findCommentsByBlogId(bid));
    }


    @PostMapping("/comment")
    public MyDTO saveComment(@RequestBody Comment comment)
            throws CustomException {
        log.info("【CommentController】saveComment");
        return MyDTO.successDTO(commentService.saveComment(comment));
    }


    @DeleteMapping("/comment")
    public MyDTO deleteComment(@RequestParam("cid") Long cid) {
        log.info("【CommentController】deleteComment, delete cid = {}", cid);
        return MyDTO.successDTO(commentService.deleteComment(cid));
    }
}
