package icu.shishc.controller;

import icu.shishc.Exception.CustomException;
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


//    @GetMapping("/get/message")
//    public MyDTO getLeaveMessage()
//            throws CustomException {
//        return MyDTO.successDTO(commentService.listMessage());
//    }


    @GetMapping("/get/by-id")
    public MyDTO getCommentByBid(@RequestParam("bid") Long bid)
            throws CustomException {
        return MyDTO.successDTO(commentService.findCommentsByBlogId(bid));
    }


//    @PostMapping("/add")
//    public MyDTO saveComment(@RequestBody Comment comment)
//            throws CustomException {
//        return MyDTO.successDTO(commentService.saveComment(comment));
//    }
}
