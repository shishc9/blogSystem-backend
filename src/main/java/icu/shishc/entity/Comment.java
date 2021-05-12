package icu.shishc.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

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
    private String email;
    private String content;
    private Date createTime;
    private Long parentCommentId;
}
