package com.shepherd.common.limit.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/3/7 18:03
 */
@Component
public class FixWindowRateLimiter {
    @Resource
    private RedisConnectionFactory redisConnectionFactory;
    private static final String FIX_KEY = "fix-window";
    private static final Integer LIMIT_COUNT = 10;

    public Boolean limit() {
        RedisAtomicInteger redisAtomicInteger = new RedisAtomicInteger(FIX_KEY, redisConnectionFactory);
        int count = redisAtomicInteger.getAndIncrement();
        System.out.println(count);
        if (count == 0) {
            redisAtomicInteger.expire(1,TimeUnit.SECONDS);
        }
        if (count >= LIMIT_COUNT) {
            return false;
        }
        return true;
    }
}
