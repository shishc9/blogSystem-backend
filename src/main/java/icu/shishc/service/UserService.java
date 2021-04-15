package icu.shishc.service;

import icu.shishc.Exception.CustomException;
import icu.shishc.entity.User;

public interface UserService{


    /**
     * 根据UID查找用户
     * @param userId
     * @return
     * @throws CustomException
     */
    User getUserById(Long userId) throws CustomException;


    /**
     * 根据用户名查找用户
     * @param username
     * @return
     * @throws CustomException
     */
    User getUserByName(String username) throws CustomException;


    /**
     * 新增一个用户
     * @param user
     * @return
     * @throws CustomException
     */
    User insert(User user) throws CustomException;


    /**
     * 删除一个用户
     * @param id
     * @return
     * @throws CustomException
     */
    Integer delete(Long id) throws CustomException;


    /**
     * 更新一个用户
     * @param user
     * @return
     * @throws CustomException
     */
    User update(User user) throws CustomException;


    /**
     * user Identity check.
     * @param user
     * @return
     */
    boolean userCheck(User user);


    /**
     * email format check
     * @param email
     * @return
     */
    boolean regexMatch(String email);


    /**
     * 获取用户角色
     * @param username
     * @return
     */
    String getRole(String username);
}
