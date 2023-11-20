package com.shepherd.mybatisplus.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shepherd.mybatisplus.demo.dao.UserDAO;
import com.shepherd.mybatisplus.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/7/1 14:54
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserDAO, User> implements UserService {


    @Resource
    private UserDAO userDAO;

    public List<User> getList() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        List<User> users = userDAO.selectList(queryWrapper);
        return users;
    }
}
