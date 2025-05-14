package com.shepherd.basedemo.runner;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2024/10/30 14:43
 */
@Component
public class MyInitializingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bean 属性设置完成，执行 afterPropertiesSet() 方法！");
    }
}