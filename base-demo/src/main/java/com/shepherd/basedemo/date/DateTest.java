package com.shepherd.basedemo.date;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/30 11:52
 */
public class DateTest {

    private static ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(20);
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static ThreadLocal<SimpleDateFormat> holder = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static void main(String[] args) {

        // 创建一个LocalDateTime对象
        LocalDateTime localDateTime = LocalDateTime.now();
        // 将LocalDateTime转换为Date
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        // 打印结果
        System.out.println("LocalDateTime: " + localDateTime);
        System.out.println("转换后的Date: " + date);

        // 创建一个LocalDateTime对象
        LocalDate localDate= LocalDate.now();
        // 将LocalDateTime转换为Date
        Date date1 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        // 打印结果
        System.out.println("LocalDateTime: " + localDateTime);
        System.out.println("转换后的Date1: " + date1);

        // 创建一个java.util.Date对象
        Date date2 = new Date();
        // 将Date转换为Instant
        Instant instant = date2.toInstant();
        // 获取系统默认时区
        ZoneId zoneId = ZoneId.systemDefault();
        // 将Instant转换为LocalDateTime
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant, zoneId);
        // 输出结果
        System.out.println("Date2: " + date);
        System.out.println("LocalDateTime2: " + localDateTime2);







//        int day = 4 ;
//        // 获取当前日期的年和月、日
//        LocalDate currentDate = LocalDate.now();
//        int currentYear = currentDate.getYear();
//        int currentMonth = currentDate.getMonthValue();
//        int currentDay = currentDate.getDayOfMonth();
//        // 计算下个月的年和月
//        int nextMonth = currentMonth + 1;
//        if (currentDay < day) {
//            nextMonth = currentMonth;
//        }
//        int nextMonthYear = currentYear;
//        if (nextMonth > 12) {
//            nextMonth = 1;
//            nextMonthYear = currentYear + 1;
//        }
//        // 获取下个月的YearMonth对象
//        YearMonth nextYearMonth = YearMonth.of(nextMonthYear, nextMonth);
//        // 获取下个月的天数
//        int daysInNextMonth = nextYearMonth.lengthOfMonth();
//        // 如果指定的日期超过了下个月的天数，则使用下个月的最后一天
//        int dayToUse = Math.min(day, daysInNextMonth);
//        LocalDate localDate = LocalDate.of(nextMonthYear, nextMonth, dayToUse);
//        System.out.println(localDate);
//        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

//        // 当前日期时间
//        LocalDateTime now = LocalDateTime.now();
//
//        // 定义格式化器
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//        // 格式化日期时间
//        String formattedDateTime = now.format(formatter);
//        System.out.println("Formatted date and time: " + formattedDateTime);
//
//        // 解析日期时间
//        LocalDateTime parsedDateTime = LocalDateTime.parse(formattedDateTime, formatter);
//        System.out.println("Parsed date and time: " + parsedDateTime);





//        LocalDate today = LocalDate.now();
//        System.out.println("today: " + today);
//        LocalDate date = LocalDate.of(2024, 7, 4);
//        System.out.println("date: " + date);
//        boolean b = today.equals(date);
//        System.out.println("today 与 date是否相等：" + b);
//        LocalDate afterDate = LocalDate.of(2024, 8, 10);
//        boolean after = afterDate.isAfter(today);
//        System.out.println("after today: " + after);
//        LocalDate beforeDate = LocalDate.of(2024, 7, 3);
//        boolean before = beforeDate.isBefore(today);
//        System.out.println("before today: " + before);
//
//        Period period = Period.between(today, afterDate);
//        System.out.println("相差天数：" + period.getDays());
//        System.out.println("相差月份：" + period.getMonths());


//        // 当前日期
//        LocalDate today = LocalDate.now();
//        System.out.println("today: " + today);
//        LocalDate localDate = today.plusDays(30);
//        System.out.println("after 30 days: " + localDate);
//        LocalDate weeks = today.plusWeeks(2);
//        System.out.println("after 2 weeks: " + weeks);
//        LocalDate months = today.minusMonths(3);
//        System.out.println("before 3 months: " + months);
//        LocalDate years = today.minusYears(5);
//        System.out.println("before 5 years: " + years);
//        today = today.plus(Period.ofDays(1)).minus(1, ChronoUnit.DAYS);
//        System.out.println("plus 1 and minus 1, today: " + today);
//
//        // 当前日期时间
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println("now: " + now);
//        LocalDateTime hours = now.plusHours(12);
//        System.out.println("after 12 hours: " + 12);
//        LocalDateTime minutes = now.minusMinutes(30);
//        System.out.println("before 30 minutes: " + minutes);

    }

    public static void test5() {
        Date now = new Date();
        System.out.println("now: " + simpleDateFormat.format(now));
        // 加10天
        Date afterDate = new Date(now.getTime() + 30 * 24 * 60 * 60 * 1000L);
        System.out.println("afterDate: " + simpleDateFormat.format(afterDate));
        LocalDateTime now1 = LocalDateTime.now();
        LocalDateTime localDateTime = now1.plusDays(30);
        System.out.println(localDateTime);
        for ( int i = 0; i < 50; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    System.out.println(holder.get().parse("2024-07-02 18:30:00"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        LocalDate today = LocalDate.now();
        System.out.println("Current date: " + today);
        LocalDateTime now2 = LocalDateTime.now();
        System.out.println("now: " + now2);
        // 创建一个特定日期时间
        LocalDateTime specificDateTime = LocalDateTime.of(2024, 6, 13, 10, 15);
        System.out.println("Specific date and time: " + specificDateTime);
        // Date的可变性问题
        Date date = new Date();
        System.out.println("Original date: " + date);
        date.setTime(1000000000000L);
        System.out.println("Modified date: " + date);

    }

    public static void test4() {
        String dayAfterTommorrow = "20140116";
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.printf("Date generated from String %s is %s %n", dayAfterTommorrow, formatted);

    }

    public static void test() {
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

    public static void test1() {
        Integer periodDate = 20;
        // 获取当前日期的年和月、日
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        int currentDay = currentDate.getDayOfMonth();
        // 计算下个月的年和月
        int nextMonth = currentMonth + 1;
        if (currentDay < periodDate) {
            nextMonth = currentMonth;
        }
        int nextMonthYear = currentYear;
        if (nextMonth > 12) {
            nextMonth = 1;
            nextMonthYear = currentYear + 1;
        }
        // 获取下个月的YearMonth对象
        YearMonth nextYearMonth = YearMonth.of(nextMonthYear, nextMonth);
        // 获取下个月的天数
        int daysInNextMonth = nextYearMonth.lengthOfMonth();
        // 如果指定的日期超过了下个月的天数，则使用下个月的最后一天
        int dayToUse = Math.min(periodDate, daysInNextMonth);
        LocalDate localDate = LocalDate.of(nextMonthYear, nextMonth, dayToUse);
        System.out.println(localDate);
//        allotCase.setPeriodEndTime(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    public static void test2() {
        Integer periodAfterMonth = 2;
        // 获取当前日期的年和月、日
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        // 计算下个月的年和月
        int nextMonth = currentMonth + periodAfterMonth - 1;
        int nextMonthYear = currentYear;
        if (nextMonth > 12) {
            nextMonth = 1;
            nextMonthYear = currentYear + 1;
        }
        // 获取下个月的YearMonth对象
        YearMonth nextYearMonth = YearMonth.of(nextMonthYear, nextMonth);
        // 获取下个月的天数
        int daysInNextMonth = nextYearMonth.lengthOfMonth();
        LocalDate localDate = LocalDate.of(nextMonthYear, nextMonth, daysInNextMonth);
        System.out.println(localDate);
//        allotCase.setPeriodEndTime(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    public static void test3() {
        LocalDateTime today = LocalDateTime.now();
        System.out.println("Today's Local date : " + today);
    }
}

