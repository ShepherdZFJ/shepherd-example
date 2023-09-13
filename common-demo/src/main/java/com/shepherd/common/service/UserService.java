package com.shepherd.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2023/6/6 15:02
 */
@Service
public class UserService {
    @Autowired
    private RoleService roleService;
}
