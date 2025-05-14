package com.shepherd.basedemo.controller;

import com.shepherd.basedemo.advice.BizException;
import com.shepherd.basedemo.entity.Address;
import com.shepherd.basedemo.param.UserParam;
import com.shepherd.basedemo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2025/5/7
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping
    public void addUser(@RequestBody @Validated({UserParam.Insert.class}) UserParam userParam) {
        System.out.println(userParam);
    }

    @PutMapping
    public void updateUser(@RequestBody @Validated(UserParam.Update.class) UserParam userParam) {
        System.out.println(userParam);
    }

    @GetMapping("/{userId}")
    public void detail(@PathVariable("userId") @Min(value = 1L, message = "userId必须大于0") Long userId) {
        System.out.println(userId);
    }

    @GetMapping("/info")
    public void getUserInfo(@RequestParam("userId") @Max(value = 10L, message = "userId必须不超过10") Long userId) {
        System.out.println(userId);
    }

    @PostMapping("/address")
    public void addAddress(@RequestBody @Validated Address address) {
        System.out.println(address);
    }
}
