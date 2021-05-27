package icu.shishc.service.serviceImpl;

import icu.shishc.entity.User;
import icu.shishc.exception.CustomException;
import icu.shishc.mapper.AttentionMapper;
import icu.shishc.mapper.UserMapper;
import icu.shishc.service.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public int addAttention(Long uid, Long uided) throws CustomException {
        if(!userExistOrNot(uid) || ! userExistOrNot(uided)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "USER_NOT_EXIST");
        }
        if(attentionOrNot(uid, uided) == 1) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "DON'T_ATTENTION_AGAIN");
        }
        User user1 = userMapper.getUserById(uid);
        User user2 = userMapper.getUserById(uided);
        userMapper.updateNum(uid, user1.getPostCount(), user1.getLikeCount(), user1.getCollectionCount(),user1.getFollowing() + 1, user1.getFollowed());
        userMapper.updateNum(uided, user2.getPostCount(), user2.getLikeCount(),user2.getCollectionCount(), user2.getFollowing(), user2.getFollowed() + 1);
        return attentionMapper.addAttention(uid, uided);
    }

    @Override
    public int cancelAttention(Long uid, Long uided) throws CustomException {
        if(!userExistOrNot(uid) || ! userExistOrNot(uided)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "USER_NOT_EXIST");
        }
        User user1 = userMapper.getUserById(uid);
        User user2 = userMapper.getUserById(uided);
        userMapper.updateNum(uid, user1.getPostCount(), user1.getLikeCount(), user1.getCollectionCount(),user1.getFollowing() - 1, user1.getFollowed());
        userMapper.updateNum(uided, user2.getPostCount(), user2.getLikeCount(), user2.getCollectionCount(), user2.getFollowing(), user2.getFollowed() - 1);
        return attentionMapper.cancelAttention(uid, uided);
    }

    @Override
    public List<User> getAttention(Long uid) throws CustomException {
        if(!userExistOrNot(uid)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "USER_NOT_EXIST");
        }
        List<Long> list = attentionMapper.getAttentions(uid);
        if(list.size() > 0) {
            return userMapper.getUserByList(list);
        }
        return null;
    }

    @Override
    public List<User> getAttentioned(Long uid) throws CustomException{
        if(!userExistOrNot(uid)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "USER_NOT_EXIST");
        }
        List<Long> list = attentionMapper.countAttentions(uid);
        if(list.size() > 0) {
            return userMapper.getUserByList(list);
        }
        return null;
    }

    @Override
    public int attentionOrNot(Long uid, Long uided) throws CustomException {
        if(!userExistOrNot(uid) || ! userExistOrNot(uided)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "USER_NOT_EXIST");
        }
        return attentionMapper.attentionOrNot(uid, uided);
    }


    private boolean userExistOrNot(Long uid) {
        User user = userMapper.getUserById(uid);
        return user != null;
    }
}
