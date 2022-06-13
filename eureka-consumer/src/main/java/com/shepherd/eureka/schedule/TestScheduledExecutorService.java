package com.shepherd.eureka.schedule;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/6/9 10:31
 */
public class TestScheduledExecutorService {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        // 参数：1、任务体 2、首次执行的延时时间
        //      3、任务执行间隔 4、间隔时间单位
//        scheduledExecutor.scheduleAtFixedRate(()->System.out.println("task ScheduledExecutorService "+new Date()),
//                0, 3, TimeUnit.SECONDS);

        scheduledExecutor.schedule(()->System.out.println("task ScheduledExecutorService "+new Date()), 3, TimeUnit.SECONDS);
        System.out.println(1111);
    }
}