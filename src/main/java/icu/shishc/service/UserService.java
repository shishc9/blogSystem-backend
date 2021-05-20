package icu.shishc.service;

import icu.shishc.Exception.CustomException;
import icu.shishc.entity.Perms;
import icu.shishc.entity.User;
import icu.shishc.enumeration.UserIdentity;

import java.util.List;

/**
 * @author ShiShc
 */
public interface UserService{


    /**
     * 根据UID查找用户
     * @param userId 用户id
     * @return 用户实体
     * @throws CustomException .
     */
    User getUserById(Long userId) throws CustomException;


    /**
     * 通过邮箱查找用户
     * @param email 邮箱
     * @return 用户user
     */
    User getUserByEmail(String email);


    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户实体
     * @throws CustomException .
     */
    User getUserByName(String username) throws CustomException;


    /**
     * 新增一个用户
     * @param user 用户实体
     * @return 新增用户实体
     * @throws CustomException .
     */
    User insert(User user) throws CustomException;


    /**
     * 删除一个用户
     * @param id 用户id
     * @return 1 0
     * @throws CustomException .
     */
    Integer delete(Long id) throws CustomException;


    /**
     * 更新一个用户
     * @param user 用户实体
     * @return 更新后的用户
     * @throws CustomException .
     */
    User update(User user) throws CustomException;


    /**
     * user Identity check.
     * @param user 用户实体
     * @return T / F
     */
    boolean userCheck(User user);


    /**
     * 用户邮箱正则表达式检验
     * @param email 用户邮箱
     * @return T / F
     */
    boolean regexMatch(String email);


    /**
     * 检查userid
     * @param userId 用户ID
     * @return T/F
     */
    Boolean checkUserId(Long userId);


    /**
     * 获取用户所有权限
     * @param identity identity
     * @return list
     */
    List<Perms> getUserPerms(UserIdentity identity);


    /**
     * 更新密码
     * @param userId 用户id
     * @param password 密码
     */
    void updatePassword(Long userId, String password);


    /**
     * 获取所有用户
     * @return list
     */
    List<User> getAllUsers();
}
