package icu.shishc.service.serviceImpl;

import icu.shishc.entity.User;
import icu.shishc.mapper.UserMapper;
import icu.shishc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @PackageName:icu.shishc.service.serviceImpl
 * @Date:2021/3/15, 9:40
 * @Auther:ShiShc
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(Long userId) {
        User user = userMapper.getUserById(userId);
        return user;
    }

    @Override
    public User getUserByName(String username) {
        User user = userMapper.getUserByName(username);
        return user;
    }

    @Override
    public Integer insert(User user) {
        return 0;
    }

    @Override
    public Integer delete(int id) {
        return 0;
    }

    @Override
    public Integer update(User user) {
        return 0;
    }
}
