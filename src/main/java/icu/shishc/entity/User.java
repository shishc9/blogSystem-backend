package icu.shishc.entity;

import icu.shishc.enumeration.UserIdentity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @PackageName:icu.shishc.entity
 * @Date:2021/3/14, 22:20
 * @Auther:ShiShc
 */

@Data
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private String username;
    private String password;
    private UserIdentity userIdentity;
    private Integer age;
    private String gender;
    private String hobby;
    private String email;
    private Date gmtCreate;
    private Date gmtLastLogin;

}
