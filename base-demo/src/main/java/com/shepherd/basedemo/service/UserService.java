package com.shepherd.basedemo.service;

import com.shepherd.basedemo.entity.User;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/4/9 16:27
 */
public interface UserService {
    void registerUser(User user) throws InterruptedException;
}
