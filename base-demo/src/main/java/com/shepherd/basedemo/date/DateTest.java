package com.shepherd.basedemo.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/30 11:52
 */
public class DateTest {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {

        // 案例1：simpleDateFormat跨年格式化问题
        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
        System.out.println("defaultLocale:" + Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.DECEMBER, 29,0,0,0);
        SimpleDateFormat YYYY = new SimpleDateFormat("YYYY-MM-dd");
        System.out.println("格式化: " + YYYY.format(calendar.getTime()));
        System.out.println("weekYear:" + calendar.getWeekYear());
        System.out.println("firstDayOfWeek:" + calendar.getFirstDayOfWeek());
        System.out.println("minimalDaysInFirstWeek:" + calendar.getMinimalDaysInFirstWeek());

        // 案例2：simpleDateFormat线程安全问题
//        ExecutorService threadPool = Executors.newFixedThreadPool(20);
//        for (int i = 0; i < 10; i++) {
//            //提交20个并发解析时间的任务到线程池，模拟并发环境
//            threadPool.execute(() -> {
//                for (int j = 0; j < 10; j++) {
//                    try {
//                        System.out.println(simpleDateFormat.parse("2020-01-01 11:12:13"));
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//        threadPool.shutdown();
//        threadPool.awaitTermination(1, TimeUnit.HOURS);


        Date today = new Date();
        Date nextMonth = new Date(today.getTime() + 30L * 1000 * 60 * 60 * 24);
        System.out.println(today);
        System.out.println(nextMonth);
    }
}

