package icu.shishc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @date: 2021-4-20, 18:54
 * @author: ShiShc
 */

@Component
public class RedisUtils {

    private static RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisUtils(RedisTemplate<String, Object> redisTemplate) { RedisUtils.redisTemplate = redisTemplate; }

    public static boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * pu tong huan cun huo qu
     * @param key
     * @return
     */
    public static Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

}
