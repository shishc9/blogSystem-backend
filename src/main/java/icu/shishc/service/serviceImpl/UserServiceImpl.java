package icu.shishc.service.serviceImpl;

import icu.shishc.Exception.CustomException;
import icu.shishc.entity.User;
import icu.shishc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @PackageName:icu.shishc.service.serviceImpl
 * @Date:2021/3/15, 9:40
 * @Auther:ShiShc
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(Long userId) throws CustomException {
        return null;
    }

    @Override
    public User getUserByName(String username) throws CustomException {
        return null;
    }

    @Override
    public User insert(User user) throws CustomException {
        return null;
    }

    @Override
    public Integer delete(Long id) throws CustomException {
        return null;
    }

    @Override
    public User update(User user) throws CustomException {
        return null;
    }

    @Override
    public boolean userCheck(User user) {
        return true;
    }

    @Override
    public boolean regexMatch(String email) {
        return false;
    }

    @Override
    public String getRole(String username) {
        return null;
    }


//    @Autowired
//    UserMapper userMapper;
//
//
//    @Override
//    public User getUserById(Long userId)
//            throws CustomException {
//        if(userId <= 0) {
//            log.warn("【Service】UserService::getUserById:Illegal param, userId <= 0");
//            throw new CustomException(HttpStatus.BAD_REQUEST, "Bad param");
//        }
//        User user = userMapper.getUserById(userId);
//        if(user == null) {
//            log.warn("【Service】UserService::getUserById:the user doesn't exists, userId = {}", userId);
//            throw new CustomException(HttpStatus.OK, "Bad request");
//        } else {
//            log.info("【Service】UserService::getUserById:return the user, userId = {}", userId);
//            return user;
//        }
//    }
//
//
//    @Override
//    public User getUserByName(String username) throws CustomException{
//        User user = userMapper.getUserByName(username);
//        if(user == null) {
//            log.warn("【Service】UserService::getUserByName:the user doesn't exists, username = {}", username);
//            throw new CustomException(HttpStatus.OK, "Bad request");
//        } else {
//            log.info("【Service】UserService::getUserByName:return the user, username = {}", username);
//            return user;
//        }
//    }
//
//
//    @Override
//    public User insert(User user) throws CustomException{
//        User user1 = userMapper.getUserByName(user.getUsername());
//        if(user1 != null) {
//            log.warn("【Service】UserService::insert:the user has exist, userId = {}", user.getUserId());
//            throw new CustomException(HttpStatus.BAD_REQUEST, "User has exist!");
//        }
//        userMapper.insert(user.getUsername(), user.getPassword(), user.getUserIdentity().getKey(), user.getAge(), user.getGender(), user.getHobby(), user.getEmail());
//        User user2 = userMapper.getUserByName(user.getUsername());
//        log.info("【Service】UserService::insert:add user successfully! userId = {}", user2.getUserId());
//        return user2;
//    }
//
//
//    @Override
//    public Integer delete(Long id) throws CustomException {
//        if(id <= 0) {
//            log.warn("【Service】UserService::delete:Illegal param, userId <= 0");
//            throw new CustomException(HttpStatus.BAD_REQUEST, "Bad param");
//        }
//        User user = userMapper.getUserById(id);
//        if(null == user) {
//            log.warn("【Service】UserService::delete: the user doesn't exist, uid = {}", id);
//            throw new CustomException(HttpStatus.BAD_REQUEST, "delete failed!");
//        }
//        userMapper.delete(id);
//        log.info("【Service】UserService::delete: delete user successfully! userId = {}", id);
//        return 1;
//    }
//
//
//    @Override
//    public User update(User user) throws CustomException {
//        Long userId = user.getUserId();
//        User user1 = userMapper.getUserById(userId);
//        if(null == user1) {
//            log.warn("【Service】UserService::update: the user doesn't exist! userId = {}", userId);
//            throw new CustomException(HttpStatus.BAD_REQUEST, "user doesn't exist!");
//        }
//        userMapper.update(userId, user.getUsername(), user.getPassword(), user.getAge(), user.getGender(), user.getEmail());
//        log.info("【Service】UserService::update: update successfully! userId = {}", userId);
//        return userMapper.getUserById(userId);
//    }
//
//    @Override
//    public boolean userCheck(User user) {
//        String username = user.getUsername().trim();
//        String password = user.getPassword().trim();
//        String email = user.getEmail().trim();
//        if(username.equals("") || password.contains(" ") || !regexMatch(email)) {
//            log.info("【Service】UserService::userCheck: bad user entity");
//            return false;
//        }
//        log.info("【Service】UserService::userCheck: correct user entity");
//        return true;
//    }
//
//    @Override
//    public boolean regexMatch(String email) {
//        String pattern = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
//        log.info("【Service】UserService::regexMatch: email format check");
//        return email.matches(pattern);
//    }
//
//    @Override
//    public String getRole(String username) {
//        log.info("【Service】UserService::getRole, username = {}", username);
//        return userMapper.getUserByName(username).getUserIdentity().toString();
//    }


}
