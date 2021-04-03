package icu.shishc.controller;

import icu.shishc.Exception.CustomException;
import icu.shishc.dto.MyDTO;
import icu.shishc.entity.User;
import icu.shishc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/get-by-id")
    public MyDTO getUserById(@Param("userId") Long userId) throws CustomException {
        User user = userService.getUserById(userId);
        log.info("【Controller】UserController::getById: return user, uID = {}", userId);
        return MyDTO.successDTO(user);
    }


    @GetMapping("/get-by-username")
    public MyDTO getUserByName(@Param("username") String username) throws CustomException {
        User user = userService.getUserByName(username);
        log.info("【Controller】UserController::getUserByName: return user, uID = {}", user.getUserId());
        return MyDTO.successDTO(user);
    }


    @PostMapping("/insert")
    public MyDTO insert(@Param("user") User user) throws CustomException {
        User user1 = userService.insert(user);
        log.info("【Controller】UserController::insert: insert successfully! username = {}", user.getUsername() == user1.getUsername()?user.getUsername() : null);
        return MyDTO.successDTO(user1);
    }


    @PostMapping("/update")
    public MyDTO update(@Param("user") User user) throws CustomException {
        log.info("【Controller】UserController::update: before update, uid = {}", user.getUserId());
        User user1 = userService.update(user);
        log.info("【Controller】UserController::update: after update, uid = {}, username = {}", user1.getUserId(), user1.getUsername());
        return MyDTO.successDTO(user1);
    }


    @GetMapping("/delete")
    public MyDTO delete(@Param("uid") Long uid) throws CustomException {
        Integer status = userService.delete(uid);
        log.info("【Controller】UserController::delete: delete uid = {}", uid);
        return MyDTO.successDTO(status);
    }
}
