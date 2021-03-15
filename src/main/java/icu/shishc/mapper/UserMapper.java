package icu.shishc.mapper;

import icu.shishc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User getUserById(@Param("userId") Long userId);

    User getUserByName(@Param("username") String username);

    int insert(@Param("user") User user);

    int delete(@Param("id") int id);

    int update(@Param("user") User user);
}
