package icu.shishc.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @PackageName:icu.shishc.dto
 * @Date:2021/3/18, 14:03
 * @Auther:ShiShc
 */

@Data
public class LoginDTO implements Serializable {
    private String username;
    private String password;

}
