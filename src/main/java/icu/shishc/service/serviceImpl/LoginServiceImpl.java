package icu.shishc.service.serviceImpl;

import icu.shishc.exception.CustomException;
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
        currentUser.logout();
        log.info("【LoginService】logout");
        return true;
    }


    @Override
    public User register(User user) throws CustomException {
        if(user.getUserIdentity().equals(UserIdentity.ADMIN)) {
            log.warn("【LoginService】register, you can't be ADMIN");
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        log.info("【LoginService】register");
        return userService.insert(user);
    }
}
