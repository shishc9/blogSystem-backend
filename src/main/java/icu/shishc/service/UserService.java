package icu.shishc.service;

import icu.shishc.entity.User;
import org.springframework.stereotype.Service;

public interface UserService{


    /**
     * 根据UID查找用户
     * @param userId
     * @return
     */
    User getUserById(Long userId);


    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User getUserByName(String username);



    Integer insert(User user);


    Integer delete(int id);


    Integer update(User user);
}
