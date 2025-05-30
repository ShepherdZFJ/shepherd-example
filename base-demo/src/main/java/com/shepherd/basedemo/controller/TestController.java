package com.shepherd.basedemo.controller;

import com.alibaba.excel.util.ListUtils;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import com.pig4cloud.plugin.excel.annotation.Sheet;
import com.shepherd.basedemo.param.UserParam;
import com.shepherd.basedemo.dto.UserDTO;
import com.shepherd.basedemo.entity.Address;
import com.shepherd.basedemo.entity.User;
import com.shepherd.basedemo.excel.DemoData;
import com.shepherd.basedemo.excel.anno.ExcelExport;
import com.shepherd.basedemo.handler.LoginUser;
import com.shepherd.basedemo.handler.UserSession;
import com.shepherd.basedemo.service.TestService;
import com.shepherd.basedemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/23 11:53
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private UserService userService;
    @Resource
    private TestService testService;

    private ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("letter-pool-%d").build();
    private ExecutorService fixedThreadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors()*2,
            Runtime.getRuntime().availableProcessors() * 40,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(Runtime.getRuntime().availableProcessors() * 20),
            namedThreadFactory);


    @ResponseExcel(name = "test1", sheets = @Sheet(sheetName = "testSheet1"))
    @GetMapping("/e1")
    public List<DemoData> e1() {
        List<DemoData> dataList = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            DemoData data = new DemoData();
//            data.setUsername("tr1" + i);
//            data.setPassword("tr2" + i);
//            dataList.add(data);
//        }
        return dataList;
    }

    @GetMapping("/user/cache")
    @Cacheable(value = "USER:", key = "#user.id")
    public User testUserCache(User user) {
//        user.setBirthday(new Date());
        return user;
    }

    @GetMapping("/excel/export")
    @ExcelExport(name = "test.xlsx", sheetName = "she-01")
    public List<List<String>> excelExport() {
//        List<User> userList = new ArrayList<>();
//        int i = 0;
//        while (i < 10) {
//            User user = User.builder().id((long)i).userNo("no-" + i).birthday(new Date()).gender(i%3)
//                    .phone("123456789"+i).email("she12dfe@163.com").name("芽儿哟"+i).address("杭州"+i).build();
//            userList.add(user);
//            i++;
//        }
//        return userList;
        List<List<String>> dataList = new ArrayList<>();
        ListUtils.newArrayList("c1", "c2", "c3");
        dataList.add(ListUtils.newArrayList("c1", "c2", "c3"));
        dataList.add(ListUtils.newArrayList("001", "张三", "10"));
        dataList.add(ListUtils.newArrayList("002", "李四", "25"));
        return dataList;

    }

    @GetMapping("/filter")
    public String testFilter() {
        log.info("controller业务方法执行了");
        return "hello";
    }

    @PostMapping("/request/advice")
    public void testRequestBodyAdvice(@RequestBody Address address) {
        System.out.println(address);
    }

    @PostMapping("/111")
    public void test111(@RequestBody User user) {
        System.out.println(user);
    }

    @GetMapping("/222")
    public void test222(User user) {
        System.out.println(user);
    }

    @PostMapping("/date")
    public UserDTO testLocalDateTime(@RequestBody UserParam param) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(param, userDTO);
        System.out.println(userDTO);
        return userDTO;
    }

    @GetMapping("/loginUser")
    public User getUser(@RequestParam("id") Long id, @LoginUser UserSession session) {
        Long userId = session.getId();
        User user = userService.getUser(userId);
        return user;
    }

    @GetMapping("/file")
    public void testFile(HttpServletResponse response) throws IOException {
        ArrayList<String> list = Lists.newArrayList("665441 算法笔记.胡凡.pdf");
        testService.downloadFile(list, response);
    }

    @GetMapping("/async")
    public void testAsync() {
        MDC.put("traceId", UUID.randomUUID().toString().replace("-", ""));
        log.info("打印日志了");
        fixedThreadPool.execute(()->{
            log.info("异步执行了");
            try {
//                TimeUnit.SECONDS.sleep(3);
                User  user = null;
                String name = user.getName();
            } catch (Exception e) {
                log.error("异步报错了：", e);
            }
        });
        MDC.clear();
    }

    public static void main(String[] args) {
        String s = UUID.randomUUID().toString().replace("-", "");
        System.out.println(s);
    }



}
