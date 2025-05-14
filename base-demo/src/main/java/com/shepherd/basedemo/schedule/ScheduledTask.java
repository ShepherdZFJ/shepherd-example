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
 * @date 2024/12/16 11:57
 */
//@Component
@Slf4j
public class ScheduledTask {
    // 每隔5秒执行一次
    @Scheduled(fixedRate = 5000)
    public void taskWithFixedRate() {
        log.info("Fixed Rate Task: " + DateUtil.formatDateTime(new Date()));
    }

    // 首次延时3s执行，上次任务结束后5秒再执行
    @Scheduled(fixedDelay = 5000, initialDelay = 3000)
    public void taskWithFixedDelay() {
        log.info("Fixed Delay Task: " + DateUtil.formatDateTime(new Date()));
    }

    // 使用 cron 表达式, 每10秒执行一次
    @SneakyThrows
    @Scheduled(cron = "0/10 * * * * ?", fixedRate = 5000)
    @Async("asyncExecutor")
    public void taskWithCron() {
        log.info("task1: " + DateUtil.formatDateTime(new Date()));
        // 模拟task1执行需要耗费8s
        TimeUnit.SECONDS.sleep(8);
    }
}
