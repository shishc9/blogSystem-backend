package icu.shishc.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "Username can't be null")
//    @Length(min = 4, max = 32)
//    这里先加上非空限制， 至于长度限制让前端去检测吧(非空有可能也会扔给前端去做，先留着)
    private String username;

    @NotBlank(message = "password can't be null")
//    @Length(min = 8, max = 16)
    private String password;

    private boolean isMainPerson;
    private Integer age;
    private String gender;
    private String hobby;

    @Email(message = "Email format is incorrect")
    private String email;

    private Date gmtCreate;
    private Date gmtLastLogin;

}
