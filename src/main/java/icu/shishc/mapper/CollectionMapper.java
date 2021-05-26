package icu.shishc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @date: 2021-5-25, 19:24
 * @author: ShiShc
 */

@Mapper
@Repository
public interface CollectionMapper {

    int userAddBlogCollection(@Param("uid") Long uid, @Param("bid") Long bid);


    int userCancelBlogCollection(@Param("uid") Long uid, @Param("bid") Long bid);


    List<Long> getCollections(@Param("uid") Long uid);


    int collectionOrNot(@Param("uid") Long uid, @Param("bid") Long bid);


    int deleteUserCollections(@Param("uid") Long uid);


    int deleteBlogCollection(@Param("bid") Long bid);
}
