package com.shepherd.mybatisplus.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shepherd.mybatisplus.demo.dao.UserDAO;
import com.shepherd.mybatisplus.demo.entity.Address;
import com.shepherd.mybatisplus.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/7/1 14:58
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Resource
    private UserServiceImpl userService;

    @Resource
    private UserDAO userDAO;


    @Test
    public void testService() {

    }

    /**
     * 添加一条记录
     */
    @Test
    public void testAdd() {
        User user = User.builder()
                .id(1L)
                .userNo("001")
                .nickname("大哥")
                .email("shepherd@qq.com")
                .phone("1234556")
                .birthday(new Date())
                .build();
        userDAO.insert(user);
    }


    /**
     * 查询所有记录
     */
    @Test
    public void testList() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        List<User> users = userDAO.selectList(queryWrapper);
        System.out.println(users);
    }

    /**
     * mapper层的crud接口方法批量插入
     */
    @Test
    public void testMapperBatchAdd() {
        List<User> users = new ArrayList<>();
        for(long i = 1; i <= 1000; i++) {
            User user = User.builder()
                    .id(i)
                    .userNo("No-" + i)
                    .nickname("哈哈")
                    .phone("12345678901")
                    .email("shepherd_123@qq.com")
                    .birthday(new Date())
                    .gender(0)
                    .isDelete(0)
                    .build();
            users.add(user);
        }
        long start = System.currentTimeMillis();
        users.forEach(user -> {
            userDAO.insert(user);
        });
        long end = System.currentTimeMillis();
        System.out.println("执行时长：" + (end-start) + "毫秒");
    }

    /**
     * service层的crud接口方法批量插入
     */
    @Test
    public void testServiceBatchAdd() {
        List<User> users = new ArrayList<>();
        for(long i = 1; i <= 1000; i++) {
            User user = User.builder()
                    .id(i)
                    .userNo("No-" + i)
                    .nickname("哈哈")
                    .phone("12345678901")
                    .email("shepherd_123@qq.com")
                    .birthday(new Date())
                    .gender(0)
                    .isDelete(0)
                    .build();
            users.add(user);
        }
        long start = System.currentTimeMillis();
        userService.saveBatch(users);
        long end = System.currentTimeMillis();
        System.out.println("执行时长：" + (end-start) + "毫秒");
    }

    /**
     * 逻辑删除
     */
    @Test
    public void testLogicDelete() {
        userDAO.deleteById(1L);
    }

    @Test
    public void testQuery() {
        User user = userDAO.selectById(100L);
        System.out.println(user);
    }

    @Test
    public void testAutoFieldFill() {
        User user = User.builder()
                .id(1001L)
                .userNo("No-001")
                .nickname("哈哈")
                .phone("12345678901")
                .email("shepherd_123@qq.com")
                .birthday(new Date())
                .gender(0)
                .isDelete(0)
                .build();
        userDAO.insert(user);
    }

    @Test
    public void testTypeHandler() {
        Address address = new Address();
        address.setProvince("浙江省");
        address.setCity("杭州市");
        address.setRegion("余杭区");
        address.setAddress("城北万象城");
        address.setId(1L);
        User user = User.builder()
                .id(100L)
                .userNo("No-001")
                .nickname("哈哈")
                .phone("12345678901")
                .email("shepherd_123@qq.com")
                .birthday(new Date())
                .gender(0)
                .isDelete(0)
                .address(address)
                .build();
        userDAO.insert(user);
    }

}