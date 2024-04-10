package com.shepherd.basedemo.event.listener;

import com.shepherd.basedemo.entity.User;
import com.shepherd.basedemo.event.RegisterEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/9 16:23
 */
@Slf4j
@Component
@Order(1)
//@Async("asyncExecutor")
public class RegisterSendRedPacketListener implements ApplicationListener<RegisterEvent> {

    @SneakyThrows
    @Override
    public void onApplicationEvent(RegisterEvent event) {
        log.info("======>>>发放红包了");
        // 睡眠一下，模拟发送优惠券比较复杂
        User user = null;
        // 空指针错误
        String userNo = user.getUserNo();


        TimeUnit.SECONDS.sleep(2);
        log.info("======>>>发放优惠券了");
    }
}
