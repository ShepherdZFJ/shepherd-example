package com.shepherd.basedemo.service.impl;

import com.shepherd.basedemo.entity.User;
import com.shepherd.basedemo.event.RegisterEvent;
import com.shepherd.basedemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/9 16:30
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private ApplicationContext applicationContext;


    @Override
    public void registerUser(User user) throws InterruptedException {
        log.info("=====>>>user注册成功了");
        applicationContext.publishEvent(new RegisterEvent(this, user));
        log.info("=====>>>user注册完成结束了");
        TimeUnit.SECONDS.sleep(5);
    }
}
