package com.shepherd.example;

import com.alibaba.fastjson.JSONObject;
import com.shepherd.example.limit.redis.FixWindowRateLimiter;
import com.shepherd.example.limit.redis.SlideWindowRateLimiter;
import netscape.javascript.JSObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/3/7 18:19
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RateLimiterTest {
    @Resource
    private FixWindowRateLimiter fixWindowRateLimiter;
    @Resource
    private SlideWindowRateLimiter slideWindowRateLimiter;

    @Test
    public void testFixWindow() throws InterruptedException {
        JSONObject jsonObject = JSONObject.parseObject(null);
        for (int i = 0; i < 100; i++) {
            Boolean limit = fixWindowRateLimiter.limit();
            System.out.println(limit);
            if (i==50) {
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }

    @Test
    public void testSlideWindow() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Boolean limit = slideWindowRateLimiter.limit();
//            System.out.println(limit);
            if (i==50) {
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }
}
