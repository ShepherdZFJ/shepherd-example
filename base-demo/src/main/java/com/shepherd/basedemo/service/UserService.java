package com.shepherd.basedemo.service;

import com.shepherd.basedemo.entity.User;

import java.util.List;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/9 16:27
 */
public interface UserService {
    void registerUser(User user) throws InterruptedException;

    void addUser(User user);

    void batchAddUser(List<User> users);

    void test();
}
