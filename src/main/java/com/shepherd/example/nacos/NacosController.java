package com.shepherd.example.nacos;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/4/21 14:46
 */
@RestController
@RequestMapping("/nacos/test")
public class NacosController {
    @Resource
    private User user;
    @Resource
    private UserProperties userProperties;
    @GetMapping
    public JSONObject test() {
        JSONObject rst = new JSONObject();
        rst.put("name", userProperties.getName());
        rst.put("user", user);
        return rst;
    }
}
