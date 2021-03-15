package icu.shishc.service.serviceImpl;

import icu.shishc.entity.User;
import icu.shishc.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @PackageName:icu.shishc.service.serviceImpl
 * @Date:2021/3/15, 9:40
 * @Auther:ShiShc
 */

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public User getUserByName(String username) {
        return null;
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
