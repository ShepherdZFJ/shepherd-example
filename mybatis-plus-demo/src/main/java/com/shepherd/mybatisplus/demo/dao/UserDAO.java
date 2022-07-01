package com.shepherd.mybatisplus.demo.dao;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shepherd.mybatisplus.demo.entity.User;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/7/1 14:42
 */
public interface UserDAO extends BaseMapper<User> {

//    @SqlParser(filter = true)
    User getUser(Long id);
}
