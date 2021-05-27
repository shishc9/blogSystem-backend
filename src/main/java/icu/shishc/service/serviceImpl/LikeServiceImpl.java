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
        log.info("【LikeService】addLike");
        if(userService.getUserById(userId) == null) {
            log.warn("【LikeService】addLike::bad userid, userid = {}", userId);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        if(blogMapper.getBlogByBID(bid) == null) {
            log.warn("【LikeService】addLike::bad blogId, blogId = {}", bid);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        blogMapper.addALike(bid);
        return likeMapper.addLike(bid, userId);
    }

    @Override
    public Integer cancelLike(Long bid, Long userId) throws CustomException {
        log.info("【LikeService】cancelLike");
        if(userService.getUserById(userId) == null) {
            log.warn("【LikeService】cancelLike::bad userid, userid = {}", userId);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        if(blogMapper.getBlogByBID(bid) == null) {
            log.warn("【LikeService】cancelLike::bad blogId, blogId = {}", bid);
            throw new CustomException(HttpStatus.BAD_REQUEST, "BAD_PARAM");
        }
        blogMapper.cancelLikes(bid, 1);
        return likeMapper.cancelLike(bid, userId);
    }


    @Override
    public Integer deleteBlogLikes(Long bid){
        // 删除博客的所有点赞
        return likeMapper.deleteBlogAllLike(bid);
    }

    @Override
    public Integer likeOrNot(Long bid, Long uid) {
        return likeMapper.likeOrNot(uid, bid);
    }
}
