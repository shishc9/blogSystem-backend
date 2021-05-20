package icu.shishc.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LikeMapper {

    int addLike(@Param("bid")Long bid, @Param("uid")Long uid);


    int cancelLike(@Param("bid")Long bid, @Param("uid")Long uid);

}
