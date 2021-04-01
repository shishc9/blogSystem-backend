package icu.shishc.controller;

import icu.shishc.entity.User;
import icu.shishc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PackageName:icu.shishc.controller
 * @Date:2021/3/15, 15:56
 * @Auther:ShiShc
 */

@Slf4j
@RestController
@RequestMapping("/blogbackend/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getbyid")
    public User getById(@RequestParam("userId") Long userId) {
        User user = userService.getUserById(userId);
        return user;
    }

    @GetMapping("/getbyname")
    public User getByName(@RequestParam("username") String username) {
        User user = userService.getUserByName(username);
        return user;
    }



}
