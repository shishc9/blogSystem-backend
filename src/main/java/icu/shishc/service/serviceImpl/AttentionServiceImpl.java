package icu.shishc.service.serviceImpl;

import icu.shishc.entity.User;
import icu.shishc.mapper.AttentionMapper;
import icu.shishc.mapper.UserMapper;
import icu.shishc.service.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @date: 2021-5-26, 22:47
 * @author: ShiShc
 */

@Service
public class AttentionServiceImpl implements AttentionService {

    @Autowired
    AttentionMapper attentionMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public int addAttention(Long uid, Long uided) {
        User user1 = userMapper.getUserById(uid);
        User user2 = userMapper.getUserById(uided);
        userMapper.updateNum(uid, user1.getPostCount(), user1.getLikeCount(), user1.getFollowing() + 1, user1.getFollowed());
        userMapper.updateNum(uided, user2.getPostCount(), user2.getLikeCount(), user2.getFollowing(), user2.getFollowed() + 1);
        return attentionMapper.addAttention(uid, uided);
    }

    @Override
    public int cancelAttention(Long uid, Long uided) {
        User user1 = userMapper.getUserById(uid);
        User user2 = userMapper.getUserById(uided);
        userMapper.updateNum(uid, user1.getPostCount(), user1.getLikeCount(), user1.getFollowing() - 1, user1.getFollowed());
        userMapper.updateNum(uided, user2.getPostCount(), user2.getLikeCount(), user2.getFollowing(), user2.getFollowed() - 1);
        return attentionMapper.cancelAttention(uid, uided);
    }

    @Override
    public List<User> getAttention(Long uid) {
        List<Long> list = attentionMapper.getAttentions(uid);
        return userMapper.getUserByList(list);
    }

    @Override
    public List<User> getAttentioned(Long uid) {
        List<Long> list = attentionMapper.countAttentions(uid);
        return userMapper.getUserByList(list);
    }

    @Override
    public int attentionOrNot(Long uid, Long uided) {
        return attentionMapper.attentionOrNot(uid, uided);
    }
}
