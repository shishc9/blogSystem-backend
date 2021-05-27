package icu.shishc.service;

import icu.shishc.entity.User;
import icu.shishc.exception.CustomException;

import java.util.List;

public interface AttentionService {

    /**
     * 添加关注
     * @param uid uid
     * @param uided uided
     * @return .
     */
    int addAttention(Long uid, Long uided) throws CustomException;

    /**
     * 取消关注
     * @param uid uid
     * @param uided uided
     * @return .
     */
    int cancelAttention(Long uid, Long uided) throws CustomException;

    /**
     * 获取一个用户的关注
     * @param uid uid
     * @return .
     */
    List<User> getAttention(Long uid) throws CustomException;

    /**
     * 获取关注这个用户的人
     * @param uid uid
     * @return .
     */
    List<User> getAttentioned(Long uid) throws CustomException;

    /**
     * 是否关注
     * @param uid uid
     * @param uided uided
     * @return .
     */
    int attentionOrNot(Long uid, Long uided) throws CustomException;

}
