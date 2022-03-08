package com.shepherd.example.limit;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/3/7 17:34
 */

import java.util.concurrent.TimeUnit;

/**
 * 固定窗口时间算法
 * @return
 */
public class Counter {
    public long timeStamp = System.currentTimeMillis(); // 当前时间
    public int reqCount = 0; // 初始化计数器
    public final int limit = 10; // 时间窗口内最大请求数
    public final long interval = 1000 ; // 时间窗口ms

    public boolean limit() {
        long now = System.currentTimeMillis();
        if (now < timeStamp + interval) {
            // 在时间窗口内
            reqCount++;
            // 判断当前时间窗口内是否超过最大请求控制数
            return reqCount <= limit;
        } else {
            timeStamp = now;
            // 超时后重置
            reqCount = 1;
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        for (int i = 0; i < 100; i++) {
            boolean limit = counter.limit();
            System.out.println(limit);
            if (i == 50) {
                TimeUnit.SECONDS.sleep(2);
            }
        }
    }
}
