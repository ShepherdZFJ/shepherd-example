package com.shepherd.basedemo.event.listener;

import com.shepherd.basedemo.event.RegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/9 16:17
 * 消息通知监听器
 */
@Slf4j
@Component // 把监听器注册到spring容器中
@Order(2)
//@Async("asyncExecutor")
public class RegisterMsgNoticeListener implements ApplicationListener<RegisterEvent> {
    @Override
    public void onApplicationEvent(RegisterEvent event) {
        log.info("=========>>>站内信通知了");
        log.info("=========>>>短信通知了");
        log.info("=========>>>邮箱通知了");
    }
}
