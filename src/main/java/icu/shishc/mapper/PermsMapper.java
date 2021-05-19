package icu.shishc.mapper;

import icu.shishc.entity.Perms;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Closer
 * @DESC: 权限列表
 */
public interface PermsMapper {

    /**
     * 获取用户所有权限
     * @param identity identity
     * @return list
     */
    List<Perms> getUserPerms(@Param("identity") Integer identity);

}
