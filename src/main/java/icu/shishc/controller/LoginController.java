package icu.shishc.controller;

import icu.shishc.Exception.CustomException;
import icu.shishc.dto.MyDTO;
import icu.shishc.entity.User;
import icu.shishc.service.LoginService;
import icu.shishc.service.UserService;
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
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public MyDTO authLogin(@RequestParam String username, @RequestParam String pwd) throws CustomException {
        boolean flag = loginService.authLogin(username, pwd);
        if(flag) {
            log.info("【Controller】LoginController::authLogin, username = {}, pwd = {}, successfully!", username, pwd);
            return MyDTO.successDTO("Login success");
        }
        log.info("【Controller】LoginController::authLogin, username = {}, pwd = {}, failed", username, pwd);
        return MyDTO.wrongDTO(HttpStatus.BAD_REQUEST, "Login failed!");
    }

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    @PostMapping("/logout")
    public MyDTO logout() throws CustomException{
        boolean flag = loginService.logout();
        if(flag) {
            log.info("【Controller】LoginController::Logout, successfully");
            return MyDTO.successDTO("Logout success");
        }
        return MyDTO.wrongDTO(HttpStatus.BAD_REQUEST, "Logout failed!");
    }

    @GetMapping("/noAuth")
    public MyDTO noAuth() {
        return MyDTO.wrongDTO(HttpStatus.UNAUTHORIZED, "无访问权限...");
    }


    @PostMapping("/register")
    public MyDTO register(@RequestBody User user) throws CustomException{
        if(!userService.userCheck(user)) {
            log.warn("【Controller】LoginController::register: bad user entity");
            return MyDTO.wrongDTO(HttpStatus.BAD_REQUEST, "bad user entity");
        }
        User user1 = loginService.register(user);
        log.info("【Controller】LoginController::register: register successfully! username = {}", user.getUsername() == user1.getUsername()?user.getUsername() : null);
        return MyDTO.successDTO(user1);
    }

//    @GetMapping("/error")
//    public MyDTO wrongPage() {
//        return MyDTO.wrongDTO(HttpStatus.NOT_FOUND, "Page not found");
//    }
}
