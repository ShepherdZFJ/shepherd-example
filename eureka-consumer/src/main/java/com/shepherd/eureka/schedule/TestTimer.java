package com.shepherd.eureka.schedule;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/6/9 10:26
 */
public class TestTimer {
    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("task  run:"+ new Date());
            }
        };
        Timer timer = new Timer();
        //安排指定的任务在指定的时间开始进行重复的固定延迟执行。这里是每3秒执行一次
        timer.schedule(timerTask,10,3000);

    }
}
