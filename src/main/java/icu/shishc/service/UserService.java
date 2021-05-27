package icu.shishc.service;

import icu.shishc.exception.CustomException;
import icu.shishc.entity.Perms;
import icu.shishc.entity.User;
import icu.shishc.enumeration.UserIdentity;

import java.util.List;

/**
 * @author ShiShc
 */
public interface UserService{

    /**
     * 更新登陆时间
     * @param username username
     */
    void updateLoginTime(String username);


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


//    /**
//     * 删除一个用户的所有附带数据
//     * @param uid uid
//     * @throws CustomException .
//     */
//    void deleteUserData(Long uid) throws CustomException;


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
     * @param oldPassword 旧密码
     * @param userId 用户id
     * @param newPassword 新密码
     * @return T / F
     */
    boolean updatePassword(String oldPassword, Long userId, String newPassword) throws CustomException;


    /**
     * 更新用户统计量
     * @param postCount postCount
     * @param likeCount likeCount
     * @param following following
     * @param followed followed
     */
    void updateUserNum(Long uid, Integer postCount, Integer likeCount, Integer collectionNum, Integer following, Integer followed);


    /**
     * 获取所有用户
     * @return list
     */
    List<User> getAllUsers();
}
