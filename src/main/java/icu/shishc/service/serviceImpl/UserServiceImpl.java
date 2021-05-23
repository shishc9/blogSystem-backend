package icu.shishc.service.serviceImpl;

import icu.shishc.exception.CustomException;
import icu.shishc.entity.Perms;
import icu.shishc.entity.User;
import icu.shishc.enumeration.UserIdentity;
import icu.shishc.mapper.PermsMapper;
import icu.shishc.mapper.UserMapper;
import icu.shishc.service.UserService;
import icu.shishc.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PackageName:icu.shishc.service.serviceImpl
 * @Date:2021/3/15, 9:40
 * @Auther:ShiShc
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    PermsMapper permsMapper;


    @Override
    public List<User> getAllUsers() {
        List<User> list = userMapper.getAllUsers();
        log.info("【UserService】getAllUsers");
        return list;
    }


    @Override
    public User getUserById(Long userId) throws CustomException {
        if(!checkUserId(userId)) {
            log.warn("【UserService】getUserById::bad userid, userid = {}", userId);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        log.info("【UserService】getUserById::return user");
        return userMapper.getUserById(userId);
    }


    @Override
    public User getUserByEmail(String email) {
        log.info("【UserService】gerUserByEmail::email = {}", email);
        return userMapper.findUserByEmail(email);
    }


    @Override
    public User getUserByName(String username) throws CustomException {
        if("".equals(username.trim())) {
            log.warn("【UserService】getUserByName::bad username, username = {}", username);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        log.info("【UserService】getUserByName::return user");
        return userMapper.getUserByName(username);
    }


    @Override
    public User insert(User user) throws CustomException {
        if(!userCheck(user)) {
            log.warn("【UserService】insert::bad user entity");
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        String username = user.getUsername();
        User user1 = userMapper.getUserByName(username);
        if(user1 != null) {
            log.warn("【UserService】insert::the user has exist, username = {}", username);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        String pwd = MD5Utils.toMd5(user.getPassword(), "shishc", 10);
        user.setPassword(pwd);
        userMapper.insert(username, pwd, user.getUserIdentity().getKey(), user.getUserSite(), user.getAge(), user.getGender(), user.getEmail());
        User user2 = userMapper.getUserByName(user.getUsername());
        log.info("【UserService】insert::add user successfully! userId = {}", user2.getUserId());
        return user2;
    }


    @Override
    public Integer delete(Long userId) throws CustomException {
        if(!checkUserId(userId)) {
            log.warn("【UserService】delete::bad userid, userid = {}", userId);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        log.info("【UserService】delete::delete user, userId = {}", userId);
        return userMapper.delete(userId);
    }


    @Override
    public User update(User user) throws CustomException {
        if(!userCheck(user)) {
            log.warn("【UserService】update::bad user entity");
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        Long userId = user.getUserId();
        User user1 = userMapper.getUserById(userId);
        if(null == user1) {
            log.warn("【UserService】:update:: the user doesn't exist! userId = {}", userId);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        userMapper.update(userId, user.getUsername(), user.getAge(), user.getUserSite(), user.getGender(), user.getEmail());
        log.info("【Service】UserService::update: update successfully! userId = {}", userId);
        return userMapper.getUserById(userId);
    }


    @Override
    public boolean updatePassword(String oldPassword, Long userId, String newPassword) throws CustomException{
        log.info("【UserService】updatePwd::update");
        String dbPwd = userMapper.getPasswordByUid(userId);
        String oldP = MD5Utils.toMd5(oldPassword, "shishc", 10);
        String newP = MD5Utils.toMd5(newPassword, "shishc", 10);
        if(!oldP.equals(dbPwd)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "PWD_ERROR");
        }
        userMapper.updatePassword(newP, userId);
        log.info("【UserService】updatePassword::pwd change successfully!");
        return true;
    }


    @Override
    public boolean userCheck(User user) {
        String password = user.getPassword();
        String username = user.getUsername();
        String email = user.getEmail();
        if("".equals(username) || "".equals(password) || !regexMatch(email)) {
            log.warn("【UserService】userCheck::bad user entity");
            return false;
        }
        return true;
    }


    @Override
    public boolean regexMatch(String email) {
        String pattern = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        return email.matches(pattern);
    }


    @Override
    public Boolean checkUserId(Long userId) {
        if(userId < 0) {
            log.warn("【UserService】checkUserId::userId <= 0, userId = {}", userId);
            return false;
        }
        return true;
    }


    @Override
    public List<Perms> getUserPerms(UserIdentity identity) {
        return permsMapper.getUserPerms(identity.getKey());
    }


}
