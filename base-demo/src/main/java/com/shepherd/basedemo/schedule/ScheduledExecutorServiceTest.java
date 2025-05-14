package com.shepherd.basedemo.schedule;

import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/12/13 18:06
 */
public class ScheduledExecutorServiceTest {
//    public static void main(String[] args) {
//        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
//        // 参数：1、任务体 2、首次执行的延时时间
//        //      3、任务执行间隔 4、间隔时间单位
////        scheduledExecutor.scheduleAtFixedRate(()->System.out.println("task ScheduledExecutorService "+new Date()),
////                0, 3, TimeUnit.SECONDS);
//
//        scheduledExecutor.schedule(()->System.out.println("task ScheduledExecutorService "+new Date()), 3, TimeUnit.SECONDS);
//        System.out.println(1111);
//    }
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

        // 任务1：延时任务  5秒后执行，只执行1次
        scheduler.schedule(() -> System.out.println("task1 run: " + DateUtil.formatDateTime(new Date()) + " threadName："
                + Thread.currentThread().getName()), 5, TimeUnit.SECONDS);

        // 任务2：延迟1秒后执行，每隔2秒执行一次
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("task2 run: " + DateUtil.formatDateTime(new Date()) + " threadName："
            + Thread.currentThread().getName());
        }, 1, 2, TimeUnit.SECONDS);

        // 任务3：上一个任务结束后，延迟2秒执行
        scheduler.scheduleWithFixedDelay(() -> {
            System.out.println("task3 run: " + DateUtil.formatDateTime(new Date()) + " threadName："
                    + Thread.currentThread().getName());
        }, 1, 2, TimeUnit.SECONDS);
    }


}
