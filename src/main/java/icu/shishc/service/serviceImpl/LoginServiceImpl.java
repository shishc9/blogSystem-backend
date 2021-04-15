package icu.shishc.service.serviceImpl;

import icu.shishc.Exception.CustomException;
import icu.shishc.entity.User;
import icu.shishc.service.LoginService;
import icu.shishc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @date:2021-4-15, 21:30
 * @author:ShiShc
 */

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Override
    public boolean authLogin(String username, String password) throws CustomException {
        User user = userService.getUserByName(username);
        if(user == null) {
            log.warn("【Service】LoginService::the user doesn't exists! username = {}", username);
            throw new CustomException(HttpStatus.BAD_REQUEST, "the user doesn't exists!");
        } else if(!user.getPassword().equals(password)) {
            log.warn("【Service】LoginService::pwd error! username = {}, pwd = {}", username, password);
            throw new CustomException(HttpStatus.BAD_REQUEST, "pwd error!");
        }
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);
            return true;
        } catch (AuthenticationException e) {
            return false;
        }
    }

    @Override
    public String getInfo() {
        //Session session = SecurityUtils.getSubject().getSession();
        return null;
    }

    @Override
    public boolean logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        log.info("【Service】LoginService::logout");
        return true;
    }
}
