package icu.shishc.entity;

import icu.shishc.enumeration.UserIdentity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author ShiShc
 * @PackageName:icu.shishc.entity
 * @Date:2021/3/14, 22:20
 */

@Data
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户邮箱
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户身份 0：博主 1：管理员
     */
    private UserIdentity userIdentity;
    /**
     * 用户个人网址
     */
    private String userSite;
    /**
     * 博客发布数量
     */
    private Integer postCount;
    /**
     * 点赞数量
     */
    private Integer likeCount;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 他的关注
     */
    private Integer following;
    /**
     * 关注他的
     */
    private Integer followed;

    private Date gmtCreate;
    private Date gmtLastLogin;
}
