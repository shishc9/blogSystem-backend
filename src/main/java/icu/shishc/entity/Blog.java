package icu.shishc.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @PackageName:icu.shishc.entity
 * @Date:2021/3/14, 22:39
 * @Auther:ShiShc
 */

@Data
@ApiModel(value = "Blog对象", description = "")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 博客id
     */
    private Long blogId;
    /**
     * 发布者id
     */
    private Long userId;
    /**
     * 标题
     */
    private String title;
    /**
     * 正文
     */
    private String content;
    /**
     * 阅读量
     */
    private Integer readNum;
    /**
     * 点赞数
     */
    private Integer likeNum;
    /**
     * 评论数
     */
    private Integer commentNum;
    /**
     * 收藏数
     */
    private Integer collectionNum;

    private Date gmtCreate;
    private Date gmtModified;
}
