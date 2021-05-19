package icu.shishc.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @date: 2021-5-12, 13:47
 * @author: ShiShc
 */


@Data
@ApiModel(value = "评论")
public class Comment {
    private Long commentId;
    private Long blogId;
    private String username;
    private String content;
    private Date gmtCreate;
    private Long parentCommentId;

    // reply comment
    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;
    private String parentUsername;
}
