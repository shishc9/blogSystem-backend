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
        return attentionMapper.addAttention(uid, uided);
    }

    @Override
    public int cancelAttention(Long uid, Long uided) {
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
