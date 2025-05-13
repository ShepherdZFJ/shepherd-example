//package com.shepherd.basedemo.runner;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.shepherd.basedemo.service.UserService;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//import java.util.Objects;
//
///**
// * @author fjzheng
// * @version 1.0
// * @date 2024/10/9 15:34
// * 数据库初始化
// */
//@Component
//public class DataInitializer implements CommandLineRunner {
//    @Resource
//    private EnvInitMapper envInitMapper;
//    @Resource
//    private UserService userService;
//    @Resource
//    private RoleService roleService;
//    @Resource
//    private UserRoleService userRoleService;
//
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        // 1/判断是不是第一次启动 若是，执行初始数据插入等操作 若不是，不执行
//        // 这个可以读取数据库标志，初始化之后插入一个标志记录即可, 当然也可以读取缓存
//        QueryWrapper<EnvInit>queryWrapper = new QueryWrapper<>();
//        EnvInit init = envInitMapper.selectOne(queryWrapper);
//        if (Objects.isNull(init)) {
//            // 2.第一次初始化环境
//            userService.firstInitData();
//            // 3.插入已经初始化标志
//            init = new EnvInit();
//            init.setIsInit(1);
//            envInitMapper.insert(init);
//        }
//    }
//
//    /**
//     * 初始化环境基础数据，可以插入环境所需的任何数据
//     */
//    @Transactional(rollbackFor = Exception.class)
//    public void initData() {
//        userService.firstInitData();
//        roleService.firstInitData();
//        userRoleService.firstInitData();
//    }
//}
