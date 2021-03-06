package icu.shishc.service.serviceImpl;

import icu.shishc.entity.User;
import icu.shishc.exception.CustomException;
import icu.shishc.mapper.BlogMapper;
import icu.shishc.mapper.LikeMapper;
import icu.shishc.service.LikeService;
import icu.shishc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @date: 2021-5-20, 11:56
 * @author: ShiShc
 */

@Slf4j
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    LikeMapper likeMapper;
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    UserService userService;


    @Override
    public Integer addLike(Long bid, Long userId) throws CustomException {
        if(likeOrNot(bid, userId) == 1) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_REQUEST");
        }
        if(blogMapper.getBlogByBID(bid).getIsDelete() == 1) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "THE_BLOG_HAS_DELETED");
        }
        log.info("【LikeService】addLike");
        blogMapper.addALike(bid);
        Long uid = blogMapper.getUserByBid(bid);
        User user = userService.getUserById(uid);
        userService.updateUserNum(uid, user.getPostCount(), user.getLikeCount() + 1, user.getCollectionCount(), user.getFollowing(), user.getFollowed());
        return likeMapper.addLike(bid, userId);
    }

    @Override
    public Integer cancelLike(Long bid, Long userId) throws CustomException {
        if(likeOrNot(bid, userId) == 0) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_REQUEST");
        }
        if(blogMapper.getBlogByBID(bid).getIsDelete() == 1) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "THE_BLOG_HAS_DELETED");
        }
        log.info("【LikeService】cancelLike");
        blogMapper.cancelLikes(bid, 1);
        Long uid = blogMapper.getUserByBid(bid);
        User user = userService.getUserById(uid);
        userService.updateUserNum(uid, user.getPostCount(), user.getLikeCount() - 1, user.getCollectionCount(), user.getFollowing(), user.getFollowed());
        return likeMapper.cancelLike(bid, userId);
    }


    @Override
    public Integer deleteBlogLikes(Long bid){
        // 删除博客的所有点赞
        return likeMapper.deleteBlogAllLike(bid);
    }

    @Override
    public Integer likeOrNot(Long bid, Long uid) throws CustomException {
        if(userService.getUserById(uid) == null || blogMapper.getBlogByBID(bid) == null) {
            log.warn("【LikeService】cancelLike::bad userid, userid = {}", uid);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        return likeMapper.likeOrNot(uid, bid);
    }
}
