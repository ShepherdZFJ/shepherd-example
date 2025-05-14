package com.shepherd.basedemo.pojo;

import com.google.common.collect.Lists;
import com.shepherd.basedemo.entity.Address;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/11/15 14:36
 */
public class Test {
    public static void main(String[] args) {
//        List<String> list = list2.stream().filter(s -> !list1.contains(s)).collect(Collectors.toList());
//        System.out.println(list);
//        System.out.println(list1.size());
//        System.out.println(list2.size());
//        Set<String> duplicates = list1.stream()
//                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
//                .entrySet()
//                .stream()
//                .filter(entry -> entry.getValue() > 1) // 筛选出现次数 > 1 的值
//                .map(Map.Entry::getKey)               // 提取 key
//                .collect(Collectors.toSet());




        Long i = 0L;
        System.out.println(i.equals(0));

        // 当前时间
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime midnight = LocalDateTime.of(now.toLocalDate().plusDays(1), LocalTime.MIDNIGHT);
        // 计算分钟差
        long minutesUntilMidnight = Duration.between(now, midnight).toMinutes();
        System.out.println(minutesUntilMidnight);
        LocalDate date = LocalDate.of(2024, 12, 16);
        System.out.println(date.getYear());
        System.out.println(date.getMonthValue());
        System.out.println(date.getDayOfMonth());

        System.out.println(UUID.randomUUID().toString().replace("-", ""));

    }
}
