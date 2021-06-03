package icu.shishc.dto;

import lombok.Data;

@Data
public class PasswordDTO {
    private Long userId;
    private String oldPassword;
    private String newPassword;
}
