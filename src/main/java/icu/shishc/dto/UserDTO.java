package icu.shishc.dto;

import icu.shishc.entity.User;
import icu.shishc.enumeration.UserIdentity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @date: 2021-5-9, 16:29
 * @author: ShiShc
 */

@Data
@ApiModel(value = "User数据")
public class UserDTO {

    String username;
    UserIdentity userIdentity;
    Integer age;
    String gender;
    String hobby;
    String email;


    public UserDTO(User user) {
        this.username = user.getUsername();
        this.userIdentity = user.getUserIdentity();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.hobby = user.getHobby();
        this.email = user.getEmail();
    }
}