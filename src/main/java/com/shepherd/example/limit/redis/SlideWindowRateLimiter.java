package com.shepherd.example.limit.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/3/7 18:43
 */
@Component
public class SlideWindowRateLimiter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlideWindowRateLimiter.class);
    private static final String SLIDE_KEY = "slide-window";
    private static final Integer LIMIT_COUNT = 10;
    private static final Integer INTERVAL = 1;
    @Resource
    private RedisTemplate redisTemplate;

    public Boolean limit() {
        long currentTime = System.currentTimeMillis();
        long startTime = currentTime - INTERVAL * 1000;
        redisTemplate.opsForZSet().removeRangeByScore(SLIDE_KEY, 0, startTime);
        Long count = redisTemplate.opsForZSet().count(SLIDE_KEY, startTime, currentTime);
        if (count >= LIMIT_COUNT) {
            LOGGER.info("数量已达到限流数据，====={}", false);
            return false;
        }
        redisTemplate.opsForZSet().add(SLIDE_KEY, UUID.randomUUID().toString(), currentTime);
        redisTemplate.expire(SLIDE_KEY, 2, TimeUnit.SECONDS);
        LOGGER.info("未到达限流数量，======={}", true);
        return true;

    }


}
