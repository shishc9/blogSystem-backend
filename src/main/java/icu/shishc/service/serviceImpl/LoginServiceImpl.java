package icu.shishc.service.serviceImpl;

import icu.shishc.Exception.CustomException;
import icu.shishc.dto.MyDTO;
import icu.shishc.entity.User;
import icu.shishc.enumeration.UserIdentity;
import icu.shishc.service.LoginService;
import icu.shishc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
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
            log.warn("【LoginService】authLogin::the user doesn't exists! username = {}", username);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_REQUEST");
        } else if(!user.getPassword().equals(password)) {
            log.warn("【LoginService】authLogin::pwd error! username = {}, pwd = {}", username, password);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_REQUEST");
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


//    /**
//     * 目前先不用这个, 预留方法
//     * @return
//     */
//    @Override
//    public String getInfo() {
//        //Session session = SecurityUtils.getSubject().getSession();
//        return null;
//    }


    @Override
    public boolean logout() throws CustomException{
        Subject currentUser = SecurityUtils.getSubject();
//        if(!currentUser.isAuthenticated()) {
//            log.warn("【Service】LoginService::logout, Not logged in, unable to log out");
//            throw new CustomException(HttpStatus.BAD_REQUEST, "Not logged in, unable to log out");
//        }
//        预留，处理没有登录但请求登出的情况
//        if(currentUser == null) {
//            log.warn("【Service】LoginService::logout, please log in first");
//            throw new CustomException(HttpStatus.OK, "please log in first");
//        }
        currentUser.logout();
        log.info("【LoginService】logout");
        return true;
    }


    @Override
    public User register(User user) throws CustomException {
        if(user.getUserIdentity().equals(UserIdentity.ADMIN)) {
            log.warn("【LoginService】register, you can't be ADMIN");
            throw new CustomException(HttpStatus.BAD_REQUEST, "U_NOT_BE_ADMIN");
        }
        log.info("【LoginService】register");
        return userService.insert(user);
    }
}
