package com.shepherd.basedemo.runner;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/10/17 18:46
 */

@Service
public class MyService {
    @PostConstruct
    public void init() {
        System.out.println("Bean 初始化完成后执行 @PostConstruct 方法！");
    }
}
