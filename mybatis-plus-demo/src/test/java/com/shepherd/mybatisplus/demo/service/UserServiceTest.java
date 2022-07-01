package com.shepherd.mybatisplus.demo.service;

import com.shepherd.mybatisplus.demo.dao.UserDAO;
import com.shepherd.mybatisplus.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/7/1 14:58
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Resource
    private UserService userService;

    @Resource
    private UserDAO userDAO;

    @Test
    public void testList() {
        List<User> list = userService.getList();
        System.out.println(list);
    }

    @Test
    public void test1() {
        User user = userDAO.getUser(1115l);
        System.out.println(user);
    }

}