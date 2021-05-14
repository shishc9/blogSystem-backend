package icu.shishc.controller;

import icu.shishc.dto.MyDTO;
import icu.shishc.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2021-5-12, 16:34
 * @author: ShiShc
 */


@Slf4j
@RestController("/comment")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {this.commentService = commentService;}


    @GetMapping("/message")
    public MyDTO commentTest() {
        return MyDTO.successDTO(commentService.listComment());
    }
}
