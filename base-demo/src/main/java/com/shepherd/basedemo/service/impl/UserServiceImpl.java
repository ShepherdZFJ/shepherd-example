package com.shepherd.basedemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shepherd.basedemo.dao.UserDAO;
import com.shepherd.basedemo.entity.User;
import com.shepherd.basedemo.event.RegisterEvent;
import com.shepherd.basedemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/9 16:30
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserDAO, User> implements UserService {
    @Resource
    private ApplicationContext applicationContext;
    @Resource
    private UserDAO userDAO;


    @Override
    public void registerUser(User user) throws InterruptedException {
        log.info("=====>>>user注册成功了");
        applicationContext.publishEvent(new RegisterEvent(this, user));
        log.info("=====>>>user注册完成结束了");
        TimeUnit.SECONDS.sleep(5);
    }

    @Override
    public void addUser(User user) {
        userDAO.insert(user);
    }

    @Override
    public void batchAddUser(List<User> users) {
        long start = System.currentTimeMillis();
        saveBatch(users);
        long end = System.currentTimeMillis();
        System.out.println("插入时间：" + (end - start) + " ms");
    }


}
