package icu.shishc.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @PackageName:icu.shishc.dto
 * @Date:2021/3/18, 14:03
 * @Auther:ShiShc
 */

@Data
public class LoginDTO implements Serializable {

    @NotBlank(message = "username can't be null")
    private String username;

    @NotBlank(message = "password can't be null")
    private String password;

}
