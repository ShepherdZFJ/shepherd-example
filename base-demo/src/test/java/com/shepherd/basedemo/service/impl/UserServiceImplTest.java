package com.shepherd.basedemo.service.impl;

import com.shepherd.basedemo.dao.UserDAO;
import com.shepherd.basedemo.entity.User;
import com.shepherd.basedemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
    @Resource
    private UserDAO userDAO;


    @Test
    public void testWriteLocalDateTime() {
        User user = User.builder().id(1L).userNo("001").gender(0).name("张三").phone("12234")
                .birthday(LocalDate.now()).createTime(LocalDateTime.now()).build();
        userDAO.insert(user);
    }

    @Test
    public void testReadLocalDateTime() {
        User user = userDAO.selectById(1L);
        System.out.println(user);
    }






    @Test
    public void testEvent() throws InterruptedException {
        User user = User.builder().userNo("1111").birthday(LocalDate.now()).gender(0)
                .phone("12345677890").email("shepherd@163.com").name("芽儿哟").build();
        userService.registerUser(user);
    }

    @Test
    public void testAdd() {
        User user = User.builder().userNo("123").birthday(LocalDate.now()).gender(0).
                email("105768@qq.com").phone("1233463466").name("哈哈").isDelete(0).build();
        userService.addUser(user);
    }

    @Test
    public void testAddBatch() {
        List<User> userList = getUserList();
        userService.batchAddUser(userList);
    }

    List<User> getUserList() {
        List<User> users = new ArrayList<>();
        long i = 1;
        while (i <= 100000) {
            User user = new User();
            user.setUserNo(UUID.randomUUID().toString());
            user.setBirthday(LocalDate.now());
            user.setGender( i%2 == 0 ? 0:1);
            user.setEmail(generateRandomEmail());
            user.setPhone(generateRandomPhoneNumber());
            user.setName(generateRandomName());
            user.setIsDelete(0);
            users.add(user);
            i++;
        }
        return users;
    }

    private static final String[] FIRST_NAMES = {"张", "李", "王", "赵", "刘", "陈", "杨", "黄", "周", "吴", "徐", "孙", "朱", "马", "胡", "郭", "何", "高", "林", "罗"};
    private static final String[] MIDDLE_NAMES = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "百", "千", "万", "亿"};
    private static final String[] LAST_NAMES = {"云", "风", "山", "水", "花", "草", "木", "火", "土", "金", "银", "石", "星", "月", "日", "夜"};

    public static String generateRandomName() {
        Random random = new Random();
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String middleName = MIDDLE_NAMES[random.nextInt(MIDDLE_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return firstName + middleName + lastName;
    }

    public static String generateRandomPhoneNumber() {
        Random random = new Random();
        StringBuilder phoneNumber = new StringBuilder();

        // 生成区号（3位）
        phoneNumber.append("1"); // 中国手机号区号通常以1开头
        phoneNumber.append(random.nextInt(10)); // 第二位数字可以是0-9之间的任意数字
        phoneNumber.append(random.nextInt(10)); // 第三位数字可以是0-9之间的任意数字

        // 生成8位手机号码
        for (int i = 0; i < 8; i++) {
            phoneNumber.append(random.nextInt(10)); // 0-9之间的任意数字
        }

        return phoneNumber.toString();
    }

    private static final String[] DOMAINS = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "icloud.com", "qq.com", "163.com", "126.com"};

    public static String generateRandomEmail() {
        Random random = new Random();
        StringBuilder email = new StringBuilder();

        // 生成用户名
        for (int i = 0; i < 8; i++) {
            char c = (char) ('a' + random.nextInt(26)); // 生成随机小写字母
            email.append(c);
        }

        // 随机选择一个域名
        String domain = DOMAINS[random.nextInt(DOMAINS.length)];

        email.append("@").append(domain);

        return email.toString();
    }



}