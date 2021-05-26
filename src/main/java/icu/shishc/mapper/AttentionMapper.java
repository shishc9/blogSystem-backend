package icu.shishc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AttentionMapper {

    /**
     * 新增一个关注
     * @param uid uid
     * @param uided uided
     * @return .
     */
    int addAttention(@Param("uid") Long uid, @Param("uided") Long uided);

    /**
     * 取消关注
     * @param uid uid
     * @param uided uided
     * @return .
     */
    int cancelAttention(@Param("uid") Long uid, @Param("uided") Long uided);

    /**
     * 获取他的关注
     * @param uid uid
     * @return .
     */
    List<Long> getAttentions(@Param("uid") Long uid);

    /**
     * 获取关注他的
     * @param uid uid
     * @return .
     */
    List<Long> countAttentions(@Param("uid") Long uid);

    /**
     * 是否关注了
     * @param uid uid
     * @param uided uided
     * @return .
     */
    int attentionOrNot(@Param("uid") Long uid, @Param("uided") Long uided);


    /**
     * 删除用户的所有关注和被关注
     * @param uid uid
     * @return .
     */
    int deleteUserAttention(@Param("uid") Long uid);
}
