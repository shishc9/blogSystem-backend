package icu.shishc.mapper;

import icu.shishc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Closer
 */
@Mapper
@Repository
public interface UserMapper {


    /**
     * 通过id查找用户
     * @param userId
     * @return
     */
    User getUserById(@Param("userId") Long userId);


    /**
     * 用户名查找
     * @param username
     * @return
     */
    User getUserByName(@Param("username") String username);


    /**
     * 通过邮箱查找用户
     * @param email email
     * @return user
     */
    User findUserByEmail(@Param("email")String email);


    /**
     * 新增
     * @param username
     * @param password
     * @param identity
     * @param age
     * @param gender
     * @param email
     * @return
     */
    Integer insert(@Param("username") String username,
                   @Param("password") String password,
                   @Param("identity") int identity,
                   @Param("age") int age,
                   @Param("gender") String gender,
                   @Param("email") String email
    );


    /**
     * 删除用户
     * @param id
     * @return
     */
    Integer delete(@Param("id") Long id);


    /**
     * 更新
     * @param username username
     * @param age age
     * @param gender gender
     * @param email email
     * @return int
     */
    Integer update(@Param("userId") Long userId,
                   @Param("username") String username,
                   @Param("age") int age,
                   @Param("gender") String gender,
                   @Param("email") String email);

    /**
     * 修改密码
     * @param password 密码
     * @param uid 用户id
     * @return int
     */
    int updatePassword(@Param("pwd")String password, @Param("uid")Long uid);
}
