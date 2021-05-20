package icu.shishc.controller;

import icu.shishc.Exception.CustomException;
import icu.shishc.dto.MyDTO;
import icu.shishc.dto.UserDTO;
import icu.shishc.entity.User;
import icu.shishc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author ShiShc
 * @PackageName:icu.shishc.controller
 * @Date:2021/3/15, 15:56
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * 用户接口
     */
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return UserDTO
     * @throws CustomException .
     */
    @GetMapping("/{username}")
    public MyDTO getUserByName(@PathVariable("username") String username) throws CustomException {
        User user = userService.getUserByName(username);
        log.info("【UserController】getUserByName:: return user, uID = {}", user == null ? 0 : user.getUserId());
        return user == null ? MyDTO.successDTO(null) : MyDTO.successDTO(new UserDTO(user));
    }


    /**
     * 更新用户信息， 等级开放 -> Blogger + User
     * @param user user实体
     * @return MyDTO
     * @throws CustomException .
     */
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public MyDTO update(@RequestBody User user) throws CustomException {
        log.info("【UserController】update:: before update, user = {}", user.toString());
        User user1 = userService.update(user);
        log.info("【UserController】update:: after update, user = {}", user1.toString());
        return MyDTO.successDTO(new UserDTO(user1));
    }


    /**
     * 删除/注销用户, 等级开放 -> blogger + user
     * @param uid 用户id
     * @return MyDTO
     * @throws CustomException .
     */
    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public MyDTO delete(@RequestParam("uid") String uid) throws CustomException {
        long param = Long.parseLong(uid);
        Integer status = userService.delete(param);
        log.info("【UserController】delete:: delete uid = {}", uid);
        return MyDTO.successDTO(status);
    }
}
