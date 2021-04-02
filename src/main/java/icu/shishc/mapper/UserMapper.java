package icu.shishc.mapper;

import icu.shishc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
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
     * 新增
     * @param username
     * @param password
     * @param identity
     * @param age
     * @param gender
     * @param hobby
     * @param email
     * @return
     */
    Integer insert(@Param("username") String username,
                   @Param("password") String password,
                   @Param("identity") int identity,
                   @Param("age") int age,
                   @Param("gender") String gender,
                   @Param("hobby") String hobby,
                   @Param("email") String email
    );


    /**
     * 删除用户
     * @param id
     * @return
     */
    Integer delete(@Param("id") int id);


    /**
     * 更新
     * @param username
     * @param password
     * @param age
     * @param gender
     * @param hobby
     * @param email
     * @return
     */
    Integer update(@Param("userId") Long userId,
                   @Param("username") String username,
                   @Param("password") String password,
                   @Param("age") int age,
                   @Param("gender") String gender,
                   @Param("hobby") String hobby,
                   @Param("email") String email);
}
