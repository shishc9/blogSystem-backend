package icu.shishc.controller;

import icu.shishc.Exception.CustomException;
import icu.shishc.dto.MyDTO;
import icu.shishc.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date:2021-4-15, 21:26
 * @author:ShiShc
 */

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/login")
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

    @GetMapping("/logout")
    public MyDTO Logout() {
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

}
