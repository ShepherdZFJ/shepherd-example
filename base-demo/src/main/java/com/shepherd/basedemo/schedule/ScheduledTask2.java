package com.shepherd.basedemo.schedule;

import cn.hutool.core.date.DateUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/12/16 16:57
 */
@Component
@Slf4j
public class ScheduledTask2 {
    // 使用 cron 表达式, 每10秒执行一次
    @Async("asyncExecutor")
//    @Scheduled(cron = "0/10 * * * * ?")
    public void taskWithCron() {
        try {
            log.info("task2====>>开始执行");
//            int i = 1/0;
//            log.info("task2====>>结束执行");
        } catch (Exception e) {
            log.error("task2 fail: ", e);
        }

    }
}
