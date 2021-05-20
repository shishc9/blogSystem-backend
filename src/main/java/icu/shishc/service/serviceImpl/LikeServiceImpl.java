package icu.shishc.service.serviceImpl;

import icu.shishc.mapper.LikeMapper;
import icu.shishc.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Override
    public Integer addLike(Long bid, Long userId) {
        log.info("【LikeService】addLike");
        return likeMapper.addLike(bid, userId);
    }

    @Override
    public Integer cancelLike(Long bid, Long userId) {
        log.info("【LikeService】cancelLike");
        return likeMapper.cancelLike(bid, userId);
    }
}
