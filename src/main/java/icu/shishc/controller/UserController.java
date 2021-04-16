package icu.shishc.controller;

import icu.shishc.Exception.CustomException;
import icu.shishc.dto.MyDTO;
import icu.shishc.entity.User;
import icu.shishc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @PackageName:icu.shishc.controller
 * @Date:2021/3/15, 15:56
 * @Auther:ShiShc
 */

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/get/by-id")
    public MyDTO getUserById(@RequestParam("userId") Long userId) throws CustomException {
        User user = userService.getUserById(userId);
        log.info("【Controller】UserController::getById: return user, uID = {}", userId);
        return MyDTO.successDTO(user);
    }


    @GetMapping("/get/by-username")
    public MyDTO getUserByName(@RequestParam("username") String username) throws CustomException {
        username = username.trim();
        if(username.equals("")) {
            log.warn("【Controller】UserController::getUserByName: bad username");
            return MyDTO.wrongDTO(HttpStatus.BAD_REQUEST, "username can't be null");
        }
        User user = userService.getUserByName(username);
        log.info("【Controller】UserController::getUserByName: return user, uID = {}", user == null ? 0 : user.getUserId());
        return MyDTO.successDTO(user);
    }


    @PostMapping("/insert")
    public MyDTO insert(@RequestBody User user) throws CustomException {
        if(!userService.userCheck(user)) {
            log.warn("【Controller】UserController::insert: bad user entity");
            return MyDTO.wrongDTO(HttpStatus.BAD_REQUEST, "bad user entity");
        }
        User user1 = userService.insert(user);
        log.info("【Controller】UserController::insert: insert successfully! username = {}", user.getUsername() == user1.getUsername()?user.getUsername() : null);
        return MyDTO.successDTO(user1);
    }


    @PostMapping("/update")
    public MyDTO update(@RequestBody User user) throws CustomException {
        if(!userService.userCheck(user)) {
            log.warn("【Controller】UserController::update: bad user entity");
            return MyDTO.wrongDTO(HttpStatus.BAD_REQUEST, "bad user entity");
        }
        log.info("【Controller】UserController::update: before update, uid = {}", user.getUserId());
        User user1 = userService.update(user);
        log.info("【Controller】UserController::update: after update, uid = {}, username = {}", user1.getUserId(), user1.getUsername());
        return MyDTO.successDTO(user1);
    }


    @GetMapping("/delete")
    public MyDTO delete(@RequestParam("uid") Long uid) throws CustomException {
        Integer status = userService.delete(uid);
        log.info("【Controller】UserController::delete: delete uid = {}", uid);
        return MyDTO.successDTO(status);
    }
}
