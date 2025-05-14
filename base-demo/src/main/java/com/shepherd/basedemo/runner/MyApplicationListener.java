package com.shepherd.basedemo.runner;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/10/17 18:51
 */
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("应用启动完成，执行 ApplicationReadyEvent 事件监听逻辑！");
    }
}

