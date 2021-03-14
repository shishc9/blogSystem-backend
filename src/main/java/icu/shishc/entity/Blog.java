package icu.shishc.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
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

    @NotNull
    private String username;

    @NotNull(message = "blog's title can't be null")
    private String title;

    @NotNull
    private String content;

    /**
     * 状态0：公开， 状态1：私有
     */
    private boolean status;

    private Integer readNum;
    private Integer likeNum;

    private Date gmtCreate;
    private Date gmtModified;
}
