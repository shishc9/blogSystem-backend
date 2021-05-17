package icu.shishc.service.serviceImpl;

import icu.shishc.Exception.CustomException;
import icu.shishc.entity.User;
import icu.shishc.mapper.UserMapper;
import icu.shishc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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


    @Override
    public User getUserById(Long userId) throws CustomException {
        if(!checkUserId(userId)) {
            log.warn("【UserService】getUserById::bad userid, userid = {}", userId);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_ENTITY");
        }
        log.info("【UserService】getUserById::return user");
        return userMapper.getUserById(userId);
    }


    @Override
    public User getUserByName(String username) throws CustomException {
        if("".equals(username.trim())) {
            log.warn("【UserService】getUserByName::bad username, username = {}", username);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_USERNAME");
        }
        log.info("【UserService】getUserByName::return user");
        return userMapper.getUserByName(username);
    }


    @Override
    public User insert(User user) throws CustomException {
        if(!userCheck(user)) {
            log.warn("【UserService】insert::bad user entity");
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_USER_ENTITY");
        }
        String username = user.getUsername();
        User user1 = userMapper.getUserByName(username);
        if(user1 != null) {
            log.warn("【UserService】insert::the user has exist, username = {}", username);
            throw new CustomException(HttpStatus.BAD_REQUEST, "USER_EXIST");
        }
        userMapper.insert(username, user.getPassword(), user.getUserIdentity().getKey(), user.getAge(), user.getGender(), user.getEmail());
        User user2 = userMapper.getUserByName(user.getUsername());
        log.info("【UserService】insert::add user successfully! userId = {}", user2.getUserId());
        return user2;
    }


    @Override
    public Integer delete(Long userId) throws CustomException {
        if(!checkUserId(userId)) {
            log.warn("【UserService】delete::bad userid, userid = {}", userId);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_UID");
        }
        User user = userMapper.getUserById(userId);
        if(null == user) {
            log.warn("【Service】UserService::delete: the user doesn't exist, uid = {}", userId);
            throw new CustomException(HttpStatus.BAD_REQUEST, "USER_NOT_EXIST");
        }
        userMapper.delete(userId);
        log.info("【Service】UserService::delete: delete user successfully! userId = {}", userId);
        return 1;
    }


    @Override
    public User update(User user) throws CustomException {
        if(!userCheck(user)) {
            log.warn("【UserService】update::bad user entity");
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_USER_ENTITY");
        }
        Long userId = user.getUserId();
        User user1 = userMapper.getUserById(userId);
        if(null == user1) {
            log.warn("【UserService】:update:: the user doesn't exist! userId = {}", userId);
            throw new CustomException(HttpStatus.BAD_REQUEST, "USER_NOT_EXIST");
        }
        userMapper.update(userId, user.getUsername(), user.getPassword(), user.getAge(), user.getGender(), user.getEmail());
        log.info("【Service】UserService::update: update successfully! userId = {}", userId);
        return userMapper.getUserById(userId);
    }


    @Override
    public boolean userCheck(User user) {
        Long userId = user.getUserId();
        String password = user.getPassword();
        String username = user.getUsername();
        String email = user.getEmail();
        String gender = user.getGender();
        if(!checkUserId(userId) || "".equals(username) || "".equals(password) || !regexMatch(email) || (!"MALE".equals(gender) && !"FEMALE".equals(gender))) {
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
        if(userId <= 0) {
            log.warn("【UserService】checkUserId::userId <= 0, userId = {}", userId);
            return false;
        }
        return true;
    }
}
