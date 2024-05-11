package com.shepherd.basedemo.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/29 11:14
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BaseTest {
    @Resource
    private ApplicationContext applicationContext;

    @Test
    public void test() {
        String applicationName = applicationContext.getApplicationName();
        System.out.println(applicationName);
    }
}
