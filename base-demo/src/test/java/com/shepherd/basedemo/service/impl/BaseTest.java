package com.shepherd.basedemo.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

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

    @Value("${ip.black}")
    private Set<String> blackIpList;

    @Value("${ip.white}")
    private Set<String> whiteIpList;

    @Test
    public void test() {
        String applicationName = applicationContext.getApplicationName();
        System.out.println(applicationName);
    }
}
