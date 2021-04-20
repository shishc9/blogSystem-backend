package icu.shishc.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @date: 2021-4-20, 18:54
 * @author: ShiShc
 */

@Component
public class RedisUtil {

    private RedisTemplate<String, Object> redisTemplate;

    RedisUtil(RedisTemplate<String, Object> redisTemplate) { this.redisTemplate = redisTemplate; }



}
