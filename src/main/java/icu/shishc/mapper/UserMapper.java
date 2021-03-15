package icu.shishc.mapper;

import icu.shishc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User getUserById(@Param("userId") Long userId);

    User getUserByName(@Param("username") String username);

    Integer insert(@Param("user") User user);

    Integer delete(@Param("id") int id);

    Integer update(@Param("user") User user);
}
