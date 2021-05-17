package icu.shishc.entity;

import icu.shishc.enumeration.BlogStatus;
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

    private Long blogId;
    private Long userId;
    private String title;
    private String content;

    /**
     * 状态0：公开， 状态1：私有
     */
    private BlogStatus status;
    private Integer readNum;
    private Integer likeNum;

    private Date gmtCreate;
    private Date gmtModified;
}
