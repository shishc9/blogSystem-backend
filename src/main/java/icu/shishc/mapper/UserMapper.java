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
     * 获取用户id
     * @param username
     * @return
     */
    Long getUserId(@Param("username") String username);


    /**
     * 用户名查找
     * @param username username
     * @return user
     */
    User getUserByName(@Param("username") String username);


    /**
     * list批量查找用户
     * @param list list
     * @return .
     */
    List<User> getUserByList(@Param("list") List<Long> list);


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
     * @param email 邮件
     * @param avatar 头像
     * @return int
     */
    Integer insert(@Param("username") String username,
                   @Param("password") String password,
                   @Param("avatar") String avatar,
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
     * @param email 邮件
     * @param avatar 头像
     * @return int
     */
    Integer update(@Param("userId") Long userId,
                   @Param("username") String username,
                   @Param("site") String userSite,
                   @Param("avatar") String avatar,
                   @Param("email") String email);


    Integer updateNum(@Param("uid") Long uid,
                      @Param("postCount") Integer postCount,
                      @Param("likeCount") Integer likeCount,
                      @Param("collectionCount") Integer collectionCount,
                      @Param("following") Integer following,
                      @Param("followed") Integer followed);


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


    /**
     * 以下都是统计量的修改
     */
    int addPostCount(@Param("uid") Long uid);


    int cancelPostCount(@Param("uid") Long uid);


    int addLikeCount(@Param("uid") Long uid);


    int cancelLikeCount(@Param("uid") Long uid);


    int addFollowingCount(@Param("uid") Long uid);


    int cancelFollowingCount(@Param("uid") Long uid);


    int addFollowedCount(@Param("uid") Long uid);


    int cancelFollowedCount(@Param("uid") Long uid);


    int updateLoginTimeUsername(@Param("username")String username);


    int updateLoginTimeEmail(@Param("email") String email);

}
