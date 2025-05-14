package com.shepherd.basedemo.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/28 18:26
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate redisTemplate;


    @Test
    public void test() {
//        StringBuilder lua = new StringBuilder();
//        lua.append("local c");
//        lua.append("\nc = redis.call('get',KEYS[1])");
//        // 当前调用累计超过最大值，则直接返回，拒绝请求
//        lua.append("\nif c and tonumber(c) > tonumber(ARGV[1]) then");
//        lua.append("\nreturn c;");
//        lua.append("\nend");
//        // 执行计算器自加
//        lua.append("\nc = redis.call('incr',KEYS[1])");
//        lua.append("\nif tonumber(c) == 1 then");
//        // 从第一次调用开始限流，设置对应键值的过期
//        lua.append("\nredis.call('expire',KEYS[1],ARGV[2])");
//        lua.append("\nend");
//        lua.append("\nreturn c;");
//        int limitCount = 3;
//        int limitPeriod = 60;
//        List<String> keys = new ArrayList<>();
//        keys.add("kk");
//        RedisScript<Number> redisScript = new DefaultRedisScript<>(lua.toString(), Number.class);
//        Number count = stringRedisTemplate.execute(redisScript, keys, String.valueOf(limitCount), String.valueOf(limitPeriod));
//        System.out.println(count);

        ValueOperations<String, Long> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("hoo", 123l);

    }

    @Test
    public void test01() {
        redisTemplate.opsForList().leftPush("list-key", "a");
        redisTemplate.opsForList().leftPushAll("list-key", "b", "c");
        List<String> list = redisTemplate.opsForList().range("list-key", 0, -1);
        System.out.println(list);


    }
}
