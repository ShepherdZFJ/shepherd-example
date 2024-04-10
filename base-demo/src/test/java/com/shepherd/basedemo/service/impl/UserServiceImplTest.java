package com.shepherd.basedemo.service.impl;

import com.shepherd.basedemo.entity.User;
import com.shepherd.basedemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/9 16:39
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    @Resource
    private UserService userService;


    @Test
    public void testEvent() throws InterruptedException {
        User user = User.builder().userNo("1111").birthday(new Date()).gender(0)
                .phone("12345677890").email("shepherd@163.com").nickname("芽儿哟").build();
        userService.registerUser(user);
    }

}