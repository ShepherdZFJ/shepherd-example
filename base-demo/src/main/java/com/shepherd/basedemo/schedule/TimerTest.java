package com.shepherd.basedemo.schedule;

import com.shepherd.basedemo.pojo.User;
import lombok.SneakyThrows;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/12/4 14:16
 */
public class TimerTest {
    public static void main(String[] args) {

        TimerTask task1 = new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("task1  run:"+ new Date());
                TimeUnit.SECONDS.sleep(6);
                throw new RuntimeException("task1 error...");
            }
        };
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("task2  run:"+ new Date());
            }
        };
        System.out.println("开始执行了。。。" + new Date());
        Timer timer = new Timer();
        //安排指定的任务在指定的时间开始进行重复的固定延迟执行。这里是每10秒执行一次
        timer.schedule(task1,0,10000);
        timer.schedule(task2,0,10000);

    }
}
