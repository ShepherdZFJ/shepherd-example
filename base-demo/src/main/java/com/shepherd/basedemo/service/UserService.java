package com.shepherd.basedemo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shepherd.basedemo.dao.UserDAO;
import com.shepherd.basedemo.dto.UserDTO;
import com.shepherd.basedemo.entity.User;
import com.shepherd.basedemo.event.RegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/9 16:30
 */
@Slf4j
@Service
public class UserService extends ServiceImpl<UserDAO, User> {
    @Resource
    private ApplicationContext applicationContext;
    @Resource
    private UserDAO userDAO;


    public void registerUser(User user) throws InterruptedException {
        log.info("=====>>>user注册成功了");
        applicationContext.publishEvent(new RegisterEvent(this, user));
        log.info("=====>>>user注册完成结束了");
        TimeUnit.SECONDS.sleep(5);
    }

    public void addUser(User user) {
        userDAO.insert(user);
    }

    public void batchAddUser(List<User> users) {
        long start = System.currentTimeMillis();
        saveBatch(users);
        long end = System.currentTimeMillis();
        System.out.println("插入时间：" + (end - start) + " ms");
    }

    public void test() {
        log.info("userService的test()执行了");
    }

    @Cacheable(cacheNames = {"user1", "user2"})
    public List<User> listUsers() {
        List<User> list = this.list();
        log.info("执行数据库了......");
        return list;
    }

//    @Cacheable(cacheNames = {"user", "order"})
    public User getUser(Long id) {
        User user = this.getById(id);
        log.info("走数据库查询了......");
        return user;
    }

    @Cacheable(cacheNames = {"user", "order"}, key = "#userDTO.id")
    public User getUser(UserDTO userDTO) {
        Long id = userDTO.getId();
        User user = this.getById(id);
        log.info("走数据库查询了......");
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    @CachePut(cacheNames = {"user", "order"}, key = "#user.id")
    public User updateUser1(User user) {
        user.setCreateTime(LocalDateTime.now());
        userDAO.updateById(user);
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = "user", allEntries = true)
    public void batchSaveUser(List<User> users) {
        this.saveOrUpdateBatch(users);
    }

    @Transactional(rollbackFor = Exception.class)
    @Caching(put = {@CachePut(cacheNames = "user", key = "#user.id")},
             evict = {@CacheEvict(cacheNames = "user", key = "#user.userNo")})
    public User updateUser(User user) {
        user.setCreateTime(LocalDateTime.now());
        userDAO.updateById(user);
        return user;
    }

    public void firstInitData() {

    }


}
