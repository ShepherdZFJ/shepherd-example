package com.shepherd.basedemo.event.listener;

import com.shepherd.basedemo.event.RegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/9 16:25
 */
@Slf4j
@Component
public class RegisterPushDataListener{

    @EventListener
    @Order(3)
//    @Async("asyncExecutor")
    public void onApplicationEvent(RegisterEvent event) {
        log.info("======>>>推送用户信息到大数据系统了，user={}", event.getUser());
    }
}
