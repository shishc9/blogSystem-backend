package icu.shishc.service;

import icu.shishc.Exception.CustomException;
import icu.shishc.entity.User;
import org.springframework.stereotype.Service;

public interface UserService{


    /**
     * 根据UID查找用户
     * @param userId
     * @return
     */
    User getUserById(Long userId) throws CustomException;


    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User getUserByName(String username) throws CustomException;


    /**
     * 新增一个用户
     * @param user
     * @return
     */
    User insert(User user) throws CustomException;


    /**
     * 删除一个用户
     * @param id
     * @return
     */
    Integer delete(int id) throws CustomException;


    /**
     * 更新一个用户信息
     * @param user
     * @return
     */
    User update(User user) throws CustomException;
}
