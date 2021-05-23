package icu.shishc.mapper;

import icu.shishc.entity.Perms;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Closer
 * @DESC: 权限列表
 */
@Mapper
@Repository
public interface PermsMapper {

    /**
     * 获取用户所有权限
     * @param identity identity
     * @return list
     */
    List<Perms> getUserPerms(@Param("identity") Integer identity);

}
