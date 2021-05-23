package icu.shishc.mapper;

import icu.shishc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Closer
 */
@Mapper
@Repository
public interface UserMapper {


    /**
     * 通过id查找用户
     * @param userId 用户id
     * @return user
     */
    User getUserById(@Param("userId") Long userId);


    /**
     * 用户名查找
     * @param username username
     * @return user
     */
    User getUserByName(@Param("username") String username);


    /**
     * 通过邮箱查找用户
     * @param email email
     * @return user
     */
    User findUserByEmail(@Param("email")String email);


    /**
     * 获取除管理员外的所有用户
     * @return list
     */
    List<User> getAllUsers();

    /**
     * 新增
     * @param username 用户名
     * @param password 密码
     * @param identity 身份
     * @param age 年龄
     * @param gender 性别
     * @param email 邮件
     * @return int
     */
    Integer insert(@Param("username") String username,
                   @Param("password") String password,
                   @Param("identity") int identity,
                   @Param("site") String userSite,
                   @Param("age") int age,
                   @Param("gender") String gender,
                   @Param("email") String email
    );


    /**
     * 删除用户
     * @param id 用户id
     * @return int
     */
    Integer delete(@Param("id") Long id);


    /**
     * 更新用户基本信息（除了密码）
     * @param userId 用户id
     * @param username 用户名
     * @param age 年龄
     * @param gender 性别
     * @param email 邮件
     * @return int
     */
    Integer update(@Param("userId") Long userId,
                   @Param("username") String username,
                   @Param("age") int age,
                   @Param("site") String userSite,
                   @Param("gender") String gender,
                   @Param("email") String email);


    /**
     * 修改密码
     * @param password 密码
     * @param uid 用户id
     * @return int
     */
    int updatePassword(@Param("pwd")String password, @Param("uid")Long uid);


    /**
     * 通过用户id查找密码
     * @param uid 用户id
     * @return string
     */
    String getPasswordByUid(@Param("uid")Long uid);
}
