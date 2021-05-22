package icu.shishc.controller;

import icu.shishc.exception.CustomException;
import icu.shishc.dto.MyDTO;
import icu.shishc.dto.UserDTO;
import icu.shishc.dto.LoginDTO;
import icu.shishc.entity.User;
import icu.shishc.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @date:2021-4-15, 21:26
 * @author:ShiShc
 */

@RestController
@Slf4j
public class LoginController {

    /**
     * 登录接口
     */
    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public MyDTO login(@RequestBody LoginDTO loginDTO) throws CustomException {
        String username = loginDTO.getUsername();
        String pwd = loginDTO.getPwd();
        boolean flag = loginService.authLogin(username, pwd);
        if(flag) {
            log.info("【LoginController】authLogin, username = {}, pwd = {}, successfully!", username, pwd);
            return MyDTO.successDTO("LOGIN_SUCCESS");
        }
        log.info("【LoginController】authLogin, username = {}, pwd = {}, failed", username, pwd);
        return MyDTO.wrongDTO(HttpStatus.BAD_REQUEST, "LOGIN_FAILED");
    }


    @GetMapping("/logout")
    public MyDTO logout() throws CustomException{
        boolean flag = loginService.logout();
        if(flag) {
            log.info("【LoginController】Logout, successfully");
            return MyDTO.successDTO("LOGOUT_SUCCESS");
        }
        return MyDTO.wrongDTO(HttpStatus.BAD_REQUEST, "BAD_REQUEST");
    }


    @GetMapping("/noauth")
    public MyDTO noAuth() {
        return MyDTO.wrongDTO(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
    }


    /**
     * 注册用户
     * @param user user
     * @return MyDTO
     * @throws CustomException MyDTO
     */
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public MyDTO register(@RequestBody User user) throws CustomException{
        User user1 = loginService.register(user);
        log.info("【LoginController】register::register successfully! username = {}", user.getUsername().equals(user1.getUsername()) ?user.getUsername() : null);
        return MyDTO.successDTO(new UserDTO(user1));
    }
}
