package com.shepherd.basedemo.runner;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/10/30 14:40
 */
@Component
public class MyEventListener {

    @EventListener(ApplicationStartedEvent.class)
    public void onApplicationStarted() {
        System.out.println("Spring Boot 启动时，执行 ApplicationStartedEvent 监听逻辑！");
    }
}
