package icu.shishc.CommentService;

import icu.shishc.exception.CustomException;
import icu.shishc.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @date: 2021-5-20, 11:00
 * @author: ShiShc
 */

@SpringBootTest
public class CommentTest {

    @Autowired
    CommentService commentService;


    @Test
    void test() throws CustomException {
        System.out.println(commentService.deleteComment(Long.parseLong("50014")));
    }
}
