package com.shepherd.common.limit.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.IOException;
import java.util.UUID;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/3/7 16:11
 */
public class SimpleRateLimiter {
    private Jedis jedis;
    public SimpleRateLimiter(Jedis jedis) {
        this.jedis = jedis;
    }

    /**
     * 整体思路：每次行为(调接口)都添加一条记录，然后删除当前时间窗口外的数据，再比较当前zset的值数量和最大限流数
     * 通过score为当前时间戳来维护时间窗口，用当前时间减去限流间隔时间即可推出当前时间窗口，所以这是滑动时间窗口
     * value为uuid，这里value并没有意义，只需要保证唯一，如果不唯一很有可能在并发情况当做score和value都相同只记录一条，导致统计值不对
     * 缺点：如果限流时间间隔内产生大量行为，会导致redis保存大量的key，浪费大量的空间
     * 这里针对是个人限流，所以key里面包含userId
     * @param userId
     * @param actionKey
     * @param period
     * @param maxCount
     * @return
     * @throws IOException
     */
    public boolean isActionAllowed(String userId, String actionKey, int period, int maxCount) throws IOException {
        String key = String.format("hist:%s:%s", userId, actionKey);
        long nowTs = System.currentTimeMillis();
        Pipeline pipe = jedis.pipelined();
        pipe.multi();
        // 记录行为，这里的score为数据戳，value为uuid
        pipe.zadd(key, nowTs, UUID.randomUUID().toString());
        // 移除时间窗口之前的行为记录，剩下的都是时间窗口之内的
        pipe.zremrangeByScore(key, 0, nowTs - period * 1000);
        // 获取窗口内的数量
        Response<Long> count = pipe.zcard(key);
        // 设置key的过期时间
        pipe.expire(key, period + 1);
        pipe.exec();
        pipe.close();
        // 看当前窗口内的数量是否小于限流数量
        return count.get() <= maxCount;
    }
    public static void main(String[] args) throws IOException {
        Jedis jedis = new Jedis("110.42.224.58", 6379);
        SimpleRateLimiter limiter = new SimpleRateLimiter(jedis);
        for(int i=0;i<20;i++) {
            System.out.println(limiter.isActionAllowed("shepherd", "limit-key", 60, 5));
        }
    }
}
